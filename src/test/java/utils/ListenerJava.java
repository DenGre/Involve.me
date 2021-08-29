package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ListenerJava extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
        if (webDriverAttribute instanceof WebDriver) {
            AllureAttachment.attachScreenshot((WebDriver) webDriverAttribute);
        }
    }
}
