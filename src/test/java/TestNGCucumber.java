import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/cucumber"}, glue = {"UIAutomator.Selenium"},
monochrome = true, plugin = {"html:target/cucumber.html"})
public class TestNGCucumber extends AbstractTestNGCucumberTests {

}
