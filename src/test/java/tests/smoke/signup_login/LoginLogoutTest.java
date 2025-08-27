package tests.smoke.signup_login;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.UrlConstants;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.ConfigManager;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class LoginLogoutTest extends BaseTest{
	
	NavBar navBar;
	SignUpLogin signUpLogin;
	Webtool basePage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpResources() {
		LogUtil.trace("Setting up test resources");
		navBar = new NavBar();
		signUpLogin = new SignUpLogin();
		LogUtil.trace("Set up successfully");
		
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}

	@Test(groups = {"smoke"}, priority = 0)
	public void verifyLoginFunctionality() {
		
		LogUtil.info("* Verifying Login fields can be populated and user is logged in successfully.");
		
		LogUtil.info("Navigating to Sign up / Login: [" + UrlConstants.LOGIN + "]");
		navBar.clickSignUpLoginNav();
		
		String email = ConfigManager.getEmail();
		String pass = ConfigManager.getPassword();
		
		signUpLogin.login(email, pass);
		
		Assert.assertTrue(navBar.isLoggedInAsVisible());
		LogUtil.info("User logged in successfully.");

	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void verifyLogoutFunctionality() {
		
		LogUtil.info("* Verifying user can log out successfully.");
		
		LogUtil.info("Navigating to: Sign up / Login: [" + UrlConstants.LOGIN + "]");
		navBar.clickSignUpLoginNav();
		
		String email = ConfigManager.getEmail();
		String pass = ConfigManager.getPassword();
		
		signUpLogin.login(email, pass);
		
		LogUtil.info("Logging in.");
		Assert.assertTrue(navBar.isLoggedInAsVisible());
		LogUtil.info("Logged in.");
		
		LogUtil.info("Logging user out.");
		navBar.clickLogoutButton();
		
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		LogUtil.info("User logged out.");
	}
}
