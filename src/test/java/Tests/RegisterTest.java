package Tests;



import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.registerPage;
import pageObject.mainPage.mainPage;

public class RegisterTest extends BaseTest {


    @Test(description = "Creating new Account")
    public void tc01_creatingNewAccount() {
        mainPage mp = new mainPage(driver);
        mp.register();
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Branded", "@gmail.com", "Anna123456");
    }

    @Test(description = "creating account without email")
    public void tc02_creatingAccountWithoutEmail() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Branded", "", "Anna123456");
        String expected = "This field is required.";
        String actual = rp.errorEmail();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "creating account without password")
    public void tc03_accountWithoutPassword() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Branded", "dennis@gmail.com", "");
        String expected = "This field is required.";
        String actual = rp.passwordError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "creating account without name")
    public void tc04_accountWithoutName() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("", "Branded", "dennis@gmail.com", "");
        String expected = "This field is required.";
        String actual = rp.noNameError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "creating account without organization name")
    public void tc05_accountWithoutOrgName() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "", "dennis@gmail.com", "");
        String expected = "This field is required.";
        String actual = rp.orgNameError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "creating account - short password")
    public void tc06_EH_shortPassword() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Br", "dennis@gmail.com", "1");
        String expected = "Please enter at least 10 characters.";
        String actual = rp.passwordError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "creating account - special requirements")
    public void tc07_EH_specialReq() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Br", "dennis@gmail.com", "1234567890");
        String expected = "The password must contain a minimum of one lower case character, one upper case character and one digit.";
        String actual = rp.passwordError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "creating account - no terms accepted")
    public void tc08_EH_noTermsAccepted() {
        registerPage rp = new registerPage(driver);
        rp.registerAccount("Dennis", "Br", "dennis@gmail.com", "1234567890");
        String expected = "This field is required.";
        String actual = rp.termsError();
        Assert.assertEquals(actual, expected);
    }
}



