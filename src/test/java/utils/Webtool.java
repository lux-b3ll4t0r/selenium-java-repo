package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Webtool {
	
	public static void get(String url) {
		DriverFactory.getDriver().get(url);
	}
	
	public static void clearCookies() {
			DriverFactory.getDriver().manage().deleteAllCookies();
	}
	
	public static void clearStorage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
			js.executeScript("window.localStorage.clear();");
			js.executeScript("window.sessionStorage.clear();");
		}catch(Exception e) {
			LogUtil.warn("Failed to clear local/session storage: " + e.getMessage());
		}
	}
	
	
	public static String getCurrentUrl() {	
		return DriverFactory.getDriver().getCurrentUrl();
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
	
	public static void scrollToElementLocated(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void acceptAlertSafe() {
		
		try {
			new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
								.until(ExpectedConditions.alertIsPresent());
			Alert alert = DriverFactory.getDriver().switchTo().alert();
			alert.accept();
		}catch(Exception e) {
			LogUtil.warn("Alert was not found, no need to handle.");
		}
	}
	
	public static boolean isElementVisible(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		return element.isDisplayed();
	}
	
	public static void waitForUrlToBe(String url) {
		new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.urlToBe(url));
	}
	
	public static void waitForInvisibilityOfElementLocatedBy(By locator) {
		new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public static WebElement waitForVisibitliyOfElementLocated(By locator) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public static WebElement waitForVisibitliyOfElementLocated(By locator, int duration) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(duration))
							.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public static WebElement waitForVisibilityOfElement(WebElement element) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement waitForElementToBeClickable(By locator) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static List<WebElement> waitForVisibilityOfAllElementsLocatedBy(By locator) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
}
