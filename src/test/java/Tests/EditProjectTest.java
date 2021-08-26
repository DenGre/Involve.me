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

    @Test(description = "Logging in")
    public void tc01_login() {
        mainPage mp = new mainPage(driver);
        mp.login();
        loginPage lp = new loginPage(driver);
        lp.logIn(email, password);
        ProjectsPage pp = new ProjectsPage(driver);
        Assert.assertEquals("Workspaces", pp.getTitle());
    }

    @Test(description = "Verifying the prep window is opened")
    public void tc02_isPrepWindowPopped() {
        topBarEditor tb = new topBarEditor(driver);
        tb.clickOnTemplates();
        TemplatesPage tp = new TemplatesPage(driver);
        tp.clickAll();
        tp.chooseTemplate("Blank");
        EditProjectPage epp = new EditProjectPage(driver);
        Assert.assertTrue(epp.isNewProjectPrepWindowDisplayeed());
    }

    @Test(description = "Error Handling - creagin a project without a title")
    public void tc03_creatingProjectWithoutATitle() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.editProjectPrep("", projectType);
        Assert.assertEquals(epp.getProjectNameErrorMsg(), "This field is required.");
    }

    @Test(description = "Error Handling - creating a project with a short title")
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
    public void tc05_creatingProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.editProjectPrep("test", projectType);
        Assert.assertEquals(epp.getProjectTitle(), "TEST");
    }

    @Test(description = "adding and item from the list to the project")
    public void tc06_addingItemToTheProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        epp.addElementToProject(projectItemName);
        Assert.assertEquals(epp.getProjectItemTitle(), projectItemName);
    }

    @Test(description = "adding slides to the project at the bottom")
    public void tc07_addingSlideToTheProject() {
        EditProjectPage epp = new EditProjectPage(driver);
        int before = epp.getSlidesNumber();
        epp.addSlide();
        int after = epp.getSlidesNumber();
        Assert.assertEquals(before + 1, after);
    }

    @Test(description = "deleting the last slide")
    public void tc08_deletingTheLastSlide() {
        EditProjectPage epp = new EditProjectPage(driver);
        int before = epp.getSlidesNumber();
        epp.deleteSlide(2);
        int after = epp.getSlidesNumber();
        Assert.assertEquals(before -1, after);
    }

}
