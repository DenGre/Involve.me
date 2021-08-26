package pageObject.templates;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.basePage.basePage;

import java.util.List;

public class TemplatesPage extends basePage {

    /* Locating all the neede elements */

    @FindBy(css = "a#filter-all")
    private WebElement allCategory;
    @FindBy(css = "a#filter-all span")
    private WebElement allCatNumber;
    @FindBy(css = "#filter-quiz")
    private WebElement quizCategory;
    @FindBy(css = "#filter-quiz span")
    private WebElement quizCatNumber;
    @FindBy(css = "#filter-survey")
    private WebElement surveyCategory;
    @FindBy(css = "#filter-survey span")
    private WebElement surveyCatNumber;
    @FindBy(css = "#filter-calculator")
    private WebElement calculatorCategory;
    @FindBy(css = "#filter-calculator span")
    private WebElement calculatorCatNumber;
    @FindBy(css = "#filter-form")
    private WebElement formCategory;
    @FindBy(css = "#filter-form span")
    private WebElement formCatNumber;
    @FindBy(css = "#filter-payment")
    private WebElement paymentCategory;
    @FindBy(css = "#filter-payment span")
    private WebElement paymentCatNumber;
    @FindBy(css = "#filter-leadpage")
    private WebElement leadPageCategory;
    @FindBy(css = "#filter-leadpage span")
    private WebElement leadPageCatNumber;
    @FindBy(css = "#filter-promotion")
    private WebElement promotionCategory;
    @FindBy(css = "#filter-promotion span")
    private WebElement promotionCatNumber;
    @FindBy(css = "#filter-personality_test")
    private WebElement personalityTestCategory;

    @FindBy(css = "#filter-personality_test span")
    private WebElement personalityTestCatNumber;
    @FindBy(css = "#template-gallery tbody tr")
    private List<WebElement> templatesBlocks;
    @FindBy(css = "#template-gallery")
    private WebElement templatesCount;

    // Constructor
    public TemplatesPage(WebDriver driver) {
        super(driver);
    }

    /* Clicking on the categories */
    public void clickAll() {
        click(allCategory);
    }

    public void clickQuiz() {
        click(quizCategory);
    }

    public void clickSurvey() {
        click(surveyCategory);
    }

    public void clickCalculator() {
        click(calculatorCategory);
    }

    public void clickForm() {
        click(formCategory);
    }

    public void clickPaymentForm() {
        click(paymentCategory);
    }

    public void clickLeadPage() {
        click(leadPageCategory);
    }

    public void clickPromotion() {
        click(promotionCategory);
    }

    public void clickPersonalityTest() {
        click(personalityTestCategory);
    }

    /* Choosing the chosen template */
    @Step("Choosing a template from the list with the name - {templateName")
    public void chooseTemplate(String templateName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#template-gallery")));
        List<WebElement> templates = templatesBlocks;
        for (WebElement template : templates) {
            if (getText(template).contains(templateName)) {
                WebElement btn = template.findElement(By.cssSelector("a .btn.btn-primary"));
                moveToElement(btn);
                sleep(2);
                click(btn);
                break;
            }
        }
    }

    /* Previewing the chosen template */
    @Step("Previewing the chose template with the name = {templateName")
    public void previewTemplate(String templateName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#template-gallery")));
        List<WebElement> templates = templatesBlocks;
        for (WebElement template : templates) {
            if (getText(template).contains(templateName)) {
                WebElement btn = template.findElement(By.cssSelector(".btn-preview"));
                moveToElement(btn);
                click(btn);
                break;
            }
        }
    }

    /* Counting the templates items inside the chosen category */
    @Step("Returns the quantity of the templates in the category")
    public int getTemplatesCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#template-gallery")));
        return templatesBlocks.size();
    }

    public int getTemplatesCountDisplayed(String catName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#template-gallery")));
        switch (catName) {
            case "all":
                return Integer.parseInt(getText(allCatNumber));
            case "quiz":
                return Integer.parseInt(getText(quizCatNumber));
            case "survey":
                return Integer.parseInt(getText(surveyCatNumber));
            case "calculator":
                return Integer.parseInt(getText(calculatorCatNumber));
            case "form":
                return Integer.parseInt(getText(formCatNumber));
            case "payment form":
                return Integer.parseInt(getText(paymentCatNumber));
            case "lead page":
                return Integer.parseInt(getText(leadPageCatNumber));
            case "promotion":
                return Integer.parseInt(getText(promotionCatNumber));
            case "personality test":
                return Integer.parseInt(getText(personalityTestCatNumber));
            default:
                return 0;
        }
    }

}
