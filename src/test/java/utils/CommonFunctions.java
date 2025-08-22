package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
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
	
	
	public void submitLoginDetails() {
		login.clickLoginButton();
	}
	
	
	public void logout() {
		navBar.clickLogoutButton();
	}
}
