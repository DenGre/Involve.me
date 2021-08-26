package Tests;


import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.loginPage;
import pageObject.mainPage.mainPage;
import pageObject.projects.topBarEditor;

public class LoginTest extends BaseTest {

    @Test(description = "Logging in")
    @Description("Log in with a valid username and a valid password")
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
        //change validation
    }

    @Test(description = "Invalid log in")
    @Description("Error handling - Logging in with non valid password")
    public void tc02_EH_nonvalidPassword() {
        loginPage lp = new loginPage(driver);
        lp.logIn(email, "123456789A");
        String actual = "These credentials do not match our records.";
        String expected = lp.errorMessage();
        Assert.assertEquals(actual, expected);


    }
//    add log out test

}
