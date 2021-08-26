package Tests;



import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.registerPage;
import pageObject.mainPage.mainPage;

public class RegisterTest extends BaseTest {


    @Test(description = "New Account")
    @Description("Creating new account")
    @Severity(SeverityLevel.BLOCKER)
    public void tc01_creatingNewAccount() {
        mainPage mp = new mainPage(driver);
        mp.register();
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Branded", "@gmail.com", "Anna123456");
    }

    @Test(description = "Without email")
    @Description("Creating an account without an email")
    @Severity(SeverityLevel.CRITICAL)
    public void tc02_creatingAccountWithoutEmail() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Branded", "", "Anna123456");
        String expected = "This field is required.";
        String actual = rp.errorEmail();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Without password")
    @Description("Creating account without entering a password")
    @Severity(SeverityLevel.CRITICAL)
    public void tc03_accountWithoutPassword() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Branded", "dennis@gmail.com", "");
        String expected = "This field is required.";
        String actual = rp.passwordError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Without name")
    @Description("Creating accont without a name")
    @Severity(SeverityLevel.NORMAL)
    public void tc04_accountWithoutName() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("", "Branded", "dennis@gmail.com", "");
        String expected = "This field is required.";
        String actual = rp.noNameError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Without organization name")
    @Description("Creating account with no organization name")
    @Severity(SeverityLevel.MINOR)
    public void tc05_accountWithoutOrgName() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "", "dennis@gmail.com", "");
        String expected = "This field is required.";
        String actual = rp.orgNameError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Short password")
    @Description("Creating an account with short passoword")
    @Severity(SeverityLevel.NORMAL)
    public void tc06_EH_shortPassword() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Br", "dennis@gmail.com", "1");
        String expected = "Please enter at least 10 characters.";
        String actual = rp.passwordError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Special requirements")
    @Description("Error handling - creating an account with password that does not match the requirements")
    @Severity(SeverityLevel.NORMAL)
    public void tc07_EH_specialReq() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Br", "dennis@gmail.com", "1234567890");
        String expected = "The password must contain a minimum of one lower case character, one upper case character and one digit.";
        String actual = rp.passwordError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "No terms accepted")
    @Description("Not accepting the terms after filling out the wright info")
    @Severity(SeverityLevel.NORMAL)
    public void tc08_EH_noTermsAccepted() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Br", "dennis@gmail.com", "1234567890");
        String expected = "This field is required.";
        String actual = rp.termsError();
        Assert.assertEquals(actual, expected);
    }
}



