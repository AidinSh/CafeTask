import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SuccessfulLogin extends Setup{
    //Account
    String username = "AidinS";
    String email = "aidin_shahmoradi@yahoo.com";
    String password = "duma66641478";


    public void swipeChangeLog(ExtentTest test){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MobileElement tvChangelog = driver.findElementById("com.fastaccess.github:id/title");
        Point location = tvChangelog.getLocation();

        //Swipe down the ChangeLog
        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(location)).moveTo(PointOption.point(new Point(location.x, location.y+200))).release().perform();
        test.log(Status.PASS, "Swpie the Change Log Modal");
    }


    public void tapOnBasiAuth(ExtentTest test){
        MobileElement btnBasicAuthentication = driver.findElementById("com.fastaccess.github:id/basicAuth");
        btnBasicAuthentication.click();
        test.log(Status.PASS, "Tap on Basic Authentication Button");
    }

    public void fillUserAndPass(ExtentTest test){
        MobileElement etUserName = driver.findElementById("com.fastaccess.github:id/usernameEditText");
        MobileElement etPassword = driver.findElementById("com.fastaccess.github:id/passwordEditText");
        MobileElement btnLogin = driver.findElementById("com.fastaccess.github:id/login");

        etUserName.setValue(username);
        etPassword.setValue(password);
        btnLogin.click();
        test.log(Status.PASS, "Filled the username and password and tap on login button");
    }

    public void openSideMenu(ExtentTest test){
        MobileElement btnSideMenu = driver.findElementByClassName("android.widget.ImageButton");
        btnSideMenu.click();
        test.log(Status.PASS, "Tap on side menu button");
    }

    public void tapOnProfile(ExtentTest test){
        List<MobileElement> navBar = driver.findElementsByClassName("android.widget.TextView");
        MobileElement btnProfile = null;
        for (MobileElement element: navBar){
            if(element.getText().equals("PROFILE")){
                btnProfile = element;
                break;
            }
        }
        btnProfile.click();
        test.log(Status.PASS, "tap on profile button");
    }

    public void tapOnLogout(ExtentTest test){
        MobileElement btnLogout = driver.findElementById("com.fastaccess.github:id/logout");
        btnLogout.click();
        test.log(Status.PASS, "tap on logout button");
    }

    public void confirmLogout(ExtentTest test){
        MobileElement btnYes = driver.findElementById("com.fastaccess.github:id/ok");
        btnYes.click();
        test.log(Status.PASS, "tap on Yes button");
    }

    public void fillSearchBox(ExtentTest report, String phrase){
        MobileElement btnSearch = driver.findElementById("com.fastaccess.github:id/search");
        btnSearch.click();
        MobileElement etSearch = driver.findElementById("com.fastaccess.github:id/searchEditText");
        etSearch.click();
        etSearch.setValue(phrase);
        btnSearch.click();
        report.log(Status.PASS, "Fill and submit the search");
    }

    public void chooseSearchResult(ExtentTest report, String phrase){
        List<MobileElement> searchList = driver.findElementsByClassName("android.widget.TextView");
        MobileElement tvResult = null;
        for (MobileElement element:searchList){
            if (element.getText().equals(phrase)){
                tvResult = element;
                break;
            }
        }
        tvResult.click();
        report.log(Status.PASS, "Tap on desired search result");
    }

    public void tapOnIssuesButton(ExtentTest report){
        MobileElement btnIssues = driver.findElementById("com.fastaccess.github:id/issues");
        btnIssues.click();
        report.log(Status.PASS, "Tap on Issues Button");
    }

    public void tapOnIssuesOptionsButton(ExtentTest report){
        MobileElement btnMakeIssue = driver.findElementById("com.fastaccess.github:id/fab");
        btnMakeIssue.click();
        report.log(Status.PASS, "Tap on Options button in issues menu");
    }

    public void tapOnAddIssue(ExtentTest report){
        MobileElement btnAdd = driver.findElementById("com.fastaccess.github:id/add");
        btnAdd.click();
        report.log(Status.PASS, "Tap on Add Issue Button");
    }

    public void fillIssueFields(ExtentTest report, String title, String description){
        MobileElement etTitle = driver.findElementByClassName("android.widget.EditText");
        MobileElement etDescription = driver.findElementById("com.fastaccess.github:id/description");
        etTitle.setValue(title);
        etDescription.click();
        MobileElement etTextField = driver.findElementById("com.fastaccess.github:id/editText");
        etTextField.setValue(description);
        MobileElement btnSubmitDescription = driver.findElementById("com.fastaccess.github:id/submit");
        btnSubmitDescription.click();
        MobileElement btnSubmit = driver.findElementById("com.fastaccess.github:id/submit");
        btnSubmit.click();
    }

    @Test
    public void LoginTest(){
        ExtentTest report = extent.createTest("Login Successfully", "Successful Login through Basic Authentication");
        try {
            swipeChangeLog(report);
            tapOnBasiAuth(report);
            fillUserAndPass(report);
            Thread.sleep(5000);
            boolean isLoggedIn = false;
            isLoggedIn = driver.findElementById("com.fastaccess.github:id/toolbar").isDisplayed();
            if (isLoggedIn) {
                Assert.assertTrue(isLoggedIn, "User is logged in");
                report.pass("User is logged in");
            }else {
                Assert.assertTrue(isLoggedIn, "User is not logged in");
                report.fail("User is not logged in");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //@Test
    public void Logout(){
        ExtentTest report = extent.createTest("Logout Successfully", "Successful Logout");
        try {
            openSideMenu(report);
            tapOnProfile(report);
            tapOnLogout(report);
            confirmLogout(report);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //@Test
    public void makeIssue(){
        ExtentTest report = extent.createTest("Make Issue", "Successfully make an issue");
        try{
            tapOnBasiAuth(report);
            fillUserAndPass(report);
            Thread.sleep(5000);
            fillSearchBox(report,"cafebazaar/Hop");
            Thread.sleep(3000);
            chooseSearchResult(report, "cafebazaar/Hop");
            tapOnIssuesButton(report);
            tapOnIssuesOptionsButton(report);
            tapOnAddIssue(report);
            fillIssueFields(report, "Test3", "test3");
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
