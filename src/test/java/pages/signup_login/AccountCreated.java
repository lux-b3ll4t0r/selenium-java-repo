package pages.signup_login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BasePage;

public class AccountCreated extends BasePage{
	
	public AccountCreated(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h2/b[contains(text(), 'Account Created!')]")
	WebElement accountCreated;
	
	@FindBy(xpath = "//a[@data-qa = 'continue-button']")
	WebElement continueButton;
	
	public WebElement getAccountCreated() {
		return accountCreated;
	}
	
	public void clickContinue() {
		continueButton.click();
	}
}
