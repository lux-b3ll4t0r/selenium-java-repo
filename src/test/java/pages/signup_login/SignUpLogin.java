package pages.signup_login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.github.javafaker.Faker;

import utils.BasePage;

public class SignUpLogin extends BasePage{

	
	public SignUpLogin(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='login-form']/h2[text()='Login to your account']")
	private WebElement loginFormH2;
	
	@FindBy(xpath = "//form[@action='/login']/input[@name='email']")
	private WebElement loginEmailInput;
	
	@FindBy(xpath = "//form[@action='/login']/input[@name='password']")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//form[@action='/login']/button[@data-qa='login-button']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[@class='signup-form']/h2[text()='New User Signup!']")
	private WebElement signUpFormH2;
	
	@FindBy(xpath = "//form[@action='/signup']/input[@name='name']")
	private WebElement nameInput;
	
	@FindBy(xpath = "//form[@action='/signup']/input[@name='email']")
	private WebElement signUpEmailInput;
	
	@FindBy(xpath = "//form[@action='/signup']/button[@data-qa='signup-button']")
	private WebElement signUpButton;
	
	@FindBy(xpath = "//form[@action = '/signup']/p[contains(text(), 'Email Address already exist!')]")
	private WebElement emailExistMsg;
	
	public void startUserSignUp(String name, String email) {
		
		nameInput.sendKeys(name);
		signUpEmailInput.sendKeys(email);
		signUpButton.click();
	}
	
	
	public WebElement getLoginFormH2() {
		return loginFormH2;
	}
	
	public WebElement getLoginEmailInput() {
		return loginEmailInput;
	}
	
	public WebElement getPasswordInput() {
		return passwordInput;
	}
	
	public WebElement getLoginButton() {
		return loginButton;
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public WebElement getSignUpFormH2() {
		return signUpFormH2;
	}
	
	public WebElement getNameInput() {
		return nameInput;
	}
	
	public WebElement getSignUpEmailInput() {
		return signUpEmailInput;
	}
	
	public WebElement getSignUpButton() {
		return signUpButton;
	}
	
	public void clickSignUpButton() {
		signUpButton.click();
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
	
	public boolean isEmailErrorDisplayed() {
		
		return !driver.findElements(By.xpath("//form[@action = '/signup']/p[contains(text(), 'Email Address already exist!')]")).isEmpty();
	}
	
	public void login(String email, String password) {
		sendKeysTo(loginEmailInput, email);
		sendKeysTo(passwordInput, password);
		clickLoginButton();
	}
	
	public void signUpNewUserWithRetry(String name, String email) {
		
		int attempts = 0;
		boolean success = false;
		
		while(attempts < 5 && success == false) {
			
			sendKeysTo(nameInput, name);
			sendKeysTo(signUpEmailInput, email);
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
