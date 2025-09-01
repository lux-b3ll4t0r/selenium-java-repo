package tests.smoke.signup_login;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.UrlConstants;
import pages.homepage.NavBar;
import pages.signup_login.AccountCreated;
import pages.signup_login.SignUpAccountInfo;
import pages.signup_login.SignUpLogin;
import utils.LogUtil;
import utils.SQLWorkbench;
import utils.User;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class SignUpTest extends BaseTest{
	
	private SignUpLogin signUpLogin;
	private SignUpAccountInfo signUpAccInfo;
	private AccountCreated accountCreated;
	private User newUser;
	private NavBar navBar;
	private Connection con;

	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		signUpLogin = new SignUpLogin();
		signUpAccInfo = new SignUpAccountInfo();
		newUser = new User();
		accountCreated = new AccountCreated();
		navBar = new NavBar();	
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}

	
	@Test (groups = {"smoke"}, priority = 0)
	public void initial_signup_test() {
		
		LogUtil.info("* Verifying a new user can sign up to reach the account information page.");
		
		navBar.clickSignUpLoginNav();
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		
		newUser = new User();
		newUser.generateRandomUser();

		String name = newUser.getFullName();
		String email = newUser.getEmail();
		LogUtil.debug("Entering: \"" + name + "\"");
		LogUtil.debug("Entering: \"" + email + "\"");
		signUpLogin.signUpNewUserWithRetry(name, email);
		
		Assert.assertTrue(signUpAccInfo.isAccountInfoHeaderVisible());
		LogUtil.info("Enter account information page is visible");
		
		
	}
	
	/*
	 *  This is a smoke test method that tests the functionality of the account sign up information 
	 * 	screen that is displayed after the initial signup page. All account information is written to an excel
	 *  file that has headers matching the HTML ID of the element. -> endpoint -> "/signup"
	 */
	
	
	@Test(groups = {"smoke"}, priority = 1)
	public void actual_signup_test() {
		
		LogUtil.info("* Verifying submitting new user account information functionality.");
		
		LogUtil.info("Clicking Sign up / Login.");
		navBar.clickSignUpLoginNav();
		
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		
		newUser = new User(); // creates a new user
		newUser.generateRandomUser(); // generates that users info
		LogUtil.debug(newUser.toSafeString());
		
		String name = newUser.getFirstName();
		String email = newUser.getEmail();
		
		LogUtil.debug("Signing up new user: \"" + name + "\" - \"" + email + "\"");
		signUpLogin.signUpNewUserWithRetry(name, email);
		
		LogUtil.info("Entering new user account information");
	
		signUpAccInfo.selectTitle(newUser.getTitle());
		signUpAccInfo.enterPassword(newUser.getPassword()); 
		signUpAccInfo.selectDay(newUser.getDay());				 
		signUpAccInfo.selectMonth(newUser.getMonth());
		signUpAccInfo.selectYear(newUser.getYear());
		signUpAccInfo.clickSignUpForNewsLetter();
		signUpAccInfo.clickReceiveSpecialOffers();
		signUpAccInfo.enterFirstName(newUser.getFirstName());
		signUpAccInfo.enterLastName(newUser.getLastName());
		signUpAccInfo.enterCompany(newUser.getCompany());
		signUpAccInfo.enterAddress1(newUser.getAddress1());
		signUpAccInfo.enterAddress2(newUser.getAddress2());
		signUpAccInfo.selectCountry(newUser.getCountry());
		signUpAccInfo.enterState(newUser.getState());
		signUpAccInfo.enterCity(newUser.getCity());
		signUpAccInfo.enterZipCode(newUser.getZipCode());
		signUpAccInfo.enterMobileNumber(newUser.getMobileNumber());
		
		LogUtil.info("Submitting sign up account info");
		signUpAccInfo.clickCreateAccount();
		
		Assert.assertTrue(accountCreated.isAccountCreatedMessageVisible(), "Creation success message was not visible.");
		LogUtil.info("Account created successfully");
		
		con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.saveUser(con, newUser);
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void updateAndCloseResources() {
		
		LogUtil.trace("[UPDATING AND CLOSING RESOURCES]");
		
		if(con != null) {
			SQLWorkbench.closeConnection(con);
		}
	
	}
}
