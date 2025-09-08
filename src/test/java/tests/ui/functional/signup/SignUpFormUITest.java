package tests.ui.functional.signup;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.ui.UrlConstants;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class SignUpFormUITest extends BaseTest{
	
	private SignUpLogin signUpLogin;
	private NavBar navBar;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		signUpLogin = new SignUpLogin();
		navBar = new NavBar();	
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {	
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	@Test(groups = {"headersTests"}, priority = 0)
	public void verifySignUpFormHeaderVisibility() {
		
		LogUtil.info("* Verifying sign up form header is visible.");
				
		navBar.clickSignUpLoginNav();
	
		Assert.assertTrue(signUpLogin.isSignUpHeaderVisible(), "Sign up header not visible.");
		LogUtil.info("Sign up form header is visible.");
		
	
		

	}

}

