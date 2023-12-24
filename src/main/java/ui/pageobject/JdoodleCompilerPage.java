package ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.utilities.BaseDriver;
import ui.utilities.DriverUtils;

public class JdoodleCompilerPage {

    DriverUtils driverUtils ;
    BaseDriver base;

    public static final By pageTitle = By.xpath("//div[contains(@class,'md:leading-tight')]//h1");
    public static final By codeEditor = By.cssSelector("div#ideCodeEditor");
    public static final By codeEditorLines = By.cssSelector("div#ideCodeEditor div.ace_layer.ace_text-layer .ace_line");
    public static final By searchBar  = By.cssSelector("#navbar-collapse-basic #searchBar");
    public static final By searchBarInput  = By.cssSelector("#navbar-collapse-basic #searchBar");
    public static final By initialSearchBar  = By.cssSelector("#navbar-collapse-basic span.mr-8");
    public static final By executeButton = By.xpath("//button[contains(text(), 'Execute')]");
    //public static final By outputButton = By.cssSelector("div#ideCodeEditor");
    public static final By generatedFilesLabel = By.xpath("//h6[contains(text(),'Generated Files')]");
    public static final By compiledButton = By.xpath("//p[contains(text(),'Compiled and & executed')]");
    public static final By searchResults = By.cssSelector("#codeSearchDropdown span");
    public static final By gotItButton = By.xpath("//button[contains(text(),' Got it! ')]");
    public static final By shareLabel = By.xpath("//div[contains(@class,'print:block')]//*[contains(text(),'Like coding with JDoodle? Share a review!')]");
    public static final By loginButton = By.xpath("//div[@id='navbar-collapse-basic']//div[contains(text(),'Login')]");
    public static final By closeButton = By.cssSelector("div[aria-overlay='true'] span.sr-only");

    public void navigateToCompilerPage(String url) {
        driverUtils.openPage(url);

    }

    public void clickOnLoginButton(){
        driverUtils.clickOnElement(loginButton);
    }
    public void clickOnCloseButton(){
        driverUtils.clickOnElement(closeButton);
    }


    public void checkLogin(String text) {
        driverUtils.waitForElementToBeVisible(By.xpath("//div[contains(@aria-overlay,'true')]//button[contains(text(),'"+text+"')]"));
    }

    public void acceptCookies() {
        driverUtils.clickOnElement(gotItButton);
    }

    public JdoodleCompilerPage(BaseDriver driver) {
        this.driverUtils = new DriverUtils(driver.getDriver());
        base = driver;

    }

    public WebElement getPageTitle() {
        return driverUtils.findElement(pageTitle);
    }

    public WebElement getCodeEditorLine(int lineNumber) {
        WebElement we= driverUtils.findElement(codeEditor);
        driverUtils.waitForElementToBeVisible(we);
        return driverUtils.findElements(codeEditorLines).get(lineNumber);
    }

    public String getCodeEditorLineText(int lineNumber) {
        return getCodeEditorLine(lineNumber).getText();
    }

    public void searchLanguage(String language) {
        driverUtils.clickOnElement(initialSearchBar);
        driverUtils.enterText(searchBar, language);

    }

    public void searchLanguageIsDisplayed() {
        driverUtils.waitForElementToBeVisible(searchBar);
    }

    public void selectLanguage(String language) {
        driverUtils.clickOnElement(By.xpath("//*[@id='navbar-collapse-basic']//span[text()='"+language+"']"));
    }

    public int getCountOfSearchResult() {
        return driverUtils.findElements(searchResults).size();
    }

    public WebElement getGeneratedFilesElement() {
        return driverUtils.findElement(shareLabel);

    }

    public void clickOnExecuteButton() {

        driverUtils.scrollToElement();
        driverUtils.clickOnElement(executeButton);
    }

    public void verifyOutputOfCode(String text) {
        driverUtils.scrollToElement(getGeneratedFilesElement());
        driverUtils.waitForElementToBeVisible(By.xpath("//div[@id='output']//div[text()='"+text+"']"));
        //driverUtils.findElement(By.xpath("//div[@id='output']//div[text()='"+text+"']"));
    }

    public void verifyMenuItemsDisplayed(String menu){
        driverUtils.findElement(By.xpath("//div[@id='navbar-collapse-basic']//*[contains(text(),'"+menu+"')]"));
    }
}
