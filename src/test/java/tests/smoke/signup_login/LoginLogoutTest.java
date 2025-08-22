package tests.smoke.signup_login;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.BasePage;
import utils.ConfigManager;
import utils.LogUtil;

public class LoginLogoutTest extends BaseTest{
	
	NavBar navBar;
	SignUpLogin signUpLogin;
	BasePage basePage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpResources() {
		LogUtil.debug("Setting up test resources");
		navBar = new NavBar(driver);
		signUpLogin = new SignUpLogin(driver);
		basePage = new BasePage(driver);
		
		LogUtil.debug("Set up successfully");
	}

	@Test(groups = {"smoke"}, priority = 0)
	public void verifyLoginFunctionality() {
		
		LogUtil.info("[TEST STARTED]: Verifying Login fields can be populated and user is logged in successfully.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Sign up / Login");
		navBar.clickSignUpLoginNav();
		
		String email = ConfigManager.getEmail();
		String pass = ConfigManager.getPassword();
		
		signUpLogin.login(email, pass);
		
		Assert.assertTrue(navBar.isLoggedInAsVisible());
		LogUtil.info("User logged in successfully");
		
		LogUtil.info("[TEST COMPLETED]");

	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void verifyLogoutFunctionality() {
		
		LogUtil.info("[TEST STARTED]: Verifying user can log out successfully.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Sign up / Login");
		navBar.clickSignUpLoginNav();
		
		String email = ConfigManager.getEmail();
		String pass = ConfigManager.getPassword();
		
		signUpLogin.login(email, pass);
		
		Assert.assertTrue(navBar.isLoggedInAsVisible());
		LogUtil.info("User logged in");
		navBar.clickLogoutButton();
		LogUtil.info("Logging user out");
		
		basePage.waitForUrlToBe(ConfigManager.getLoginUrl());
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getLoginUrl());
		LogUtil.info("User logged out successfully");
		
		LogUtil.info("[TEST COMPLETED]");
		
		
	}
}
