package TestSteps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.List;

public class Search {
    AppiumDriver<MobileElement> driver;

    public Search(AppiumDriver<MobileElement> driver){
        this.driver = driver;
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
}
