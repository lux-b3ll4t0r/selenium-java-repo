package pages.signup_login;

import org.openqa.selenium.By;

import com.github.javafaker.Faker;

import utils.DriverFactory;
import utils.LogUtil;
import utils.Webtool;

public class SignUpLogin {
	
	private By loginHeader = By.xpath("//div[@class='login-form']/h2[text()='Login to your account']");
	private By loginEmailInput = By.xpath("//form[@action='/login']/input[@name='email']");
	private By passwordInput = By.xpath("//form[@action='/login']/input[@name='password']");
	private By loginButton = By.xpath("//form[@action='/login']/button[@data-qa='login-button']");
	private By signUpFormH2 = By.xpath("//div[@class='signup-form']/h2[text()='New User Signup!']");
	private By nameInput = By.xpath("//form[@action='/signup']/input[@name='name']");
	private By signUpEmailInput = By.xpath("//form[@action='/signup']/input[@name='email']");
	private By signUpButton = By.xpath("//form[@action='/signup']/button[@data-qa='signup-button']");
	//private By emailExistMsg = By.xpath("//form[@action = '/signup']/p[contains(text(), 'Email Address already exist!')]");
	
	public void startUserSignUp(String name, String email) {
		
		enterSignUpName(name);
		enterSignUpEmail(email);
		clickSignUpButton();
	}
	
	public void clickLoginButton() {
		Webtool.clickElement(loginButton);
	}

	
	public void clickSignUpButton() {
		Webtool.clickElement(signUpButton);
	}
	
	public void enterLoginEmail(String email) {
		Webtool.sendKeysTo(loginEmailInput, email);
	}
	
	public void enterLoginPassword(String password) {
		Webtool.sendKeysTo(passwordInput, password);
	}
	
	public void enterSignUpName(String name) {
		Webtool.sendKeysTo(nameInput, name);
	}
	
	public void enterSignUpEmail(String email) {
		Webtool.sendKeysTo(signUpEmailInput, email);
	}
	
	public boolean isLoginHeaderVisible() {
		return Webtool.isElementVisible(loginHeader);
	}
	
	public boolean isSignUpHeaderVisible() {
		return Webtool.isElementVisible(signUpFormH2);
	}
	
	public boolean isEmailErrorDisplayed() {
		
		return !DriverFactory.getDriver().findElements(By.xpath("//form[@action = '/signup']/p[contains(text(), 'Email Address already exist!')]")).isEmpty();
	}
	
	public String getLoginEmailText() {
		return Webtool.getInputText(loginEmailInput);
	}
	
	public String getPasswordText() {
		return Webtool.getInputText(passwordInput);
	}
	
	public void login(String email, String password) {
		enterLoginEmail(email);
		enterLoginPassword(password);
		clickLoginButton();
	}
	
	public void signUpNewUserWithRetry(String name, String email) {
		
		int attempts = 0;
		boolean success = false;
		
		while(attempts < 5 && success == false) {
			
			enterSignUpName(name);
			enterSignUpEmail(email);
			clickSignUpButton();
			
			if(isEmailErrorDisplayed() == false) {
				success = true;
				break;
			}else {
				attempts++;
				LogUtil.warn("An email error was displayed -> Attempting new email address, assuming email is already in use (Attempt: " + attempts + ")");
				Faker faker = new Faker();
				email = faker.internet().emailAddress();
			}
			
		}
	}
}
