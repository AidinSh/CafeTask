import TestSteps.Issue;
import TestSteps.Search;
import TestSteps.UserAuthentication;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

//Test Suite
public class TestSuite extends Setup {
    //User Credentials
    String username = "username";
    String password = "password";

    @Test (priority = 0, enabled = true)
    public void successfulLogin(){
        UserAuthentication userAuth = new UserAuthentication(driver);

        ExtentTest report = extent.createTest("Login Successfully", "Successful Login through Basic Authentication");
        try {
            userAuth.swipeChangeLog(report);
            userAuth.tapOnBasiAuth(report);
            userAuth.fillUserAndPass(report, username, password);
            //Waiting for Server Response
            Thread.sleep(5000);

            //Validating that the user is logged in or not and make the report
            boolean isLoggedIn;
            try {
                driver.findElementById("com.fastaccess.github:id/toolbar");
                isLoggedIn = true;
                report.pass("User is logged in!");
            } catch (NoSuchElementException e){
                isLoggedIn = false;
                report.fail("User is not logged in!");
            }
            Assert.assertTrue(isLoggedIn);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test (priority = 1, enabled = true)
    public void successfulLogout(){
        UserAuthentication userAuth = new UserAuthentication(driver);
        ExtentTest report = extent.createTest("Logout Successfully", "Successful Logout");
        try {
            userAuth.openSideMenu(report);
            userAuth.tapOnProfile(report);
            userAuth.tapOnLogout(report);
            userAuth.confirmLogout(report);

            //Validating that the user is logged out or not
            boolean isLoggedOut;
            try{
                driver.findElementsById("com.fastaccess.github:id/basicAuth");
                isLoggedOut = true;
                report.pass("User is logged out");
            }catch (NoSuchElementException e){
                isLoggedOut = false;
                report.fail("User is not logged out");
            }
            Assert.assertTrue(isLoggedOut);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test (priority = 2, enabled = true)
    public void makeIssueSuccessfully(){
        UserAuthentication userAuth = new UserAuthentication(driver);
        Issue issue = new Issue(driver);
        Search search = new Search(driver);

        ExtentTest report = extent.createTest("Make Issue", "Successfully make an issue");
        try{
            userAuth.swipeChangeLog(report);
            userAuth.tapOnBasiAuth(report);
            userAuth.fillUserAndPass(report, username, password);
            //Waiting for server response
            Thread.sleep(5000);
            search.fillSearchBox(report,"cafebazaar/Hop");
            //Waiting for search results
            Thread.sleep(3000);
            search.chooseSearchResult(report, "cafebazaar/Hop");
            issue.tapOnRepoIssuesButton(report);
            issue.tapOnIssuesOptionsButton(report);
            issue.tapOnAddIssue(report);
            //Waiting for issue to be created
            Thread.sleep(3000);
            issue.fillIssueFields(report, "Test1", "test1");

            //Validating that the issue is created and make the report
            boolean isIssueCreated = false;
            try{
                List<MobileElement> titles = driver.findElementsById("com.fastaccess.github:id/title");
                for(MobileElement element:titles){
                    if(element.getText().equals("Test1")){
                        report.pass("Issue Created");
                        isIssueCreated = true;
                        break;
                    }else {
                        report.fail("Issue is not created");
                    }
                }
            }catch (NoSuchElementException e){
                report.fail("Issue is not created");
            }
            Assert.assertTrue(isIssueCreated);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test (priority = 3, enabled = true)
    public void editIssue(){
        UserAuthentication userAuth = new UserAuthentication(driver);
        Issue issue = new Issue(driver);
        Search search = new Search(driver);

        String newTitle = "Test5";
        String newDesc = "Test5 desc";

        ExtentTest report = extent.createTest("Edit Issue", "Successfully Edit an issue");

        try{
            driver.navigate().back();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.navigate().back();
            issue.tapOnUserIssuesButton(report);
            issue.tapOnDesiredUserIssue(report, "Test1");
            issue.openEditUserIssue(report);
            issue.editUserIssue(report, newTitle, newDesc);
            MobileElement tvTitle = driver.findElementById("com.fastaccess.github:id/headerTitle");
            MobileElement tvDesc = driver.findElementById("com.fastaccess.github:id/comment");

            //Validating that the issue is edited and make the report
            Assert.assertEquals(tvTitle.getText(), newTitle);
            Assert.assertEquals(tvDesc.getText(), newDesc);

            if (tvTitle.getText().equals(newTitle) && tvDesc.getText().equals(newDesc)){
                report.pass("Issue edited successfully");
            }else {
                report.fail("editing issue failed!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
