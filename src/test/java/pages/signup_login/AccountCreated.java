package pages.signup_login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BasePage;

public class AccountCreated extends BasePage{
	
	public AccountCreated(WebDriver driver) {
		super(driver);
	}
	
	private By accountCreated = By.xpath("//h2/b[contains(text(), 'Account Created!')]");
	private By continueButton = By.xpath("//a[@data-qa = 'continue-button']");
	

	public boolean isAccountCreatedMessageVisible() {
		return isElementVisible(accountCreated);
	}
	
	public void clickContinue() {
		clickElement(continueButton);
	}
}
