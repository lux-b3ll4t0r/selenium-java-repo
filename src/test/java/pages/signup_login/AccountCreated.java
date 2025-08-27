package pages.signup_login;

import org.openqa.selenium.By;

import utils.BasePage;

public class AccountCreated {
	
	private By accountCreated = By.xpath("//h2/b[contains(text(), 'Account Created!')]");
	private By continueButton = By.xpath("//a[@data-qa = 'continue-button']");
	

	public boolean isAccountCreatedMessageVisible() {
		return BasePage.isElementVisible(accountCreated);
	}
	
	public void clickContinue() {
		BasePage.clickElement(continueButton);
	}
}
