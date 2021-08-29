package utils;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureAttachment {

    @Attachment(value = "{0}", type = "text/plain")
    public static String attachText(String message) {
        return message;
    }

    @Attachment(value = "Page Screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
