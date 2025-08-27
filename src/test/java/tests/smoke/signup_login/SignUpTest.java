package tests.smoke.signup_login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;
import constants.UrlConstants;
import pages.homepage.NavBar;
import pages.signup_login.AccountCreated;
import pages.signup_login.SignUpAccountInfo;
import pages.signup_login.SignUpLogin;
import utils.BasePage;
import utils.ConfigManager;
import utils.ExcelUtils;
import utils.LogUtil;
import utils.User;

@Listeners(utils.TestListener.class)
public class SignUpTest extends BaseTest{
	
	private SignUpLogin signUpLogin;
	private SignUpAccountInfo signUpAccInfo;
	private AccountCreated accountCreated;
	private User newUser;
	private NavBar navBar;
	private FileInputStream fis;
	private Workbook workbook;
	private Sheet sheet;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.trace("Setting up test resources");
		signUpLogin = new SignUpLogin();
		signUpAccInfo = new SignUpAccountInfo();
		newUser = new User();
		accountCreated = new AccountCreated();
		navBar = new NavBar();		
		LogUtil.trace("Set up successfully");
		
		
		/*
		 * TODO: Get rid of and implement DB
		 * 
		 * 
		 * */
		try {
			fis = new FileInputStream(ConfigManager.get("excelData") + ConfigManager.get("excelTestFile"));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet("users");
		}catch (Throwable t) {
			LogUtil.error("Excel File Issue: ", t);
		}
	}

	
	/*
	 * This is a smoke test that verifies the functionality of the initial signup form before
	 * the account information signup page ->  endpoint "/login"
	 * 
	 * 
	 */
	
	@Test (groups = {"smoke"}, priority = 0)
	public void verifyUserSignUp() {
		
		LogUtil.info("* Verifying a new user can sign up to reach the account information page.");
		
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		BasePage.get(UrlConstants.BASE);
		
		navBar.clickSignUpLoginNav();
		BasePage.waitForUrlToBe(UrlConstants.LOGIN);
		
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
	public void verifySubmittingNewUserAccountInformation() {
		
		LogUtil.info("* Verifying submitting new user account information functionality.");
		
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		BasePage.get(UrlConstants.BASE);
		
		LogUtil.info("Clicking Sign up / Login.");
		navBar.clickSignUpLoginNav();
		
		BasePage.waitForUrlToBe(UrlConstants.LOGIN);
		
		newUser = new User(); // creates a new user
		newUser.generateRandomUser(); // generates that users info
		LogUtil.debug(newUser.toSafeString());
		
		String name = newUser.getFirstName();
		String email = newUser.getEmail();
		
		LogUtil.debug("Signing up new user: \"" + name + "\" - \"" + email + "\"");
		signUpLogin.signUpNewUserWithRetry(name, email);
		ExcelUtils.updateByColumnHeader(sheet, "name", name);
		ExcelUtils.updateByColumnHeader(sheet, "email", email);
		
		Assert.assertTrue(signUpAccInfo.isAccountInfoHeaderVisible());
		LogUtil.info("Entering new user account information");
		
		String title = newUser.getTitle();
		signUpAccInfo.selectTitle(title);
		ExcelUtils.updateByColumnHeader(sheet, "title", title);

		signUpAccInfo.enterPassword(newUser.getPassword());
		ExcelUtils.updateByColumnHeader(sheet, "password", newUser.getPassword());
			 
		int day = newUser.getDay();
		signUpAccInfo.selectDay(day);
		ExcelUtils.updateByColumnHeader(sheet, "days", String.valueOf(day));
		
		int month = newUser.getMonth();						 
		signUpAccInfo.selectMonth(month);
		ExcelUtils.updateByColumnHeader(sheet, "months", String.valueOf(month));
		
		int year = newUser.getYear();
		signUpAccInfo.selectYear(year);
		ExcelUtils.updateByColumnHeader(sheet, "years", String.valueOf(year));
		
		signUpAccInfo.clickSignUpForNewsLetter();
		ExcelUtils.updateByColumnHeader(sheet, "newsletter", "true");
		
		signUpAccInfo.clickReceiveSpecialOffers();
		ExcelUtils.updateByColumnHeader(sheet, "optin", "true");
		
		signUpAccInfo.enterFirstName(newUser.getFirstName());
		ExcelUtils.updateByColumnHeader(sheet, "first_name", newUser.getFirstName());
		
		signUpAccInfo.enterLastName(newUser.getLastName());
		ExcelUtils.updateByColumnHeader(sheet, "last_name", newUser.getLastName());
		
		String company = newUser.getCompany();
		signUpAccInfo.enterCompany(company);
		ExcelUtils.updateByColumnHeader(sheet, "company", company);
		
		String address1 = newUser.getAddress1();
		signUpAccInfo.enterAddress1(address1);
		ExcelUtils.updateByColumnHeader(sheet, "address1", address1);
		
		String address2 = newUser.getAddress2();
		signUpAccInfo.enterAddress2(address2);
		ExcelUtils.updateByColumnHeader(sheet, "address2", address2);
		
		String country = newUser.getCountry();
		signUpAccInfo.selectCountry(country);
		ExcelUtils.updateByColumnHeader(sheet, "country", country);
		
		String state = newUser.getState();
		signUpAccInfo.enterState(state);
		ExcelUtils.updateByColumnHeader(sheet, "state", state);
		
		String city = newUser.getCity();
		signUpAccInfo.enterCity(city);
		ExcelUtils.updateByColumnHeader(sheet, "city", city);
		
		int zipCode = newUser.getZipCode();
		signUpAccInfo.enterZipCode(zipCode);
		ExcelUtils.updateByColumnHeader(sheet, "zipcode", String.valueOf(zipCode));
		
		String mobileNumber = newUser.getMobileNumber();
		signUpAccInfo.enterMobileNumber(mobileNumber);
		ExcelUtils.updateByColumnHeader(sheet, "mobile_number", mobileNumber);
		
		LogUtil.info("Submitting sign up account info");
		signUpAccInfo.waitForAdIfShown();
		signUpAccInfo.clickCreateAccount();
		
		Assert.assertTrue(accountCreated.isAccountCreatedMessageVisible());
		LogUtil.info("Account created successfully");
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void updateAndCloseResources() {
		
		LogUtil.debug("[UPDATING AND CLOSING RESOURCES]");
		
		try {

			FileOutputStream fos = new FileOutputStream(ConfigManager.get("excelData") + ConfigManager.get("excelTestFile"));
			
			if(fis != null) { 
				fis.close(); 		
			}
			
			if(workbook != null) {
				workbook.write(fos);
				fos.close();
				workbook.close();
			}
			
		}catch (Exception e) {
			LogUtil.error("Failed to update excel file", e);
			
		}
	}
}
