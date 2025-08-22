package tests.smoke.homepage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.NavBar;
import utils.ConfigManager;
import utils.LogUtil;

public class NavBarTest extends BaseTest{
	
	private NavBar navBar;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.info("Setting up test resources");
		navBar = new NavBar(driver);
		LogUtil.info("Set up successfully");
	}
	
	@Test(groups = {"smoke"})
	public void homeNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Home navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());

		Assert.assertTrue(navBar.isHomeNavVisible());
		LogUtil.info("Home nav link is visible");
		
		navBar.clickHomeNav();
		LogUtil.info("Home nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.getBaseUrl()));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getBaseUrl());
		LogUtil.info("Directed to homepage successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void productsNavBarVisibleAndClickable() {
		
		LogUtil.info("[TEST STARTED]: Verifying Products navbar link is visible and clickable.");
		
		String productsUrl = ConfigManager.getProductsUrl();
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());

		Assert.assertTrue(navBar.isProductsNavVisible());
		LogUtil.info("** Products nav link is visible **");
		
		navBar.clickProductsNav();
		LogUtil.info("** Products nav link is clickable **");
		
		wait.until(ExpectedConditions.urlToBe(productsUrl));
		Assert.assertEquals(driver.getCurrentUrl(), productsUrl);
		LogUtil.info("Directed to Products successfully");
		
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void cartNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Cart navbar link is visible and clickable.");
		
		String cartUrl = ConfigManager.getCartUrl();
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		Assert.assertTrue(navBar.isCartNavVisible());
		LogUtil.info("Cart nav link is visible");

		navBar.clickCartNav();
		LogUtil.info("Cart nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(cartUrl));
		Assert.assertEquals(driver.getCurrentUrl(), cartUrl);	
		LogUtil.info("Directed to Cart successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void signUpLoginNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Signup Login navbar link is visible and clickable.");
		
		String loginUrl = ConfigManager.getLoginUrl();
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		Assert.assertTrue(navBar.isSignUpLoginNavVisible());
		LogUtil.info("Signup Login nav link is visible");

		navBar.clickSignUpLoginNav();
		LogUtil.info("Signup Login nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(loginUrl));
		Assert.assertEquals(driver.getCurrentUrl(), loginUrl);
		LogUtil.info("Directed to Signup / Login successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test (groups = {"smoke"})
	public void testCasesNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Test Cases navbar link is visible and clickable.");
		
		String testCasesUrl = ConfigManager.getTestCasesUrl();
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		Assert.assertTrue(navBar.isTestCasesNavVisible());
		LogUtil.info("Test Cases nav link is visible");

		navBar.clickTestCasesNav();
		LogUtil.info("Test Cases nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(testCasesUrl));
		Assert.assertEquals(driver.getCurrentUrl(), testCasesUrl);
		LogUtil.info("Directed to Test Cases successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void apiTestingNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying API Testing navbar link is visible and clickable.");
		
		String apiTestingUrl = ConfigManager.getApiTestingUrl();
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		Assert.assertTrue(navBar.isApiTestingNavVisible());
		LogUtil.info("API Testing nav link is visible");
		
		navBar.clickApiTestingNav();
		LogUtil.info("API Testing nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(apiTestingUrl));
		Assert.assertEquals(driver.getCurrentUrl(), apiTestingUrl);
		LogUtil.info("Directed to API Testing successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void videoTutorialsNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Video Tutorials navbar link is visible and clickable.");
		
		String videoTutorialsUrl = ConfigManager.getVideoTutorialsUrl();
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		Assert.assertTrue(navBar.isVideoTutorialsNavVisible());
		LogUtil.info("Video Tutorials navbar link is visible");
		
		navBar.clickVideoTutorialsNav();
		LogUtil.info("Video Tutorials navbar link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(videoTutorialsUrl));
		Assert.assertEquals(driver.getCurrentUrl(), videoTutorialsUrl);
		LogUtil.info("Directed to Video Tutorials successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void contactUsNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Contact Us navbar link is visible and clickable.");
		
		String contactUsUrl = ConfigManager.getContactUsUrl();
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		Assert.assertTrue(navBar.isContactUsNavVisible());
		LogUtil.info("Contact Us navbar link is visible");
		
		navBar.clickContactUsNav();
		LogUtil.info("Contact Us navbar link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(contactUsUrl));
		Assert.assertEquals(driver.getCurrentUrl(), contactUsUrl);
		LogUtil.info("Directed to Contact Us successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
}
