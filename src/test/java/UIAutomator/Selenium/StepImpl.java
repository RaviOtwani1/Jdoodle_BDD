package UIAutomator.Selenium;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.pageobject.JdoodleCompilerPage;
import ui.utilities.BaseTest;

public class StepImpl extends BaseTest {

    JdoodleCompilerPage jdoodleCompilerPage;
    String text;

    @Given("^Opening of Jdoodle (.+)$")
    public void opening_of_jdoodle_https_www_jdoodle_com_online_java_compiler(String url){
        //jdoodleCompilerPage = new JdoodleCompilerPage(baseDriver);
        setUp();
        jdoodleCompilerPage = new JdoodleCompilerPage(baseDriver);
        jdoodleCompilerPage.navigateToCompilerPage(url);
    }

    @When("We check that the code editor page is open with default Java language")
    public void we_check_that_the_code_editor_page_is_open_with_default_java_language() {
        text = jdoodleCompilerPage.getPageTitle().getText();
        Assert.assertEquals(text , "Online Java Compiler IDE", "text "+ text );
    }

    @Then("^Page title is (.+)$")
    public void page_title_is_online_java_compiler_ide(String title) {
        title = jdoodleCompilerPage.getCodeEditorLineText(1);
        Assert.assertEquals(title.trim() , "public static void main(String args[]) {", "text "+ title );

    }

    @When("^User is entering text (.+) to search bar$")
    public void user_is_entering_text_to_search_bar(String searchText) throws InterruptedException {
        jdoodleCompilerPage.searchLanguage(searchText);
    }

    @Then("^Search count (.+) should match as per the search string$")
    public void search_count_should_match_as_per_the_search_string(int len){
        int count = jdoodleCompilerPage.getCountOfSearchResult();
        Assert.assertEquals(count, len, "count "+ count);
        jdoodleCompilerPage.selectLanguage("C#");
    }

    @When("^User select a (.+) from search drop$")
    public void user_select_a_from_search_drop(String language){
       // jdoodleCompilerPage.selectLanguage("C#");
    }

    @Then("^Updated Page title is (.+) and code is updated in the editor(.+)$")
    public void updated_page_title_is_and_code_is_updated_in_the_editor(String title, String languageCode){
        text = jdoodleCompilerPage.getCodeEditorLineText(0);
        Assert.assertEquals(text.trim() , languageCode.trim(), "text "+ text );

        text = jdoodleCompilerPage.getPageTitle().getText();
        Assert.assertEquals(text.trim() , title.trim(), "text "+ text );
    }

    @When("User clicks on Execute Button")
    public void user_clicks_on_execute_button() {
        jdoodleCompilerPage.clickOnExecuteButton();
    }

    @Then("^Code should be complied and output should be displayed as (.+)$")
    public void code_should_be_complied_and_output_should_be_displayed_as(String codeExpected){
        jdoodleCompilerPage.verifyOutputOfCode(codeExpected);
        baseDriver.close();
    }

    @And("Menu Items are displayed correctly")
    public void menu_Items_are_displayed_correctly(){
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Products");
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Solutions");
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Resources");
        jdoodleCompilerPage.verifyMenuItemsDisplayed("Pricing");
    }

    @And("Login button displays {string} and {string}")
    public void Login_button_displays_and(String val1, String val2) {
        jdoodleCompilerPage.clickOnLoginButton();
        jdoodleCompilerPage.checkLogin(val1);
        jdoodleCompilerPage.checkLogin(val2);
        baseDriver.close();
    }

}
