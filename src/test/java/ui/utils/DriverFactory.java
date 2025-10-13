package ui.utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import common.utils.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
	private static String browser = System.getProperty("browser", ConfigManager.getBrowser().toLowerCase());
	private static boolean isHeadless = ConfigManager.isHeadless();
	private static ChromeOptions chromeOptions;
	private static FirefoxOptions firefoxOptions;
	private static EdgeOptions edgeOptions;
	
	public static WebDriver getDriver() {

		return threadLocalDriver.get();
	}
		
	private static void setupChromeOptions() {
		chromeOptions = new ChromeOptions();
		
		if(isHeadless) chromeOptions.addArguments("--headless=new", "--disable-gpu");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--window-size=1920,1080");
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	}
	
	private static void setupFirefoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		if(isHeadless) firefoxOptions.addArguments("--headless");
	
	}
	
	private static void setupEdgeOptions() {
		EdgeOptions edgeOptions = new EdgeOptions();
		if(isHeadless) edgeOptions.addArguments("--headless=new");
	
	}
	
	private static void setDriver() {

		switch(browser) {
		case "chrome":
			threadLocalDriver.set(new ChromeDriver(chromeOptions));
			break;
		case "firefox":
			threadLocalDriver.set(new FirefoxDriver(firefoxOptions));
			break;
		case "edge":
			threadLocalDriver.set(new EdgeDriver(edgeOptions));
			break;
		default:
			throw new IllegalArgumentException("Browser type not recognized: " + browser);
		}
	}
	
	public static void setupDriver() {
			
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			setupChromeOptions();
			setDriver();
			break;
		
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			setupFirefoxOptions();
			setDriver();
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			setupEdgeOptions();
			setDriver();
			break;
			
		default:
			throw new IllegalArgumentException("Browser type not recognized: " + browser);
		}
		
	}
	
	public static void quitDriver() {
		if(threadLocalDriver != null) {
			threadLocalDriver.get().quit();
			threadLocalDriver.remove();
		}
	}
		
}
