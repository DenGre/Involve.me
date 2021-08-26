package pageObject.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.basePage.basePage;

public class forgotPasswordPage extends basePage {

    @FindBy(css = ".form-control")
    private WebElement emailField;
    @FindBy(css = ".btn-primary")
    private WebElement resetLink;
    @FindBy(css = ".alert-danger")
    private WebElement errormsg;
    @FindBy(css = ".alert-success")
    private WebElement emailSentConfirmation;

    public forgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    /* reseting the password for desired email */
    public void resetPassword(String email) {
        fillText(emailField, email);
        click(resetLink);
    }

    /* Error message while recovering email that not in the data base */
    public String getErrorMsg() {
        return getText(errormsg);
    }

    /* Confirmation that email with instructions to reset or change the password sent to the email */
    public String successMsg() {
        return getText(emailSentConfirmation);
    }
}
