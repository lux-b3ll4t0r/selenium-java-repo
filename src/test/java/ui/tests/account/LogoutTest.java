package ui.tests.account;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.SignUpLogin;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

public class LogoutTest extends UIBaseTest{
	
	private Homepage homepage;
	private SignUpLogin login;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		homepage = new Homepage();
		login = new SignUpLogin();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}

	@Test(groups = {"smoke"})
	public void logout_smoke_test() {
		LogUtil.info("Verifying user can log out successfully.");
		
		LogUtil.info("Navigating to: Sign up / Login");
		homepage.navigateToLogin();
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
		LogUtil.info("Logging in.");
		login.login(email, pass);
		Assert.assertTrue(homepage.isLoggedInAsVisible(), "User not logged in or \"logged in as\" element not visible.");
		
		LogUtil.info("Logging out.");
		homepage.logout();
		
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		LogUtil.info("User logged out successfully.");
	}
	
	@Test(groups = {"functional"})
	public void logout_navigate_backwards_functional_test() {
		LogUtil.info("Verifying after logout, user is still logged out after navigating backwards.");
		
		LogUtil.info("Navigating to: Sign up / Login");
		homepage.navigateToLogin();
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
		LogUtil.info("Logging in.");
		login.login(email, pass);
		
		LogUtil.info("Logging out.");
		homepage.logout();
		
		Webtool.navigateBack();
		Webtool.refresh();
		Assert.assertFalse(homepage.isLoggedInAsVisible(), "User is still logged in.");
		
		LogUtil.info("User logged out successfully.");
	}
	
	@Test(groups = {"functional"})
	 	public void logout_new_tab_functional_test() {
		LogUtil.info("Verifying user is logged out after accessing through a new tab.");
		
		LogUtil.info("Navigating to: Sign up / Login");
		homepage.navigateToLogin();
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
		LogUtil.info("Logging in.");
		login.login(email, pass);
		
		LogUtil.info("Logging out.");
		homepage.logout();
		
		Webtool.openNewTab();
		Webtool.get(UrlConstants.HOMEPAGE);
		Assert.assertFalse(homepage.isLoggedInAsVisible(), "User is still logged in.");
		
		LogUtil.info("User logged out successfully.");
	}
}
