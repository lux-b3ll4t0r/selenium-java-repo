package tests.functional.signup.ui;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.UrlConstants;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class SignUpFormUITest extends BaseTest{
	
	private SignUpLogin signUpLogin;
	private NavBar navBar;

	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.trace("Setting up test resources");
		signUpLogin = new SignUpLogin();
		navBar = new NavBar();		
		LogUtil.trace("Set up successfully");
		
		
	}
	
	@Test(groups = {"headersTests"}, priority = 0)
	public void verifySignUpFormHeaderVisibility() {
		
		LogUtil.info("* Verifying sign up form header is visible.");
		
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
		
		navBar.clickSignUpLoginNav();
	
		Assert.assertTrue(signUpLogin.isSignUpHeaderVisible());
		LogUtil.info("Sign up form header is visible");
		
	
		

	}

}

