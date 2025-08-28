package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Webtool {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	public static void main(String[] args) {

	}
	
	public static void safeSetup() {
		
		try {
			driver = DriverFactory.getDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		
		}catch(Exception e) {
			LogUtil.warn("Safe setup unsuccessful: ", e);
		}
	
	}
	
	
	public static WebDriver getDriver() {
		return Webtool.driver;
	}
	
	public static void get(String url) {
		
		if(Webtool.isSetup()) {
			driver.get(url);
		}
	}
	
	public static void clearCookies() {
		
		if(Webtool.isSetup()) {
			driver.manage().deleteAllCookies();
		}
	}
	
	public static String getCurrentUrl() {
		if(Webtool.isSetup()) {
			return driver.getCurrentUrl();
		}
		
		return "";
	}
	
	public static String getInputText(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		return element.getAttribute("value");
	}
	
	public static void sendKeysTo(By locator, String text) {
		WebElement element = waitForElementToBeClickable(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	public static void clickElement(By locator) {
		WebElement element = waitForElementToBeClickable(locator);
		element.click();
	}
	
	public static void acceptAlertSafe() {
		
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}catch(Exception e) {
			LogUtil.warn("Alert was not found, no need to handle.");
		}
	}
	
	public void acceptAlert(){
		
	}
	
	
	public static boolean isSetup() {
		if(driver != null && wait != null) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isElementVisible(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		return element.isDisplayed();
	}
	
	public static void waitForUrlToBe(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}
	
	public static void waitForInvisibilityOfElementLocatedBy(By locator) {
		
		try {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}catch(TimeoutException e) {
			LogUtil.warn("Unable to wait for invisibility of element: " + locator);
		}
	}
	
	public static WebElement waitForVisibitliyOfElementLocated(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static WebElement waitForVisibitliyOfElementLocated(By locator, int duration) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public static WebElement waitForVisibilityOfElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement waitForElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static List<WebElement> waitForVisibilityOfAllElementsLocatedBy(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
}
