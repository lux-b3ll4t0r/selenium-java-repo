package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
	}
	public void sendKeysTo(By locator, String text) {
		WebElement element = waitForElementToBeClickable(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	public void clickElement(By locator) {
		WebElement element = waitForElementToBeClickable(locator);
		element.click();
	}
	
	
	public String getInputText(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		return element.getAttribute("value");
	}
	
	public boolean isElementVisible(By locator) {
		WebElement element = waitForVisibitliyOfElementLocated(locator);
		return element.isDisplayed();
	}

	public void waitForUrlToBe(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}
	
	public WebElement waitForVisibitliyOfElementLocated(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForVisibilityOfElement(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitForElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public List<WebElement> waitForVisibilityOfAllElementsLocatedBy(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
}
