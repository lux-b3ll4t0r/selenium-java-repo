package utils;


import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;

public class CommonFunctions extends BasePage{
	
	private NavBar navBar; 
	private SignUpLogin login;


	
	
	public void submitLoginDetails() {
		login.clickLoginButton();
	}
	
	
	public void logout() {
		navBar.clickLogoutButton();
	}
}
