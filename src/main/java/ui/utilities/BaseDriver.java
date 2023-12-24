package ui.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDriver {
	private WebDriver driver;


	private static final Logger log = LoggerFactory.getLogger(BaseDriver.class);

	public WebDriver initialize() throws IOException {
		Properties prop = new Properties();
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
		prop.load(fi);
		driver = BrowserUtils.getDriver(prop);
		DataUtils.getWebAppDatafromConfig(prop);
		WebDriverListener listener = new WebDriverListener() {
			
			@Override
			public void beforeAnyCall(Object target, Method method, Object[] args) {
				String logline = String.format("About to call a method %s in element %s with parameters %s", method, target, args);
				log.trace(logline);
			}
			
			@Override
			public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
				String logline = String.format("Method %s called in element %s with parameters %s returned %s", method, target, args,
						result);
				log.trace(logline);
			}

		};
		driver = new EventFiringDecorator(listener).decorate(driver);
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver setDriver(WebDriver w_driver) {
		return driver = w_driver;
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

}
