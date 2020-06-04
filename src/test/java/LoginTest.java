import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTest extends Setup{
    //Account
    String username = "AidinSh";
    String email = "aidin_shahmoradi@yahoo.com";
    String password = "duma66641478";

    //MobileElement tglViewPassword = driver.findElementById("com.fastaccess.github:id/text_input_password_toggle");

    @Test
    public void successfulLoginAndLogout(){
        try {
            //Wait for ChangeLog modal to appear
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            MobileElement tvChangelog = driver.findElementById("com.fastaccess.github:id/title");
            Point location = tvChangelog.getLocation();

            //Swipe down the ChangeLog
            TouchAction action = new TouchAction(driver);
            action.longPress(PointOption.point(location)).moveTo(PointOption.point(new Point(location.x, location.y+200))).release().perform();

            //Tap on Basic Authentication Button
            MobileElement btnBasicAuthentication = driver.findElementById("com.fastaccess.github:id/basicAuth");
            btnBasicAuthentication.click();

            //Fill Username & Password fields and click on Login button
            MobileElement etUserName = driver.findElementById("com.fastaccess.github:id/usernameEditText");
            MobileElement etPassword = driver.findElementById("com.fastaccess.github:id/passwordEditText");
            MobileElement btnLogin = driver.findElementById("com.fastaccess.github:id/login");

            etUserName.setValue(username);
            etPassword.setValue(password);
            btnLogin.click();

            //Waiting for the response of GitHub
            Thread.sleep(5000);

            //Opening Side Menu
            MobileElement btnSideMenu = driver.findElementByClassName("android.widget.ImageButton");
            btnSideMenu.click();

            //Taping on Profile button
            List<MobileElement> navBar = driver.findElementsByClassName("android.widget.TextView");
            MobileElement btnProfile = null;
            for (MobileElement element: navBar){
                if(element.getText().equals("PROFILE")){
                    btnProfile = element;
                    break;
                }
            }
            btnProfile.click();

            //Tap on Logout Button
            MobileElement btnLogout = driver.findElementById("com.fastaccess.github:id/logout");
            btnLogout.click();

            //Confirm Logout
            MobileElement btnYes = driver.findElementById("com.fastaccess.github:id/ok");
            btnYes.click();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
