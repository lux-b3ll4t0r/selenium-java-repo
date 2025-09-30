package ui.pages.signup_login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

import common.utils.LogUtil;
import ui.utils.DriverFactory;
import ui.utils.Webtool;

public class SignUpLogin {
	
	private By emailExistMsg = By.xpath("//p[contains(text(), 'Email Address already exist!')]");
	private By invalidLoginMsg = By.xpath("//p[contains(text(), 'Your email or password is incorrect!')]");
	private By loginHeader = By.xpath("//div[@class='login-form']/h2[text()='Login to your account']");
	private By loginEmailInput = By.xpath("//form[@action='/login']/input[@name='email']");
	private By passwordInput = By.xpath("//form[@action='/login']/input[@name='password']");
	private By loginButton = By.xpath("//form[@action='/login']/button[@data-qa='login-button']");
	private By signUpFormH2 = By.xpath("//div[@class='signup-form']/h2[text()='New User Signup!']");
	private By nameInput = By.xpath("//form[@action='/signup']/input[@name='name']");
	private By signUpEmailInput = By.xpath("//form[@action='/signup']/input[@name='email']");
	private By signUpButton = By.xpath("//form[@action='/signup']/button[@data-qa='signup-button']");
	

	
	public void clickLoginButton() {Webtool.clickElement(loginButton);}
	public void clickSignUpButton() {Webtool.clickElement(signUpButton);}
	public void enterLoginEmail(String email) {Webtool.sendKeysTo(loginEmailInput, email);}
	public void enterLoginPassword(String password) {Webtool.sendKeysTo(passwordInput, password);}
	public void enterSignUpEmail(String email) {Webtool.sendKeysTo(signUpEmailInput, email);}
	public void enterSignUpName(String name) {Webtool.sendKeysTo(nameInput, name);}
	public String getLoginEmailText() {return Webtool.getInputText(loginEmailInput);}
	public String getPasswordText() {return Webtool.getInputText(passwordInput);}
	public boolean isLoginHeaderVisible() {return Webtool.isElementVisible(loginHeader);}
	public boolean isSignUpHeaderVisible() {return Webtool.isElementVisible(signUpFormH2);}
	public boolean isInvalidLoginMsgVisible() {return Webtool.isElementVisible(invalidLoginMsg);}
	
	public boolean isBrowserErrorDisplayed() {
		
		try {
			String emailError = Webtool.getBrowserErrorMessage(loginEmailInput);
			String passwordError = Webtool.getBrowserErrorMessage(passwordInput);
			
			if(!emailError.isEmpty() || !passwordError.isEmpty()) {
				return true;
			}
		}catch(Exception e) {
			LogUtil.error("Failed to get error message.", e);
		}
		return false;
	}
	
	public void login(String email, String password) {
		enterLoginEmail(email);
		enterLoginPassword(password);
		clickLoginButton();
	}
	
	public void login() {
		enterLoginEmail(System.getenv("UI_USER"));
		enterLoginPassword(System.getenv("UI_PASS"));
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
	
	public void startUserSignUp(String name, String email) {
		
		enterSignUpName(name);
		enterSignUpEmail(email);
		clickSignUpButton();
	}
	
	public boolean isEmailErrorDisplayed() {
		List<WebElement> emailErrList = DriverFactory.getDriver().findElements(emailExistMsg);
		if(!emailErrList.isEmpty()) {
			return true;
		}
		return false;

	}

	
}
