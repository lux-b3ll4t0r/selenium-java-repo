package pages.signup_login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Faker;

import utils.BasePage;

public class SignUpLogin extends BasePage{

	
	public SignUpLogin(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
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
		clickElement(loginButton);
	}

	
	public void clickSignUpButton() {
		clickElement(signUpButton);
	}
	
	public void enterLoginEmail(String email) {
		sendKeysTo(loginEmailInput, email);
	}
	
	public void enterLoginPassword(String password) {
		sendKeysTo(passwordInput, password);
	}
	
	public void enterSignUpName(String name) {
		sendKeysTo(nameInput, name);
	}
	
	public void enterSignUpEmail(String email) {
		sendKeysTo(signUpEmailInput, email);
	}
	
	public boolean isLoginHeaderVisible() {
		return isElementVisible(loginHeader);
	}
	
	public boolean isSignUpHeaderVisible() {
		return isElementVisible(signUpFormH2);
	}
	
	public boolean isEmailErrorDisplayed() {
		
		return !driver.findElements(By.xpath("//form[@action = '/signup']/p[contains(text(), 'Email Address already exist!')]")).isEmpty();
	}
	
	public String getLoginEmailText() {
		return getInputText(loginEmailInput);
	}
	
	public String getPasswordText() {
		return getInputText(passwordInput);
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
				System.out.println("Attempting to retry with new email.");
				Faker faker = new Faker();
				email = faker.internet().emailAddress();
			}
			attempts++;
		}
	}
}
