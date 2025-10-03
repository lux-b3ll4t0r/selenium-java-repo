package ui.tests.account;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.SignUpLogin;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

@Listeners(common.listeners.TestListener.class)
public class LoginTest extends UIBaseTest{
	
	private Homepage homepage;
	private SignUpLogin login;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		homepage = new Homepage();
		login = new SignUpLogin();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}

	@Test(groups = {"smoke"})
	public void login_smoke_test() {
		LogUtil.info("* Verifying user can login successfully.");
		
		LogUtil.info("Navigating to Sign up / Login");
		homepage.navigateToLogin();
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
		LogUtil.info("Logging in user.");
		login.login(email, pass);
		
		Assert.assertTrue(homepage.isLoggedInAsVisible(), "Logged in as is not visible.");
		LogUtil.info("User logged in successfully.");
	}
	
	@Test(groups = {"functional", "negative"})
	public void login_valid_email_invalid_pass_functional_test() {
		LogUtil.info("* Verifying attempting to login with valid email but invalid password rejects form.");
		
		LogUtil.info("Navigating to Sign up / Login");
		homepage.navigateToLogin();
		
		String email = System.getenv("UI_USER");
		String pass = "invalidpassword123";
		
		LogUtil.info("Attempting to login with valid email and invalid password.");
		login.login(email, pass);
		
		Assert.assertTrue(login.isInvalidLoginMsgVisible(), "Invalid login message was not displayed.");
		LogUtil.info("Form rejected successfully.");
	}
	
	@Test(groups = {"functional", "negative"})
	public void login_partial_email_valid_pass_functional_test() {
		LogUtil.info("* Verifying attempting to login with partial valid email but valid password rejects form.");
		
		LogUtil.info("Navigating to Sign up / Login");
		homepage.navigateToLogin();
		
		String email = System.getenv("UI_USER");
		String partialEmail = email.substring(0, email.indexOf("@"));
		String pass = System.getenv("UI_PASS");
		
		LogUtil.info("Attempting to login partial email with valid password.");
		login.login(partialEmail, pass);
		
		Assert.assertTrue(login.isBrowserErrorDisplayed(), "Browser error was not displayed.");
		LogUtil.info("Form rejected successfully.");
	}
	
	@Test(groups = {"functional"}, dataProvider = "pages")
	public void login_navigation_functional_test(String page) {
		LogUtil.info("* Verifying login session is saved navigating to different pages.");
		
		LogUtil.info("Navigating to Sign up / Login");
		homepage.navigateToLogin();
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
		LogUtil.info("Logging in user.");
		login.login(email, pass);
		
		LogUtil.info("Navigating to: " + page);
		Webtool.get(page);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homepage.isLoggedInAsVisible(), "User was not logged in at: " + page);
		softAssert.assertAll();
		LogUtil.info("Session saved successfully.");
	}
	
	@DataProvider(name = "pages")
	public Object[][] pages(){
		return new Object[][] {
			{UrlConstants.PRODUCTS},
			{UrlConstants.CART},
			{UrlConstants.TEST_CASES},
			{UrlConstants.API_TESTING},
			{UrlConstants.CONTACT_US},
		};
	}
	
	@Test(groups = {"functional", "negative"}, dataProvider = "incompleteLogin")
	public void login_form_incomplete_negative_test(String type, String email, String password) {
		LogUtil.info("* Verifying login form is rejected with incomplete inputs.");
		
		LogUtil.info("Navigating to Sign up / Login");
		homepage.navigateToLogin();
		
		LogUtil.info("Logging in and validating: " + type);
		login.login(email, password);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(login.isBrowserErrorDisplayed(), "No browser error was displayed.");
		softAssert.assertAll();
		
		LogUtil.info("Browser error displayed successfully for incomplete form.");
	}
	
	@DataProvider(name = "incompleteLogin")
	public Object[][] incompleteLogin(){
		return new Object[][] {
			{"No values", "", ""},
			{"No password", "emailnopass@gmail.com", ""},
			{"No email", "", "noemail"},
			{"No @ sign", "emailnoatsign", "password"},
			{"No domain", "emailnoatsign@", "password"},
			{"No domain", "emailnoatsign@.com", "password"},
		};
	}
}
