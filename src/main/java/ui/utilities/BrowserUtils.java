package ui.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BrowserUtils {

    public static WebDriver getDriver(Properties prop) throws MalformedURLException {
        WebDriver driver;
        String browser = prop.getProperty("browser");
        String demoHubIP = prop.getProperty("demoHubIP");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            //options.addArguments("--headless");
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
        else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browser.equals("chromeremote")) {
            WebDriverManager.chromedriver().setup();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("chrome");
            //caps.setCapability("enableVNC", true);
            //caps.setCapability("enableVideo", false);
            URL url = new URL(demoHubIP);
            driver = new RemoteWebDriver(url, caps);
        }
        else if (browser.equals("firefoxremote")) {
            WebDriverManager.firefoxdriver().setup();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("firefox");
            URL url = new URL(demoHubIP);
            driver = new RemoteWebDriver(url, caps);
        }
        else {
            WebDriverManager.chromedriver().setup();
            DesiredCapabilities cap = new DesiredCapabilities("chrome", "105", Platform.WIN10);
            URL url = new URL(
                    "https://oauth-ravi.otwani647-a1e73:0a716747-b9c0-48ea-88f2-9890c1e76e1b@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
            driver = new RemoteWebDriver(url, cap);
        }
        return driver;
    }
}
