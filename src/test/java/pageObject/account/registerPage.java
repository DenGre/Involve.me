package pageObject.account;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.basePage.basePage;

public class registerPage extends basePage {
    JavascriptExecutor executor;

    /* Elements */

    @FindBy(css = "#user-name")
    private WebElement nameField; // personal name field
    @FindBy(css = "#organization-name")
    private WebElement organizationField; // organization name field
    @FindBy(css = "[type='email']")
    private WebElement emailField;
    @FindBy(css = "#register-password")
    private WebElement passwordField;
    @FindBy(css = "[for='terms']")
    private WebElement termsCheckBox; // accept terms check box
    @FindBy(css = ".btn-lg")
    private WebElement registerBtn;
    @FindBy(css = "#info-url")
    private WebElement availability;
    @FindBy(css = "#user-name-error")
    private WebElement emptyNameField;
    @FindBy(css = ".e-form-heading")
    private WebElement pageTitle;
    @FindBy(css = "#email-error")
    private WebElement emailError;
    @FindBy(css = "#register-password-error")
    private WebElement noPasswordError;
    @FindBy(css = "#organization-name-error")
    private WebElement orgNameError;
    @FindBy(css = "#terms-error")
    private WebElement termsError;


    /* Constructor */
    public registerPage(WebDriver driver) {
        super(driver);
    }

    /* Registering an account */
    @Step("Registering the account with name {name}, organization {organization},email {email},password {password}")
    public void registerAccount(String name, String organization, String email, String password) {
        fillText(nameField, name);
        fillText(organizationField, organization);
        fillText(emailField, email);
        fillText(passwordField, password);
        executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", termsCheckBox);

        click(registerBtn);
        /*For the test to be completed there should be added a Sleep option so the captcha option would be done manually.
        Or on production it should be canceled for the test.*/
    }

    /* Error message while registering without email and/or password */
    @Step("Get failure message ")
    public String errorMSG() {
        return getText(emptyNameField);
    }

    public String getTitle() {
        return getText(pageTitle);
    }

    /* Non valid email */
    @Step("Get failure message ")
    public String errorEmail() {
        return getText(emailError);

    }
    @Step("Get failure message - does not meet the requirements  ")
    /* Password that does not fit the requirements */
    public String passwordError() {
        return getText(noPasswordError);
    }

    @Step("Get failure message - does not meet the requirements  ")
    /* Name error - do not fit the requirements */
    public String noNameError() {
        return getText(emptyNameField);
    }

    @Step("Get failure message - does not meet the requirements ")
    /* Error with organization name */
    public String orgNameError() {
        return getText(orgNameError);
    }

    @Step("Get failure message - accept the terms  ")
    /* Terms not accepted */
    public String termsError() {
        return getText(termsError);
    }


}
