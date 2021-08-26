package pageObject.account;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.basePage.basePage;

public class loginPage extends basePage {

    /*Elements*/
    @FindBy(css = "[name='email']")
    private WebElement emailField;
    @FindBy(css = "[name=\"password\"]")
    private WebElement passwordField;
    @FindBy(css = ".btn-primary.btn-lg")
    private WebElement loginBtn;
    @FindBy(css = " div:nth-child(6)  a")
    private WebElement forgotPasswordBtn;
    @FindBy(css = " div:nth-child(2)  > p >  a")
    private WebElement creatAccountBtn;
    @FindBy(css = ".alert.alert-danger")
    private WebElement errorMessage;
    @FindBy(css = ".e-form-heading")
    private WebElement validation;

    /* Constructor */
    public loginPage(WebDriver driver) {
        super(driver);
    }

    /* Login */
    @Step("Logging in with email {email} and password {password}")
    public void logIn(String email, String password) {
        fillText(emailField, email);
        fillText(passwordField, password);
        loginBtn.click();
    }

    /* Error message while entering wrong email and password */
    @Step("Get error message")
    public String errorMessage() {
        return getText(errorMessage);
    }

    /* Redirects to the recovering password for email section */
    public void forgotPassword() {
        click(forgotPasswordBtn);
    }
    /* Validation that the user logged in */
    public String validation() {
        return getText(validation);
    }

}
