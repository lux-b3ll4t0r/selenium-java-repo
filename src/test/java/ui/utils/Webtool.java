package ui.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.utils.ConfigManager;
import common.utils.LogUtil;

public class Webtool {
	
	public static void get(String url) {DriverFactory.getDriver().get(url);}
	public static void refresh() {DriverFactory.getDriver().navigate().refresh();}
	public static void navigateBack() {DriverFactory.getDriver().navigate().back();}
	public static void clearCookies() {DriverFactory.getDriver().manage().deleteAllCookies();}
	public static String getCurrentUrl() {	return DriverFactory.getDriver().getCurrentUrl();}
	public static List<WebElement> getElementsAsList(By locator){return DriverFactory.getDriver().findElements(locator);}
	
	public static boolean isElementVisible(By locator) {
		
		try {
			WebElement element = waitForVisibitlityOfElementLocated(locator, 2);
			return element.isDisplayed();
		}catch(Exception e) {
			LogUtil.warn("Element was not visible, if element visibility was expected to fail intentional then ignore.");
		}
	
		return false;
	}
	
	public static boolean isElementNotVisible(By locator) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(locator);
			element.click();
		}catch(Exception e) {
			return true;
		}
		return false;
	}
	
	public static boolean isElementClickable(By locator) {
		try {
			waitForElementToBeClickable(locator);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public static void sendKeysTo(By locator, String text) {
		WebElement element = waitForElementToBeClickable(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	public static WebElement getElement(By locator) {
		return DriverFactory.getDriver().findElement(locator);
	}
	
	public static void clickElement(By locator) {
		WebElement element = waitForVisibitlityOfElementLocated(locator);
		
		try {
			element.click();		
		}catch(Exception e) {
			try {
				scrollToElementLocated(locator);
				element.click();
			}catch(Exception t) {
				LogUtil.error("Tried to click element: " + locator + " but failed.");
			}
		}
	}
	
	public static void clickElement(WebElement element) {
		
		try {
			waitForElementToBeClickable(element);
			element.click();			
		}catch(Exception e) {
			try {
				scrollToElementLocated(element);
				element.click();
			}catch(Exception t) {
				LogUtil.error("Tried to click element: " + element + " but failed.");
			}
		}
	}	
	
	public static void openNewTab() {
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<>(DriverFactory.getDriver().getWindowHandles());
		DriverFactory.getDriver().switchTo().window(tabs.get(1));
	}
	
	public static String getElementAttribute(By locator, String attribute) {
		waitForVisibitlityOfElementLocated(locator);
		return DriverFactory.getDriver().findElement(locator).getAttribute(attribute);
	}
	
	public static String getInputText(By locator) {
		WebElement element = waitForVisibitlityOfElementLocated(locator);
		return element.getAttribute("value");
	}
	
	public static String getText(By locator) {
		waitForVisibitlityOfElementLocated(locator);
		return DriverFactory.getDriver().findElement(locator).getText();
	}
	public static void scrollToElementLocated(By locator) {
		WebElement element = waitForVisibitlityOfElementLocated(locator);
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static String getBrowserErrorMessage(By locator) {
		WebElement element = waitForVisibitlityOfElementLocated(locator, 2);
		return (String) ((JavascriptExecutor) DriverFactory.getDriver())
		        .executeScript("return arguments[0].validationMessage;", element);
	}
	

	public static boolean isBrowserErrorMessageVisible() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
	
		WebElement invalidElement = Webtool.waitForVisibilityOfElement((WebElement) js.executeScript(
			    "return document.querySelector(':invalid');"));
	
		String message = (String) js.executeScript("return arguments[0].validationMessage;", invalidElement);
		
		if(!message.isEmpty()) {return true;}
		
		return false;
	}
	
	public static void scrollToElementLocated(WebElement element) {
		waitForVisibilityOfElement(element);
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void acceptAlertSafe() {
		try {
			new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
								.until(ExpectedConditions.alertIsPresent());
			Alert alert = DriverFactory.getDriver().switchTo().alert();
			alert.accept();
		}catch(Exception e) {
			LogUtil.warn("Alert was not found: " + e);
		}
	}
	
	public static void waitForUrlToBe(String url) {
		new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.urlToBe(url));
	}
	
	public static void waitForInvisibilityOfElementLocatedBy(By locator) {
		new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public static WebElement waitForVisibitlityOfElementLocated(By locator) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static WebElement waitForVisibitlityOfElementLocated(By locator, int duration) {
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
	
	public static WebElement waitForElementToBeClickable(WebElement element) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.elementToBeClickable(element));	
	}
	
	public static List<WebElement> waitForVisibilityOfAllElementsLocatedBy(By locator) {
		return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(ConfigManager.getWaitDuration()))
							.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public static void clearStorage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
			js.executeScript("window.localStorage.clear();");
			js.executeScript("window.sessionStorage.clear();");
		}catch(Exception e) {
			LogUtil.warn("Failed to clear local/session storage: " + e);
		}
	}
}
