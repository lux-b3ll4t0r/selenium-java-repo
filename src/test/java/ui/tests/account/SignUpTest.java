package ui.tests.account;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;

import common.pojos.User;
import common.pojos.UserDataGenerator;
import common.utils.LogUtil;
import db.utils.SQLWorkbench;
import ui.constants.UrlConstants;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.AccountCreated;
import ui.pages.signup_login.SignUpAccountInfo;
import ui.pages.signup_login.SignUpLogin;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

@Listeners(common.listeners.TestListener.class)
public class SignUpTest extends UIBaseTest{
	
	private SignUpLogin signUpLogin;
	private SignUpAccountInfo signUpAccInfo;
	private AccountCreated accountCreated;
	private Homepage homepage;

	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		signUpLogin = new SignUpLogin();
		signUpAccInfo = new SignUpAccountInfo();
		accountCreated = new AccountCreated();
		homepage = new Homepage();	
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}

	
	@Test (groups = {"smoke"}, priority = 0)
	public void initial_signup_test() {
		
		LogUtil.info("* Verifying a new user can enter name and email address to create account.");
		
		LogUtil.info("Navigating to Signup.");
		homepage.navigateToSignup();
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		
		LogUtil.info("Signing up new user.");
		
		String name = new Faker().name().fullName();
		String email = new Faker().internet().emailAddress();
	
		signUpLogin.signUpNewUserWithRetry(name, email);
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.SIGNUP);
		LogUtil.info("Name and email accepted, directed to account information successfully..");
		
		
	}
	
	
	@Test(groups = {"smoke"}, priority = 1)
	public void account_info_smoke_test() {
		
		LogUtil.info("* Verifying user can submit account information.");
		
		LogUtil.info("Navigating to Signup.");
		homepage.navigateToSignup();
		
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		
		User user = UserDataGenerator.randomUser(); // creates a new user
			// generates that users info
		LogUtil.debug(user.toSafeString());
		
		LogUtil.debug("Signing up new user.");
		signUpLogin.signUpNewUserWithRetry(user.getFirstName(), user.getEmail());
		
		LogUtil.info("Entering and submitting new user account information.");
		signUpAccInfo.enterAndSubmitAccInfo(user);
		
		Assert.assertTrue(accountCreated.isAccountCreatedMessageVisible(), "Creation success message was not visible.");
		LogUtil.info("Account created successfully.");
		
		Connection con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.saveUser(con, user);
		SQLWorkbench.closeConnection(con);
		
	}
	
	@Test(groups = {"functional"}, priority = 1)
	public void account_info_data_persist_functional_test() {
		
		LogUtil.info("* Verifying initial name and email persist to account information page.");
		
		LogUtil.info("Navigating to Signup.");
		homepage.navigateToSignup();
		
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		
		String name = new Faker().name().fullName();
		String email = new Faker().internet().emailAddress();
		
		LogUtil.debug("Signing up new user.");
		signUpLogin.signUpNewUserWithRetry(name, email);
		
		SoftAssert softAssert = new SoftAssert();
	
		softAssert.assertEquals(signUpAccInfo.getFullNameInput(), name);
		softAssert.assertEquals(signUpAccInfo.getEmailInput(), email);
		softAssert.assertAll();
		
		LogUtil.info("Name and email persisted successfully.");
	}
	
	@Test(groups = {"functional"})
	public void account_info_incomplete_functional_test() {
		LogUtil.info("* Verifying account information form is rejected when submitting incomplete form.");
		
		LogUtil.info("Navigating to Signup.");
		homepage.navigateToSignup();
		
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		
		String name = new Faker().name().fullName();
		String email = new Faker().internet().emailAddress();
		
		LogUtil.debug("Signing up new user.");
		signUpLogin.signUpNewUserWithRetry(name, email);
		
		LogUtil.info("Submitting incomplete form.");
		signUpAccInfo.clickCreateAccount();
		
		Assert.assertTrue(signUpAccInfo.isPasswordBrowserErrorVisible(), "Form did not display rejection error.");

		LogUtil.info("Form rejected successfully.");

		
	}
}
