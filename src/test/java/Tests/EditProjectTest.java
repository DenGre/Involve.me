package Tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.loginPage;
import pageObject.mainPage.mainPage;
import pageObject.projects.EditProjectPage;
import pageObject.projects.topBarEditor;
import pageObject.templates.TemplatesPage;
import pageObject.workspaces.ProjectsPage;


public class EditProjectTest extends BaseTest {


    String projectType = "thank you page";
    String projectItemName = "Rating";

    @Test(description = "Logging in")
    @Description("Logging in with valid credentials - the project page should open")
    @Severity(SeverityLevel.BLOCKER)
    public void tc01_login() {
        mainPage mp = new mainPage(driver);
        mp.login();
        loginPage lp = new loginPage(driver);
        lp.logIn(email, password);
        ProjectsPage pp = new ProjectsPage(driver);
        Assert.assertEquals("Workspaces", pp.getTitle());
    }

    @Test(description = "Prep window of the project")
    @Description("The prep window of the project opens up with 3 options")
    @Severity(SeverityLevel.NORMAL)
    public void tc02_isPrepWindowPopped() {
        topBarEditor tb = new topBarEditor(driver);
        tb.clickOnTemplates();
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickAll();
        tp.chooseTemplate("Blank");
        EditProjectPage epp = new EditProjectPage(driver);
        Assert.assertTrue(epp.isNewProjectPrepWindowDisplayeed());
    }

    @Test(description = "Project without a title")
    @Description("Creating project without a title - expect to receive an error")
    @Severity(SeverityLevel.MINOR)
    public void tc03_creatingProjectWithoutATitle() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.editProjectPrep("", projectType);
        Assert.assertEquals(epp.getProjectNameErrorMsg(), "This field is required.");
    }

    @Test(description = "Project with a short title")
    @Description("Creating a project with short title - expect to receive an error")
    @Severity(SeverityLevel.MINOR)
    public void tc04_creatingProjectWithShortTitle() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.editProjectPrep("1", projectType);
        Assert.assertEquals(epp.getProjectNameErrorMsg(), "Please enter at least 3 characters.");
        refreshPage();
    }

    /* Bug - After test 3 or 4 while the user entered not valid text and clicked on the Start Editing button,
    the Start Editing button enters an infinite loop . Not a critical bug since it is by passed in two ways.
    One way - is just press Enter.
    Second way - is refresh the page.
     */
    @Test(description = "creating a project")
    @Description("Creating a project with the name 'test'")
    @Severity(SeverityLevel.NORMAL)
    public void tc05_creatingProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.editProjectPrep("test", projectType);
        Assert.assertEquals(epp.getProjectTitle(), "TEST");
    }

    @Test(description = "Adding and item to the project")
    @Description("Configuring the project while adding items/forms from the list at the right")
    @Severity(SeverityLevel.CRITICAL)
    public void tc06_addingItemToTheProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.addElementToProject(projectItemName);
        Assert.assertEquals(epp.getProjectItemTitle(), projectItemName);
    }

    @Test(description = "Adding slide")
    @Description("Adding slide to the project - should add an additional slide")
    @Severity(SeverityLevel.NORMAL)
    public void tc07_addingSlideToTheProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        int before = epp.getSlidesNumber();
        epp.addSlide();
        int after = epp.getSlidesNumber();
        Assert.assertEquals(before + 1, after);
    }

    @Test(description = "deleting slide")
    @Description("Deleting the last slide")
    @Severity(SeverityLevel.NORMAL)
    public void tc08_deletingTheLastSlide() {
        EditProjectPage epp = new EditProjectPage(driver);
        int before = epp.getSlidesNumber();
        epp.deleteSlide(2);
        int after = epp.getSlidesNumber();
        Assert.assertEquals(before -1, after);
    }

}
