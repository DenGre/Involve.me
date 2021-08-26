package Tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.loginPage;
import pageObject.mainPage.mainPage;
import pageObject.projects.topBarEditor;

public class LoginTest extends BaseTest {

    @Test(description = "logging in with valid credentials and log out")
    public void tc01_login() {
        mainPage mp = new mainPage(driver);
        mp.login();
        loginPage lp = new loginPage(driver);
        lp.logIn(email, password);
        topBarEditor tp = new topBarEditor(driver);
        tp.logOut();
        String expected = "Log in";
        String actual = lp.validation();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "logging in with non valid password")
    public void tc02_EH_nonvalidPassword() {
        loginPage lp = new loginPage(driver);
        lp.logIn(email, "123456789A");
        String actual = "These credentials do not match our records.";
        String expected = lp.errorMessage();
        Assert.assertEquals(actual, expected);


    }

}
