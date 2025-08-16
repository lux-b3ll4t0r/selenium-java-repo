package utils;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	
	public WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public void initDriver() {
		
		String browser = ConfigManager.getBrowser().toLowerCase();
		boolean isHeadless = ConfigManager.isHeadless();
		
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			
			if(isHeadless) chromeOptions.addArguments("--headless=new");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			threadLocalDriver.set(new ChromeDriver(chromeOptions));
			threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
		
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			
			if(isHeadless) firefoxOptions.addArguments("--headless");
			threadLocalDriver.set(new FirefoxDriver(firefoxOptions));
			threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			
			if(isHeadless) edgeOptions.addArguments("--headless=new");
			threadLocalDriver.set(new EdgeDriver(edgeOptions));
			threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
			
		default:
			throw new IllegalArgumentException("Browser type not recognized: " + browser);
		}
		
	}
	
	public void quitDriver() {
		
		if(threadLocalDriver != null) {
			threadLocalDriver.get().quit();
			threadLocalDriver.remove();
			
		}
	}
		
}
