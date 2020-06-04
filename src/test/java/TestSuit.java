import TestSteps.MakeIssue;
import TestSteps.Search;
import TestSteps.UserAuthentication;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

//Test Suit
public class TestSuit extends Setup {
    //Account
    String username = "AidinSh";
    String password = "duma66641478";

    //Test Steps from each class needed
    UserAuthentication userAuth = new UserAuthentication(driver);
    MakeIssue issue = new MakeIssue(driver);
    Search search = new Search(driver);

    @Test
    public void SuccessfulLogin(){
        ExtentTest report = extent.createTest("Login Successfully", "Successful Login through Basic Authentication");
        try {
            userAuth.swipeChangeLog(report);
            userAuth.tapOnBasiAuth(report);
            userAuth.fillUserAndPass(report, username, password);
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

    @Test
    public void SuccessfulLogout(){
        ExtentTest report = extent.createTest("Logout Successfully", "Successful Logout");
        try {
            userAuth.openSideMenu(report);
            userAuth.tapOnProfile(report);
            userAuth.tapOnLogout(report);
            userAuth.confirmLogout(report);

            //Validating that the user is loggedout or not
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

    @Test
    public void makeIssueSuccessfully(){
        ExtentTest report = extent.createTest("Make Issue", "Successfully make an issue");
        try{
            userAuth.tapOnBasiAuth(report);
            userAuth.fillUserAndPass(report, username, password);
            Thread.sleep(5000);
            search.fillSearchBox(report,"cafebazaar/Hop");
            Thread.sleep(3000);
            search.chooseSearchResult(report, "cafebazaar/Hop");
            issue.tapOnIssuesButton(report);
            issue.tapOnIssuesOptionsButton(report);
            issue.tapOnAddIssue(report);
            issue.fillIssueFields(report, "Test3", "test3");
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
