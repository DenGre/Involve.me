package pageObject.workspaces;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.basePage.basePage;

import java.util.List;

public class ProjectsPage extends basePage {

    /* Creating a workspace */
    @FindBy(css = ".justify-between > button")
    private WebElement addWrkSpcBtn;
    @FindBy(css = " div> .h-12")
    private WebElement wrkSpaceField;
    @FindBy(css = "#confirm-create-button")
    private WebElement confirmBtn;
    @FindBy(css = ".ml-auto > .inline-block")
    private WebElement cancelBtn;

    /* adding team members to the workspace */
    @FindBy(css = "[title='Invite team member to workspace']")
    private WebElement addTeamMembrsBtn;
    @FindBy(css = "#user-email")
    private WebElement emailField;
    @FindBy(css = ".relative.h-8.mr-1")
    private WebElement inviteUserBtn;
    @FindBy(css = ".ml-2.text-red-600")
    private WebElement seatsError;

    /* Creating your project button */
    @FindBy(css = ".block.max-w-xl")
    private WebElement startBtn;


    /* Editing the WorkSpace */
    @FindBy(css = "[data-icon='chevron-down']")
    private WebElement workspaceEditBtn; // Opening the dropdown of the chosen workspace
    @FindBy(css = ".mr-3 .hover\\:bg-gray-600")
    private WebElement renameWorkspaceBtn;
    @FindBy(css = ".h-12.w-full")
    private WebElement editWorkSpaceName;
    @FindBy(css = "#confirm-create-button")
    private WebElement confirmRenameBtn; //also as the delete button
    @FindBy(css = ".ml-auto [ type=\"button\"]")
    private WebElement cancelRenameBtn;
    @FindBy(css = ".mr-3 .text-red-600")
    private WebElement deleteWorkspaceBtn;
    @FindBy(css = ".h-12")
    private WebElement deleteWorkspaceField;
    @FindBy(css = "[data-icon=\"search\"]")
    private WebElement searchBtn;
    @FindBy(css = "[type='text']")
    private WebElement searchField;
    @FindBy(css = ".form-select")
    private WebElement sortingDrpDwn;
    @FindBy(css = "[aria-labelledby=\"arrow-down\"]")
    private List<WebElement> dropdown_delete;
    @FindBy(css = " div > ul > li:nth-child(10)")
    private WebElement deleteProject;
    @FindBy(css = "div > h1.text-gray-900")
    private WebElement workspaceTitle;

    @FindBy(css = ".justify-right button svg")
    private WebElement dropDownBtnProjects;
    @FindBy(css = ".dropdown-menu li:nth-child(10)")
    private WebElement deletingProject;


    @FindBy(css = "#confirm-delete-button")
    private WebElement confirmDeleteProjectBtn; // Project deletion button
    @FindBy(css = "#app .max-w-full div .mt-4 > .mt-8 > div")
    private List<WebElement> projectsBlocks;

    @FindBy(css = ".mt-6 a")
    private List<WebElement> listOfTheWorkspaces;
    @FindBy(css = "#app .max-w-full div .mt-4 > .mt-8 > div")
    private List<WebElement> listOfProjects;
    @FindBy(css = "h1 a")
    private List<WebElement> listProjectsTitles;

    @FindBy(css = ".font-medium.px-5 > span")
    private WebElement workSpaceValid;

    /* Creating new project */
    @FindBy(css = "[href='/project/choose?workspace_uuid=bcc3e366-0c10-46a3-b4a4-9915f4f5410e']")
    private WebElement createProjectBtn;

    @FindBy(css = ".flex-no-wrap a")
    private List<WebElement> tabs;

    @FindBy(css = "h1.block")
    private WebElement noProjectFoundMsg;
    @FindBy(css = "#app-layout")
    private WebElement body;

    /* Constructor */
    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    //Functional testing of the workspace section
    /* Adding new workspace */
    public void addWrksSpace(String text) {
        click(addWrkSpcBtn);
        fillText(wrkSpaceField, text);
        click(confirmBtn);
        sleep(1);

    }

    /* Adding team members to the workspace. */
    @Step("Adding team members with the email {emailMember")
    public void addTeamMembers(String emailMember) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(addTeamMembrsBtn));
        click(addTeamMembrsBtn);
        fillText(emailField, emailMember);
        click(inviteUserBtn);
        sleep(2);
        click(body);
    }

    public void addTeamMembersWithoutInvite(String emailMember) {
        wait.until(ExpectedConditions.elementToBeClickable(addTeamMembrsBtn));
        click(addTeamMembrsBtn);
        fillText(emailField, emailMember);
        click(inviteUserBtn);
        sleep(2);
    }


    /* ERROR - You have used all your available seats */
    public String seatsError() {
        return getText(seatsError);
    }

    /* Deleting workspace */
    public void deleteWorkspaceQuick() {
        click(workspaceEditBtn);
        click(deleteWorkspaceBtn);
        fillText(searchField, getText(workspaceTitle));
        click(confirmRenameBtn);
    }

    /* Renaming workspace */
    @Step("Renaming the workspace to {text}")
    public void renameWorkSpace(String text) {
        refreshPage();
        click(workspaceEditBtn);
        click(renameWorkspaceBtn);
        fillText(editWorkSpaceName, text);
        click(confirmRenameBtn);
    }

    /* Create project button */
    public void createProject() {
        click(createProjectBtn);
    }

    /* Search for project */
    @Step("Search for project {text}")
    public void searchPrjct(String text) {
        click(searchBtn);
        fillText(searchField, text);
    }


    public String getWorkspaceTitle() {
        return getText(workspaceTitle);
    }

    /* Choosing a specific workspace */
    @Step("Choosing a workspace - {workspaceName}")
    public void searchForWorkspace(String workspaceName) {
        refreshPage();
        for (WebElement workspace : listOfTheWorkspaces
        ) {
            if (getText(workspace).contains(workspaceName)) {
                click(workspace);
                break;
            }
        }
    }

    /*Deleting the chosen workspace */
    @Step("Deleting workspace - {name}")
    public void deleteWorkspace(String name) {
        for (WebElement workspace :
                listOfTheWorkspaces) {
            if (getText(workspace).contains(name)) {
                deleteWorkspaceQuick();
                break;
            }
        }
    }

    /* Selecting from the tabs - all, draft, publiched etc. templates of the chosen project */
    @Step("Selecting the tab {tabName}")
    public void selectTab(String tabName) {
        for (WebElement tab : tabs) {
            if (getText(tab).contains(tabName)) {
                click(tab);
                break;
            }
        }
    }

    /* The size of the workspaces */
    public int getWorkspacesNumber() {
        List<WebElement> workspaces = listOfTheWorkspaces;
        return workspaces.size();
    }

    /* The count of all projects of the chosen workspace */
    public int getProjectsNumber() {
        List<WebElement> projects = listOfProjects;
        return projects.size();

    }

    /* Searching for a non existed project inside the chosen workspace - No project matches the criteria. */
    public String noProjectFound() {
        return getText(noProjectFoundMsg);
    }

    /* Deleting project */
    @Step("deleting project - {projectName")
    public void deleteProject(String projectName) {
        List<WebElement> projects = projectsBlocks;
        for (WebElement project : projects) {
            if (getText(project).contains(projectName)) {
                clickDropDownBtn(project);
                click(project.findElement(By.cssSelector(".dropdown-menu li:nth-child(10)")));
                break;
            }
        }
        click(confirmDeleteProjectBtn);
        sleep(5);

    }
    /* Clicking on the dropdown of the chose project - to edit */
    private void clickDropDownBtn(WebElement project) {
        WebElement dropDownBtn = project.findElement(By.cssSelector(".justify-right button svg"));
        click(dropDownBtn);
    }

    public String getTitle() {
        return getText(workSpaceValid);
    }
}







