package tests.smoke.signup_login;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.ConfigManager;
import utils.LogUtil;


public class SignUpFormDisplayTest extends BaseTest{
	
	private SignUpLogin signUpLogin;
	private NavBar navBar;

	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.debug("Setting up test resources");
		signUpLogin = new SignUpLogin(driver);
		navBar = new NavBar(driver);		
		LogUtil.debug("Set up successfully");
		
		
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void verifySignUpFormHeaderVisibility() {
		
		LogUtil.info("[TEST STARTED]: Verifying sign up form header is visible.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		navBar.clickSignUpLoginNav();
	
		Assert.assertTrue(signUpLogin.isSignUpHeaderVisible());
		LogUtil.info("** Sign up form header is visible **");
		
		LogUtil.info("[TEST COMPLETED]");
		

	}

}

