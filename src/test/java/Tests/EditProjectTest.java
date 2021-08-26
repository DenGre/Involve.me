package Tests;

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

    @Test
    public void tc01_login() {
        mainPage mp = new mainPage(driver);
        mp.login();
        loginPage lp = new loginPage(driver);
        lp.logIn(email, password);
        ProjectsPage pp = new ProjectsPage(driver);
        Assert.assertEquals("Workspaces", pp.getTitle());
    }

    @Test
    public void tc02_isPrepWindowPopped() {
        topBarEditor tb = new topBarEditor(driver);
        tb.clickOnTemplates();
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickAll();
        tp.chooseTemplate("Blank");
        EditProjectPage epp = new EditProjectPage(driver);
        Assert.assertTrue(epp.isNewProjectPrepWindowDisplayeed());
    }

    @Test
    public void tc03_creatingProjectWithoutATitle() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.editProjectPrep("", projectType);
        Assert.assertEquals(epp.getProjectNameErrorMsg(), "This field is required.");
    }

    @Test
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
    @Test
    public void tc05_creatingProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.editProjectPrep("test", projectType);
        Assert.assertEquals(epp.getProjectTitle(), "TEST");
    }

    @Test
    public void tc06_addingItemToTheProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.addElementToProject(projectItemName);
        Assert.assertEquals(epp.getProjectItemTitle(), projectItemName);
    }

    @Test
    public void tc07_addingSlideToTheProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        int before = epp.getSlidesNumber();
        epp.addSlide();
        int after = epp.getSlidesNumber();
        Assert.assertEquals(before + 1, after);
    }

    @Test
    public void tc08_deletingTheLastSlide() {
        EditProjectPage epp = new EditProjectPage(driver);
        int before = epp.getSlidesNumber();
        epp.deleteSlide(2);
        int after = epp.getSlidesNumber();
        Assert.assertEquals(before -1, after);
    }

}
