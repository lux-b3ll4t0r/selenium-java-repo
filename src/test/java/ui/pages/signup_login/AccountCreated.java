package ui.pages.signup_login;

import org.openqa.selenium.By;

import ui.utils.Webtool;

public class AccountCreated {
	
	private By accountCreated = By.xpath("//h2/b[contains(text(), 'Account Created!')]");
	private By continueButton = By.xpath("//a[@data-qa = 'continue-button']");
	

	public boolean isAccountCreatedMessageVisible() {
		return Webtool.isElementVisible(accountCreated);
	}
	
	public void clickContinue() {
		Webtool.clickElement(continueButton);
	}
}
