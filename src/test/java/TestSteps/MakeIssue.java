package TestSteps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.concurrent.TimeUnit;

public class MakeIssue {
    AppiumDriver<MobileElement> driver;

    public MakeIssue(AppiumDriver<MobileElement> driver){
        this.driver = driver;
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
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        MobileElement btnSubmit = driver.findElementById("com.fastaccess.github:id/submit");
        btnSubmit.click();
    }
}
