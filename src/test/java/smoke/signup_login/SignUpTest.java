package smoke.signup_login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.homepage.NavBar;
import pages.signup_login.AccountCreated;
import pages.signup_login.SignUpAccountInfo;
import pages.signup_login.SignUpLogin;
import utils.ConfigManager;
import utils.ExcelUtils;
import utils.LogUtil;
import utils.User;

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
		LogUtil.debug("Setting up test resources");
		signUpLogin = new SignUpLogin(driver);
		signUpAccInfo = new SignUpAccountInfo(driver);
		newUser = new User();
		accountCreated = new AccountCreated(driver);
		navBar = new NavBar(driver);		
		LogUtil.debug("Set up successfully");
		
		try {
			fis = new FileInputStream(ConfigManager.get("excelData") + ConfigManager.get("excelTestFile"));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet("users");
		}catch (Throwable t) {
			LogUtil.error("Excel File Issue: ", t);
		}
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void verifySignUpFormHeaderVisibility() {
		
		LogUtil.info("[TEST STARTED]: Verifying sign up form header is visible.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		navBar.clickSignUpLoginNav();
	
		Assert.assertTrue(signUpLogin.getSignUpFormH2().isDisplayed());
		LogUtil.info("** Sign up form header is visible **");
		
		LogUtil.info("[TEST COMPLETED]");
		

	}
	
	/*
	 * This is a smoke test that verifies the functionality of the initial signup form before
	 * the account information signup page ->  endpoint "/login"
	 * 
	 * 
	 */
	
	@Test (groups = {"smoke"}, priority = 1)
	public void verifySignUpFormFunctionality() {
		
		LogUtil.info("[TEST STARTED]: Verifying sign up form can be populated and submitted.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		navBar.clickSignUpLoginNav();
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("signUpLoginUrl")));
		
		newUser = new User();
		newUser.generateRandomUser();

		String name = newUser.getFullName();
		String email = newUser.getEmail();
		LogUtil.debug("Entering: \"" + name + "\"");
		LogUtil.debug("Entering: \"" + email + "\"");
		signUpLogin.signUpNewUserWithRetry(name, email);
		
		
		wait.until(ExpectedConditions.visibilityOf(signUpAccInfo.getAccInfoHeader()));
		Assert.assertTrue(signUpAccInfo.getAccInfoHeader().isDisplayed());
		LogUtil.info("Enter account information page is visible");
		
		LogUtil.info("[TEST COMPLETED]");
		
		
	}
	
	/*
	 *  This is a smoke test method that tests the functionality of the account sign up information 
	 * 	screen that is displayed after the initial signup page. All account information is written to an excel
	 *  file that has headers matching the HTML ID of the element. -> endpoint -> "/signup"
	 */
	
	
	@Test(groups = {"smoke"}, priority = 2)
	public void verifySignUpAccountInformationFunctionality() {
		
		LogUtil.info("[TEST STARTED]: Verifying sign up account information can be populated and submitted.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("** Clicking Sign up / Login **");
		navBar.getSignUpLoginNav().click();  
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("signUpLoginUrl")));
		
		newUser = new User();
		newUser.generateRandomUser();
		
		String name = newUser.getFirstName();
		String email = newUser.getEmail();
		
		LogUtil.debug("Signing up new user: \"" + name + "\" - \"" + email + "\"");
		signUpLogin.signUpNewUserWithRetry(name, email);	
		wait.until(ExpectedConditions.visibilityOf(signUpAccInfo.getAccInfoHeader()));
		
		LogUtil.info("Entering new user account information");
		
		String title = newUser.getTitle();
		LogUtil.debug("Title: \"" + title + "\"");
		signUpAccInfo.selectTitle(title);
		ExcelUtils.updateByColumnHeader(sheet, "title", title);
		
		LogUtil.debug("Name: \"" + name + "\"");
		ExcelUtils.updateByColumnHeader(sheet, "name", name);
		
		LogUtil.debug("Email: \"" + email + "\"");
		ExcelUtils.updateByColumnHeader(sheet, "email", email);
		
		String password = newUser.getPassword();
		signUpAccInfo.enterPassword(password);
		ExcelUtils.updateByColumnHeader(sheet, "password", password);
		
		LogUtil.debug("Entering date of birth \"" + newUser.getBirthday() + "\"");
		 
		int day = newUser.getDay();
		LogUtil.debug("Day: \"" + day + "\"" );
		signUpAccInfo.selectDay(day);
		ExcelUtils.updateByColumnHeader(sheet, "days", String.valueOf(day));
		
		int month = newUser.getMonth();						 
		LogUtil.debug("Month: \"" + month + "\"");
		signUpAccInfo.selectMonth(month);
		ExcelUtils.updateByColumnHeader(sheet, "months", String.valueOf(month));
		
		int year = newUser.getYear();
		LogUtil.debug("Year: \"" + year + "\"");
		signUpAccInfo.selectYear(year);
		ExcelUtils.updateByColumnHeader(sheet, "years", String.valueOf(year));
		
		LogUtil.debug("Sign up for news letter: true");
		signUpAccInfo.clickSignUpForNewsLetter();
		ExcelUtils.updateByColumnHeader(sheet, "newsletter", "true");
		
		LogUtil.debug("Receive special offers: true");
		signUpAccInfo.clickReceiveSpecialOffers();
		ExcelUtils.updateByColumnHeader(sheet, "optin", "true");
		
		LogUtil.debug("First name: \"" + newUser.getFirstName() + "\"");
		signUpAccInfo.enterFirstName(newUser.getFirstName());
		ExcelUtils.updateByColumnHeader(sheet, "first_name", newUser.getFirstName());
		
		LogUtil.debug("Last name: \"" + newUser.getLastName() + "\"");
		signUpAccInfo.enterLastName(newUser.getLastName());
		ExcelUtils.updateByColumnHeader(sheet, "last_name", newUser.getLastName());
		
		String company = newUser.getCompany();
		LogUtil.debug("Company name: \"" + company + "\"");
		signUpAccInfo.enterCompany(company);
		ExcelUtils.updateByColumnHeader(sheet, "company", company);
		
		String address1 = newUser.getAddress1();
		LogUtil.debug("Address 1: \"" + address1 + "\"");
		signUpAccInfo.enterAddress1(address1);
		ExcelUtils.updateByColumnHeader(sheet, "address1", address1);
		
		String address2 = newUser.getAddress2();
		LogUtil.debug("Address 2: \"" + address2 + "\"");
		signUpAccInfo.enterAddress2(address2);
		ExcelUtils.updateByColumnHeader(sheet, "address2", address2);
		
		String country = newUser.getCountry();
		LogUtil.debug("Country: \"" + country + "\"");
		signUpAccInfo.selectCountry(country);
		ExcelUtils.updateByColumnHeader(sheet, "country", country);
		
		String state = newUser.getState();
		LogUtil.debug("State: \"" + state + "\"");
		signUpAccInfo.enterState(state);
		ExcelUtils.updateByColumnHeader(sheet, "state", state);
		
		String city = newUser.getCity();
		LogUtil.debug("City: \"" + city + "\"");
		signUpAccInfo.enterCity(city);
		ExcelUtils.updateByColumnHeader(sheet, "city", city);
		
		int zipCode = newUser.getZipCode();
		LogUtil.debug("Zipcode: \"" + zipCode + "\"");
		signUpAccInfo.enterZipCode(zipCode);
		ExcelUtils.updateByColumnHeader(sheet, "zipcode", String.valueOf(zipCode));
		
		String mobileNumber = newUser.getMobileNumber();
		LogUtil.debug("Mobile number: \"" + mobileNumber + "\"");
		signUpAccInfo.enterMobileNumber(mobileNumber);
		ExcelUtils.updateByColumnHeader(sheet, "mobile_number", mobileNumber);
		
		LogUtil.info("Submitting sign up account info");
		signUpAccInfo.getCreateAccountButton().click();
		
		wait.until(ExpectedConditions.visibilityOf(accountCreated.getAccountCreated()));
		Assert.assertTrue(accountCreated.getAccountCreated().isDisplayed());
		LogUtil.info("Account created successfully");
		
		LogUtil.info("[TEST COMPLETED]");
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
