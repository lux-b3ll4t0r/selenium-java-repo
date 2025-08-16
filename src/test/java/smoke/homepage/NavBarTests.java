package smoke.homepage;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.NavBar;
import utils.ConfigManager;
import utils.LogUtil;

public class NavBarTests extends BaseTest{
	
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
		WebElement homeNavLocal = navBar.getHomeNav();
		
		Assert.assertTrue(homeNavLocal.isDisplayed());
		LogUtil.info("Home nav link is visible");
		
		homeNavLocal.click();
		LogUtil.info("Home nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.getBaseUrl()));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getBaseUrl());
		LogUtil.info("Directed to homepage successfully");
		
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void productsNavBarVisibleAndClickable() {
		
		LogUtil.info("[TEST STARTED]: Verifying Products navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		WebElement productsNavLocal = navBar.getProductsNav();

		Assert.assertTrue(productsNavLocal.isDisplayed());
		LogUtil.info("** Products nav link is visible **");
		
		productsNavLocal.click();
		LogUtil.info("** Products nav link is clickable **");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("productsUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.get("productsUrl"));
		LogUtil.info("Directed to Products successfully");
		
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void cartNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Cart navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		WebElement cartNavLocal = navBar.getCartNav();
		
		Assert.assertTrue(cartNavLocal.isDisplayed());
		LogUtil.info("Cart nav link is visible");

		cartNavLocal.click();
		LogUtil.info("Cart nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("cartUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.get("cartUrl"));	
		LogUtil.info("Directed to Cart successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void signUpLoginNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Signup Login navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		WebElement signUpLoginNavLocal = navBar.getSignUpLoginNav();
		
		Assert.assertTrue(signUpLoginNavLocal.isDisplayed());
		LogUtil.info("Signup Login nav link is visible");

		signUpLoginNavLocal.click();
		LogUtil.info("Signup Login nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("signUpLoginUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.get("signUpLoginUrl"));
		LogUtil.info("Directed to Signup / Login successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test (groups = {"smoke"})
	public void testCasesNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Test Cases navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		WebElement testCasesNavLocal = navBar.getTestCasesNav();
		
		Assert.assertTrue(testCasesNavLocal.isDisplayed());
		LogUtil.info("Test Cases nav link is visible");

		testCasesNavLocal.click();
		LogUtil.info("Test Cases nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("testCasesUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.get("testCasesUrl"));
		LogUtil.info("Directed to Test Cases successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void apiTestingNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying API Testing navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		WebElement apiTestingNavLocal = navBar.getApiTestingNav();
		
		Assert.assertTrue(apiTestingNavLocal.isDisplayed());
		LogUtil.info("API Testing nav link is visible");
		
		apiTestingNavLocal.click();
		LogUtil.info("API Testing nav link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("apiTestingUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.get("apiTestingUrl"));
		LogUtil.info("Directed to API Testing successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void videoTutorialsNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Video Tutorials navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		WebElement videoTutorialsNavLocal = navBar.getVideoTutorialsNav();
		
		Assert.assertTrue(videoTutorialsNavLocal.isDisplayed());
		LogUtil.info("Video Tutorials navbar link is visible");
		
		videoTutorialsNavLocal.click();
		LogUtil.info("Video Tutorials navbar link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("videoTutorialsUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.get("videoTutorialsUrl"));
		LogUtil.info("Directed to Video Tutorials successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
	
	@Test(groups = {"smoke"})
	public void contactUsNavBarVisibleAndClickable() {
		LogUtil.info("[TEST STARTED]: Verifying Contact Us navbar link is visible and clickable.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		WebElement contactUsNavLocal = navBar.getContactUsNav();
		
		Assert.assertTrue(contactUsNavLocal.isDisplayed());
		LogUtil.info("Contact Us navbar link is visible");
		
		contactUsNavLocal.click();
		LogUtil.info("Contact Us navbar link is clickable");
		
		wait.until(ExpectedConditions.urlToBe(ConfigManager.get("contactUsUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.get("contactUsUrl"));
		LogUtil.info("Directed to Contact Us successfully");
		
		LogUtil.info("[TEST COMPLETED]");
	}
}
