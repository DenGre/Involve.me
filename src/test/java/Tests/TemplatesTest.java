package Tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.loginPage;
import pageObject.mainPage.mainPage;
import pageObject.projects.topBarEditor;
import pageObject.templates.TemplatesPage;
import utils.AllureAttachment;
import utils.ListenerJava;

public class TemplatesTest extends BaseTest {

    /* a Bug was found */
    // In some of the categories the blank template is not counted into the total number of templates

    @Test
    @Description("Testing the quantity of the templates in All category")
    @Severity(SeverityLevel.MINOR)
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
    @Severity(SeverityLevel.MINOR)
    public void tc02_quizTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickQuiz();
        int blocks = tp.getTemplatesCountDisplayed("quiz");
        int category = tp.getTemplatesCount();
        Assert.assertEquals(blocks,category);
    }

    @Test
    @Description("Testing the quantity of the templates in survey category")
    @Severity(SeverityLevel.MINOR)
    public void tc03_surveyTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickSurvey();
        Assert.assertEquals(tp.getTemplatesCountDisplayed("survey"), tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in calculator category")
    @Severity(SeverityLevel.MINOR)
    public void tc04_calculatorTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickCalculator();
        Assert.assertEquals(tp.getTemplatesCountDisplayed("calculator"),tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in form category")
    @Severity(SeverityLevel.MINOR)
    public void tc05_formTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickForm();
        Assert.assertEquals(tp.getTemplatesCountDisplayed("form"),tp.getTemplatesCount());
        AllureAttachment.attachScreenshot(driver);
    }

    @Test
    @Description("Testing the quantity of the templates in payment form category")
    @Severity(SeverityLevel.MINOR)
    public void tc06_paymentFormTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickPaymentForm();
        Assert.assertEquals(tp.getTemplatesCountDisplayed("payment form"), tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in lead page category")
    @Severity(SeverityLevel.MINOR)
    public void tc07_leadPageTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickLeadPage();
        Assert.assertEquals(tp.getTemplatesCountDisplayed("lead page"),tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in promotion category")
    @Severity(SeverityLevel.MINOR)
    public void tc08_promotionTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickPromotion();
        Assert.assertEquals(tp.getTemplatesCountDisplayed("promotion"),tp.getTemplatesCount());
    }

    @Test
    @Description("Testing the quantity of the templates in personality category")
    @Severity(SeverityLevel.MINOR)
    public void tc09_personalityTestNum() {
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickPersonalityTest();
        Assert.assertEquals(tp.getTemplatesCountDisplayed("personality test"),tp.getTemplatesCount());
    }


    /* and so on with all other categories that left */

    @Test
    @Description("choosing the template from the list ")
    @Severity(SeverityLevel.CRITICAL)
    public void tc10_chooseATemplate(){
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickAll();
        tp.chooseTemplate("Remote Work Feedback Survey");

    }
}


