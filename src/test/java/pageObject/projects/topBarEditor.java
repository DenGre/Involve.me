package pageObject.projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.basePage.basePage;

public class topBarEditor extends basePage {


    public topBarEditor(WebDriver driver) {
        super(driver);
    }

    /* The top sections of the projects page */
    @FindBy(css = "lg:p-4 block")
    private WebElement projects;
    @FindBy(css = "#nav-dropdown")
    private WebElement dropdown;
    @FindBy(css = "[href='https://app.involve.me/logout']")
    private WebElement logoutBtn;
    @FindBy(css = "[href='https://app.involve.me/templates']")
    private WebElement templates;
    @FindBy(css = "[href='https://app.involve.me/analytics']")
    private WebElement analytics;
    /* The next elements are connected to the dropdown menu of the personal account */
    @FindBy(css = "[href='https://app.involve.me/integrations']")
    private WebElement integrations;
    @FindBy(css = "[href='https://app.involve.me/affiliate']")
    private WebElement affiliate;
    @FindBy(css = "[href='https://app.involve.me/organizations']")
    private WebElement changeOrganizations;
    @FindBy(css = "[href='https://app.involve.me/account']")
    private WebElement yourAccount;
    @FindBy(css = "[href=\"https://app.involve.me/billing\"]")
    private WebElement plansAndBilling;
    @FindBy(css = "[href=\"https://app.involve.me/payment-accounts\"]")
    private WebElement paymentIntegrations;
    @FindBy(css = "[href=\"https://app.involve.me/domains\"]")
    private WebElement manageDomains;
    @FindBy(css = "[href=\"https://app.involve.me/custom-fonts\"]")
    private WebElement customFonts;
    @FindBy(css = "[href=\"https://app.involve.me/support\"]")
    private WebElement support;
    @FindBy(css = " li:nth-child(9) > .w-full> .mb-1")
    private WebElement storage;
    //Upgrade button if the user still did not upgraded.
    @FindBy(css = "[href=\"https://app.involve.me/billing/plans\"]")
    private WebElement upgradeAccount;


    public void logOut() {
        click(dropdown);
        //wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        click(logoutBtn);
    }

    public void clickOnProjects() {
        click(projects);
    }

    public void clickOnTemplates() {
        click(templates);
    }

    public void clickOnAnalytics() {
        click(analytics);

    }

    public void clickOnIntegrations() {
        click(integrations);
    }

    public void clickOnAffiliateProgram() {
        click(affiliate);
    }

    //The next methods are interacting with the personal account that are located at the top right corner
    public void changeOrganization() {
        click(dropdown);
        click(changeOrganizations);
    }

    public void yourAccount() {
        click(dropdown);
        click(yourAccount);
    }

    public void plansbilling() {
        click(dropdown);
        click(plansAndBilling);
    }

    public void paymentIntegrations() {
        click(dropdown);
        click(paymentIntegrations);
    }

    public void manageDomains() {
        click(dropdown);
        click(manageDomains);
    }

    public void customFonts() {
        click(dropdown);
        click(customFonts);
    }

    public void setSupport() {
        click(dropdown);
        click(support);
    }

    public void setStorage() {
        click(dropdown);
        click(storage);
    }

}
