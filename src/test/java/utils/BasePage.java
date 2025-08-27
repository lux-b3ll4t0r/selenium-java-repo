package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	public static void main(String[] args) {
		BasePage.safeSetup();
	}
	
	public static WebElement waitForVisibitliyOfElementLocated(By locator, int duration) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public static void safeSetup() {
		
		try {
			driver = DriverFactory.getDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		
		}catch(Exception e) {
			LogUtil.warn("Safe setup unsuccessful: ", e);
		}
	
	}
	
	public static boolean isSetup() {
		if(driver != null && wait != null) {
			return true;
		}
		
		return false;
	}
	
	public static WebDriver getDriver() {
		return BasePage.driver;
	}
	
	public static void get(String url) {
		
		if(BasePage.isSetup()) {
			driver.get(url);
		}
	}
	
	public static void clearCookies() {
		
		if(BasePage.isSetup()) {
			driver.manage().deleteAllCookies();
		}
	}
	
	public static String getCurrentUrl() {
		if(BasePage.isSetup()) {
			return driver.getCurrentUrl();
		}
		
		return "";
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
	
	public static String getInputText(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		return element.getAttribute("value");
	}
	
	
	
	public static boolean isElementVisible(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		return element.isDisplayed();
	}
	
	public static void waitForUrlToBe(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}
	
	public static void waitForInvisibilityOfElementLocatedBy(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public static WebElement waitForVisibitliyOfElementLocated(By locator) {
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
