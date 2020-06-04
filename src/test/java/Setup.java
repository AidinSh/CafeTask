import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Setup {
    //Device Specifications
    String deviceName ="Nokia 6.1 Plus";
    String udid ="192.168.1.42:5555";
    String platformName = "Android";
    String platformVersion = "10.0";

    //App Specifications
    String packageName = "com.fastaccess.github";
    String activityName = "com.fastaccess.ui.modules.main.MainActivity";

    //Appium Server URL
    String appiumURL = "http://127.0.0.1:4723/wd/hub";

    AppiumDriver<MobileElement> driver;
    DesiredCapabilities caps;
    ExtentReports extent;

    @BeforeTest
    public void setup(){
        try {
            caps = new DesiredCapabilities();
            //Device Capabilities
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("udid", udid);
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", platformVersion);

            //App Capabilities
            caps.setCapability("appPackage", packageName);
            caps.setCapability("appActivity", activityName);

            //Appium Server URL
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<MobileElement>(url, caps);

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("report.html");
            // create ExtentReports and attach reporter(s)
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void teardown(){
        extent.flush();
        driver.quit();
    }
}
