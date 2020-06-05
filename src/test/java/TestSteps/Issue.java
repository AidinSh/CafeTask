package TestSteps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Issue {
    AppiumDriver<MobileElement> driver;

    public Issue(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }

    public void tapOnRepoIssuesButton(ExtentTest report){
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

    public void tapOnUserIssuesButton(ExtentTest report){
        MobileElement btnIssues = driver.findElementById("com.fastaccess.github:id/pinned");
        btnIssues.click();
        report.log(Status.PASS, "Tap on user issues button");
    }

    public void tapOnDesiredUserIssue(ExtentTest report, String title){
        try {
            List<MobileElement> issues = driver.findElementsByClassName("android.widget.TextView");
            MobileElement tvResult = null;
            for(MobileElement element:issues){
                if(element.getText().equals(title)){
                    tvResult = element;
                    break;
                }
            }
            tvResult.click();
            report.log(Status.PASS, "Tap on desired user issue");
        }catch (NoSuchElementException e){
            report.fail("There is no issue with this title!");
        }
    }

    public void openEditUserIssue(ExtentTest report){
        MobileElement btnEditMenu = driver.findElementById("com.fastaccess.github:id/editMenu");
        btnEditMenu.click();
        MobileElement btnEdit = driver.findElementByXPath("//*[@resource-id='com.fastaccess.github:id/title' and contains(@text,'Edit')]");
        btnEdit.click();
        report.log(Status.PASS, "Tap on edit issue button");
    }

    public void editUserIssue(ExtentTest report, String title, String description){
        MobileElement etTitle = driver.findElementByClassName("android.widget.EditText");
        MobileElement etDescription = driver.findElementById("com.fastaccess.github:id/description");
        etTitle.clear();
        etTitle.setValue(title);
        etDescription.click();
        MobileElement etTextField = driver.findElementById("com.fastaccess.github:id/editText");
        etTextField.clear();
        etTextField.setValue(description);
        MobileElement btnSubmitDescription = driver.findElementById("com.fastaccess.github:id/submit");
        btnSubmitDescription.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        MobileElement btnSubmit = driver.findElementById("com.fastaccess.github:id/submit");
        btnSubmit.click();
        report.log(Status.PASS, "Edit issue successfully");
    }
}
