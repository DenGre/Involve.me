package pageObject.projects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.basePage.basePage;

import java.util.List;

public class EditProjectPage extends basePage {

    /* First edit page (Choose project type) */
    @FindBy(css = "input#project-name")
    private WebElement projectNameField;
    @FindBy(css = "[for='select-single-outcome']")
    private WebElement thankYouPageOpt;
    @FindBy(css = "[for='select-highscore-outcomes']")
    private WebElement answrBasedOutcomes;
    @FindBy(css = "[for='select-ranged-outcomes']")
    private WebElement scoredBasedOutcomes;
    @FindBy(css = "#project-name-error")
    private WebElement prjctErrorMSG;
    @FindBy(css = ".swal-button.swal-button--confirm")
    private WebElement savePageTitleNewName;
    @FindBy(css = ".new-project-modal")
    private WebElement newProjectPopUp;
    @FindBy(css = "#project-name-error")
    private WebElement projectNameErrorMsg;

    @FindBy(css = "div.content-menu-item")
    private List<WebElement> contentList;
    @FindBy(css = "div.content-item-wrapper")
    private WebElement dropZone;
    @FindBy(css = "button.v-remove")
    private WebElement contentItemClsBtn;
    @FindBy(css = ".swal-button--confirm.swal-button--danger")
    private WebElement confrimDeleteBtn;
    @FindBy(css = ".swal-button--cancel")
    private WebElement canceDltBtn;
    @FindBy(css = ".e-close.nav-link")
    private WebElement saveAndExitBtn;
    @FindBy(css = "div.add-page-container")
    private WebElement addPageContainer;
    //page edit
    @FindBy(css = "[title='Duplicate page']")
    private WebElement duplicatePageBtn;
    @FindBy(css = "[title='Page properties']")
    private WebElement pagePropertiesBtn;
    @FindBy(css = "#project-title")
    private WebElement projecTitleField;
    @FindBy(css = ".content-item-edit-container > div > h2")
    private WebElement contentItemTitle;
    @FindBy(css = ".e-page-item.current .add-page-container button")
    private WebElement addNewSlideBtn;
    @FindBy(css = ".e-pages-container .e-page-management")
    private List<WebElement> slidesList;
    @FindBy(css = "[title='Delete page']")
    private List<WebElement> deleteSlideBtns;
    @FindBy(css = ".swal-button--confirm.swal-button--danger")
    private WebElement confirmDeleteSlideBtn;

    //TopBar
    @FindBy(css = ".project-name")
    private WebElement projectName;


    /* Constructor */
    public EditProjectPage(WebDriver driver) {
        super(driver);
    }

    public String getTempName() {
        return getText(projectNameField);
    }

    @Step("Choosing an option from the prep window with the name {projectName} and type {projectType")
    public void editProjectPrep(String projectName, String projectType) {
        fillText(projectNameField, projectName);
        switch (projectType) {
            case "thank you page":
                click(thankYouPageOpt);
                click(driver.findElement(By.cssSelector("div:nth-child(1) > label > div > button")));
                break;
            case "outcome pages":
                click(answrBasedOutcomes);
                click(driver.findElement(By.cssSelector("div:nth-child(2) > label > div > button")));
                break;
            default:
                click(scoredBasedOutcomes);
                click(driver.findElement(By.cssSelector("div:nth-child(3) > div > button")));
                break;
        }

    }

    @Step("Adding element to the project - {element}")
    public void addElementToProject(String element) {
        int counter = 0;
        for (WebElement content : contentList) {
            if (getText(content).contains(element)) {
                if (counter == 0) {
                    dragAndDrop(content, dropZone);
                    counter++;
                } else {
                    dragAndDrop(content, (dropZone.findElements(By.cssSelector(".content-item")).get(0)));
                }

                break;
            }
        }
        sleep(2);
        click(contentItemClsBtn);
        confirmDeleteItem();

    }

    public void confirmDeleteItem() {
        click(confrimDeleteBtn);
    }

    public void changePropertiesPage(String title) {
        click(pagePropertiesBtn);
        fillText(projecTitleField, "Hello");
        click(savePageTitleNewName);
    }

    public String getProjectTitle() {
        wait.until(ExpectedConditions.visibilityOf(projectName));
        return getText(projectName);
    }

    public boolean isNewProjectPrepWindowDisplayeed() {
        return isElementDisplayed(newProjectPopUp);
    }

    // returns the error message that is related to a new project's name
    public String getProjectNameErrorMsg() {
        return getText(projectNameErrorMsg);
    }

    public String getProjectItemTitle() {
        return getText(contentItemTitle);
    }

    public int getSlidesNumber() {
        sleep(2);
        List<WebElement> slides = slidesList;
        return slides.size();
    }

    public void addSlide() {
        click(addNewSlideBtn);
    }

    public void deleteSlide(int slideNumber) {
        // will delete slide according to its given number
        click(deleteSlideBtns.get(slideNumber - 1));
        click(confirmDeleteSlideBtn);
    }
}
