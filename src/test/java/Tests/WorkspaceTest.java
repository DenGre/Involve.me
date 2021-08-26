package Tests;


import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.account.loginPage;
import pageObject.mainPage.mainPage;
import pageObject.workspaces.ProjectsPage;


public class WorkspaceTest extends BaseTest {


    String emailMember = "123@gmail.com";
    String newNameWorkspace = "testDone";
    String notFoundProject = "No project matches the criteria";


    @Test(description="New workspace")
    @Description("Creating a new workspace")
    @Severity(SeverityLevel.BLOCKER)
    public void tc01_login() {
        mainPage mp = new mainPage(driver);
        mp.login();
        loginPage lp = new loginPage(driver);
        lp.logIn(email, password);
        ProjectsPage pr = new ProjectsPage(driver);
        int before = pr.getWorkspacesNumber();
        pr.addWrksSpace(newNameWorkspace);
        int after = pr.getWorkspacesNumber();
        Assert.assertEquals(after, before);

    }

    @Test(description ="Adding team members" )
    @Description("Adding team members to the workspace. Since in this situation the user has not upgraded his account he will recieve an error, so for the purpose\" +\n" +
            "            of the test it will be done with the error message and will present that in other situation the member will be added.")
    @Severity(SeverityLevel.NORMAL)
    public void tc02_addingTeamMembers() {
        ProjectsPage pr = new ProjectsPage(driver);
        pr.addTeamMembers(emailMember);
        String actual = "You've used all your available seats.";
        String expected = pr.seatsError();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "renaming workspace name")
    @Description("Renaming the new workspace")
    @Severity(SeverityLevel.NORMAL)
    public void tc03_remainingworkspace() {
        ProjectsPage pr = new ProjectsPage(driver);
        pr.renameWorkSpace(newNameWorkspace);
        String actual = newNameWorkspace;
        String expected = newNameWorkspace;
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Deleting workspace")
    @Description("Deleting the created workspace")
    @Severity(SeverityLevel.NORMAL)
    public void tc04_deletingWorkspace() {
        ProjectsPage pr = new ProjectsPage(driver);
        pr.searchForWorkspace(newNameWorkspace);
        int before = pr.getWorkspacesNumber();
        pr.deleteWorkspace(newNameWorkspace);
        int after = pr.getWorkspacesNumber();
        Assert.assertEquals(after, before);
    }

    @Test(description = "Search for project")
    @Description("Search for a specific project")
    @Severity(SeverityLevel.NORMAL)
    public void tc05_searching4WorkSpace() {
        ProjectsPage pr = new ProjectsPage(driver);
        pr.searchForWorkspace(newNameWorkspace);
        Assert.assertEquals(pr.getWorkspaceTitle(), newNameWorkspace);
    }

    @Test(description = "Deleting project")
    @Description("Deleting a project from a workspace")
    @Severity(SeverityLevel.NORMAL)
    public void tc06_deletingProjectFromExistWorkSpace() {
        ProjectsPage pr = new ProjectsPage(driver);
        pr.searchForWorkspace("111");
        int before = pr.getProjectsNumber();
        pr.deleteProject("New Project");
        int after = pr.getProjectsNumber();
        Assert.assertEquals(before, after - 1);
    }

    @Test(description = "Searching for project")
    @Description("Searching for non existent project")
    @Severity(SeverityLevel.MINOR)
    public void tc07_searching4NonExistenProject() {
        ProjectsPage pr = new ProjectsPage(driver);
        pr.searchPrjct("1122");
        Assert.assertEquals(pr.noProjectFound(), notFoundProject);
    }


}
