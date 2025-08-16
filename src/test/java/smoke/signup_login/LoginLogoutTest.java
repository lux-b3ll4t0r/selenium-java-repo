package smoke.signup_login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.BasePage;
import utils.ConfigManager;
import utils.CommonFunctions;
import utils.LogUtil;

public class LoginLogoutTest extends BaseTest{
	
	NavBar navBar;
	SignUpLogin signUpLogin;
	BasePage basePage;
	CommonFunctions comFunc;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.debug("Setting up test resources");
		navBar = new NavBar(driver);
		signUpLogin = new SignUpLogin(driver);
		basePage = new BasePage(driver);
		comFunc = new CommonFunctions(driver);
		LogUtil.debug("Set up successfully");
	}
	
	@Test (groups = {"smoke"}, priority = 0)
	public void verifyLoginHeader() {
		
		LogUtil.info("[TEST STARTED]: Verifying Login form header is visible.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Sign up / Login");
		navBar.getSignUpLoginNav().click();
		
		wait.until(ExpectedConditions.visibilityOf(signUpLogin.getLoginFormH2()));
		Assert.assertTrue(signUpLogin.getLoginFormH2().isDisplayed());
		LogUtil.info("Login form header is visible");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void verifyLoginFunctionality() {
		
		LogUtil.info("[TEST STARTED]: Verifying Login fields can be populated and user is logged in successfully.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Sign up / Login");
		navBar.getSignUpLoginNav().click();
		wait.until(ExpectedConditions.visibilityOf(signUpLogin.getLoginFormH2()));
		
		String email = ConfigManager.getEmail();
		String pass = ConfigManager.getPassword();
		
		comFunc.enterLoginDetails(email, pass);
		
		WebElement loginEmailInput = signUpLogin.getLoginEmailInput();
		Assert.assertEquals(loginEmailInput.getAttribute("value"), email);

		WebElement password = signUpLogin.getPasswordInput();
		Assert.assertEquals(password.getAttribute("value"), pass);
		
		comFunc.submitLoginDetails();
		
		wait.until(ExpectedConditions.visibilityOf(navBar.getLoggedInAs()));
		Assert.assertTrue(navBar.getLoggedInAs().isDisplayed());
		LogUtil.info("User logged in successfully");
		
		LogUtil.info("[TEST COMPLETED]");

	}
	
	@Test(groups = {"smoke"}, priority = 2)
	public void verifyLogoutFunctionality() {
		
		LogUtil.info("[TEST STARTED]: Verifying user can log out successfully.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Sign up / Login");
		navBar.getSignUpLoginNav().click();
		wait.until(ExpectedConditions.visibilityOf(signUpLogin.getLoginFormH2()));
		
		String email = ConfigManager.getEmail();
		String pass = ConfigManager.getPassword();
		
		comFunc.login(email, pass);
		wait.until(ExpectedConditions.visibilityOf(navBar.getLoggedInAs()));
		LogUtil.info("User logged in");
		comFunc.logout();
		LogUtil.info("Logging user out");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.getLoginUrl()));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getLoginUrl());
		LogUtil.info("User logged out successfully");
		
		LogUtil.info("[TEST COMPLETED]");
		
		
	}
}
