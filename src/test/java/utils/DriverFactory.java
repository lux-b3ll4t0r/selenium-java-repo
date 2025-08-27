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
	private static String browser = ConfigManager.getBrowser().toLowerCase();
	private static boolean isHeadless = ConfigManager.isHeadless();
	private static ChromeOptions chromeOptions;
	private static FirefoxOptions firefoxOptions;
	private static EdgeOptions edgeOptions;
	
	public static WebDriver getDriver() {

		return threadLocalDriver.get();
	}
		
	public static void setupChromeOptions() {
		chromeOptions = new ChromeOptions();
		
		if(isHeadless) chromeOptions.addArguments("--headless=new");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
	}
	
	public static void setupFirefoxOptions() {
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		if(isHeadless) firefoxOptions.addArguments("--headless");
	
	}
	
	public static void setupEdgeOptions() {
		EdgeOptions edgeOptions = new EdgeOptions();
		if(isHeadless) edgeOptions.addArguments("--headless=new");
	
	}
	
	public static void setDriver() {

		switch(browser) {
		case "chrome":
			threadLocalDriver.set(new ChromeDriver(chromeOptions));
			threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
		case "firefox":
			threadLocalDriver.set(new FirefoxDriver(firefoxOptions));
			threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			break;
		case "edge":
			threadLocalDriver.set(new EdgeDriver(edgeOptions));
			threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
