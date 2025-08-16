package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;

public class CommonFunctions extends BasePage{
	
	private NavBar navBar;
	private SignUpLogin login;
	
	public CommonFunctions(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		navBar = new NavBar(driver);
		login = new SignUpLogin(driver);
	}
	
	public void enterLoginDetails(String email, String password) {
		navBar.clickSignUpLoginNav();
		wait.until(ExpectedConditions.visibilityOf(login.getLoginFormH2()));
		
		login.enterLoginEmail(email);
		login.enterLoginPassword(password);
	}
	
	public void submitLoginDetails() {
		login.clickLoginButton();
	}
	
	public void login(String email, String password) {
		
		navBar.clickSignUpLoginNav();
		wait.until(ExpectedConditions.visibilityOf(login.getLoginFormH2()));
		
		login.login(email, password);
		
		
	}
	
	public void logout() {
		
		wait.until(ExpectedConditions.visibilityOf(navBar.getLogoutButton()));
		navBar.clickLogoutButton();
	}
}
