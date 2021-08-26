package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.forgotPasswordPage;
import pageObject.account.loginPage;
import pageObject.mainPage.mainPage;

public class ForgotPasswordTest extends BaseTest {


    @Test(description = "recovering accounts password")
    public void tc01() {
        mainPage mp = new mainPage(driver);
        mp.login();
        loginPage lp = new loginPage(driver);
        lp.forgotPassword();
        forgotPasswordPage fp = new forgotPasswordPage(driver);
        fp.resetPassword("dhryhoryev@gmail.com");
        String actual = "A reset link has been sent to the email address, if it has been used to register for an account.";
        String expected = fp.successMsg();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "recovering email that not in the database")
    public void tc02() {
        forgotPasswordPage fp = new forgotPasswordPage(driver);
        fp.resetPassword("1@gmail.com");
        String actual = "We can't find a user with that e-mail address.";
        String expected = fp.getErrorMsg();
        Assert.assertEquals(actual, expected);
    }

}

