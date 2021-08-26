package Tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.loginPage;
import pageObject.mainPage.mainPage;
import pageObject.projects.topBarEditor;
import pageObject.templates.TemplatesPage;

public class TemplatesTest extends BaseTest {

    /* a Bug was found */
    // In some of the categories the blank template is not counted into the total number of templates

    @Test
    @Description("Testing the quantity of the templates in All category")
    public void tc01_allTestNum() {
        mainPage mp = new mainPage(driver);
        mp.login();
        loginPage lp = new loginPage(driver);
        lp.logIn(email, password);
        topBarEditor tbar = new topBarEditor(driver);
        tbar.clickOnTemplates();
        TemplatesPage tp = new TemplatesPage(driver);
        int blocks = tp.getTemplatesCountDisplayed("all");
        int category = tp.getTemplatesCount();
        Assert.assertEquals(blocks,category);
    }

    @Test
    @Description("Testing the quantity of the templates in quiz category")
    public void tc02_quizTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickQuiz();
        int blocks = tp.getTemplatesCountDisplayed("quiz");
        int category = tp.getTemplatesCount();
        Assert.assertTrue(blocks == category);
    }

    @Test
    @Description("Testing the quantity of the templates in survey category")
    public void tc03_surveyTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickSurvey();
        Assert.assertTrue(tp.getTemplatesCountDisplayed("survey") == tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in calculator category")
    public void tc04_calculatorTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickCalculator();
        Assert.assertTrue(tp.getTemplatesCountDisplayed("calculator") == tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in form category")
    public void tc05_formTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickForm();
        Assert.assertTrue(tp.getTemplatesCountDisplayed("form") == tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in payment form category")
    public void tc06_paymentFormTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickPaymentForm();
        Assert.assertTrue(tp.getTemplatesCountDisplayed("payment form") == tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in lead page category")
    public void tc07_leadPageTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickLeadPage();
        Assert.assertTrue(tp.getTemplatesCountDisplayed("lead page") == tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in promotion category")
    public void tc08_promotionTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickPromotion();
        Assert.assertTrue(tp.getTemplatesCountDisplayed("promotion") == tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in personality category")
    public void tc09_personalityTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickPersonalityTest();
        Assert.assertTrue(tp.getTemplatesCountDisplayed("personality test") == tp.getTemplatesCount());
    }


    /* and so on with all other categories that left */

    @Test
    @Description("choosing the template from the list ")
    public void tc10_chooseATemplate(){
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickAll();
        tp.chooseTemplate("Remote Work Feedback Survey");

    }
}


