package TestSteps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserAuthentication {

    AppiumDriver<MobileElement> driver;

    public UserAuthentication(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }

    public void swipeChangeLog(ExtentTest test){
        //Checks if the ChangeLog modal is showing ro not
        try{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            MobileElement tvChangelog = driver.findElementById("com.fastaccess.github:id/title");
            Point location = tvChangelog.getLocation();

            //Swipe down the ChangeLog
            TouchAction action = new TouchAction(driver);
            action.longPress(PointOption.point(location)).moveTo(PointOption.point(new Point(location.x, location.y+200))).release().perform();
            test.log(Status.PASS, "Swpie the Change Log Modal");

        }catch (NoSuchElementException e){
            return;
        }

    }

    public void tapOnBasiAuth(ExtentTest report){
        MobileElement btnBasicAuthentication = driver.findElementById("com.fastaccess.github:id/basicAuth");
        btnBasicAuthentication.click();
        report.log(Status.PASS, "Tap on Basic Authentication Button");
    }

    public void fillUserAndPass(ExtentTest report, String username, String password){
        MobileElement etUserName = driver.findElementById("com.fastaccess.github:id/usernameEditText");
        MobileElement etPassword = driver.findElementById("com.fastaccess.github:id/passwordEditText");
        MobileElement btnLogin = driver.findElementById("com.fastaccess.github:id/login");

        etUserName.setValue(username);
        etPassword.setValue(password);
        btnLogin.click();
        report.log(Status.PASS, "Filled the username and password and tap on login button");
    }

    public void openSideMenu(ExtentTest report){
        MobileElement btnSideMenu = driver.findElementByClassName("android.widget.ImageButton");
        btnSideMenu.click();
        report.log(Status.PASS, "Tap on side menu button");
    }

    public void tapOnProfile(ExtentTest report){
        List<MobileElement> navBar = driver.findElementsByClassName("android.widget.TextView");
        MobileElement btnProfile = null;
        for (MobileElement element: navBar){
            if(element.getText().equals("PROFILE")){
                btnProfile = element;
                break;
            }
        }
        btnProfile.click();
        report.log(Status.PASS, "tap on profile button");
    }

    public void tapOnLogout(ExtentTest report){
        MobileElement btnLogout = driver.findElementById("com.fastaccess.github:id/logout");
        btnLogout.click();
        report.log(Status.PASS, "tap on logout button");
    }

    public void confirmLogout(ExtentTest report){
        MobileElement btnYes = driver.findElementById("com.fastaccess.github:id/ok");
        btnYes.click();
        report.log(Status.PASS, "tap on Yes button");
    }

}
