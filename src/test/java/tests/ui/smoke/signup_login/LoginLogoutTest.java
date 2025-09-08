package tests.ui.smoke.signup_login;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.UIBaseTest;
import constants.ui.UrlConstants;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class LoginLogoutTest extends UIBaseTest{
	
	private NavBar navBar;
	private SignUpLogin signUpLogin;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		navBar = new NavBar();
		signUpLogin = new SignUpLogin();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}

	@Test(groups = {"smoke"}, priority = 0)
	public void login_test() {
		
		LogUtil.info("* Verifying Login fields can be populated and user is logged in successfully.");
		
		LogUtil.info("Navigating to Sign up / Login: [" + UrlConstants.LOGIN + "]");
		navBar.clickSignUpLoginNav();
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
		signUpLogin.login(email, pass);
		
		Assert.assertTrue(navBar.isLoggedInAsVisible(), "Logged in as is not visible.");
		LogUtil.info("User logged in successfully.");

	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void logout_test() {
		
		LogUtil.info("* Verifying user can log out successfully.");
		
		LogUtil.info("Navigating to: Sign up / Login: [" + UrlConstants.LOGIN + "]");
		navBar.clickSignUpLoginNav();
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
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
