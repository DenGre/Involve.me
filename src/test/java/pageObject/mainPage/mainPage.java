package pageObject.mainPage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.basePage.basePage;

public class mainPage extends basePage {

    /* The elements */
    @FindBy(css = ".other-link.login")
    WebElement loginBtn;
    @FindBy(css = ".other-link.register")
    private WebElement registerBtn;
    @FindBy(css = ".text-column.col-md-7.col-sm-12.col-xs-12  [name=email]")
    private WebElement topGetStartedFreeField;
    @FindBy(css = "#email-filled-button-001")
    private WebElement topGetStartedBtn;

    /*Constructor */

    public mainPage(WebDriver driver) {
        super(driver);
    }

    /* Clicking on the Login button at the top */
    public void login() {
        loginBtn.click();
    }

    /* Clicking on the register button */
    public void register() {
        registerBtn.click();
    }

    /* Quick register from the mainpage */
    @Step("Quick register from the main page with text {text}")
    public void getStartedFreeTop(String text) {
        topGetStartedFreeField.sendKeys(text);
        topGetStartedBtn.click();
    }

}
