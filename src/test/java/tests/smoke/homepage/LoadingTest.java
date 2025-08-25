package tests.smoke.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import utils.ConfigManager;
import utils.LogUtil;

@Listeners(utils.TestListener.class)
public class LoadingTest extends BaseTest {
	
	private HomePage homePage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.debug("Setting up test resources");
		homePage = new HomePage(driver);
		LogUtil.debug("Set up successfully");
	}
	
	@Test(groups = {"smoke"})
	public void testHomePageLoads() {
		LogUtil.info("* Verifying Homepage loads successfully.");
		
		LogUtil.info("Navigating to: " + ConfigManager.getBaseUrl());
		driver.get(ConfigManager.getBaseUrl());
		

		LogUtil.debug("Expected URL: {}, Actual URL: {}", ConfigManager.getBaseUrl(),driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getBaseUrl(), "Current url doesn't match base url.");
		
		LogUtil.debug("Expected title: {Automation Exercise}, Actual title: {" + driver.getTitle() + "}");
		Assert.assertEquals(driver.getTitle(), "Automation Exercise");
			
		Assert.assertTrue(homePage.isHomePageVisible());
		LogUtil.info("All main elements of the homepage are visible. " + homePage.getBody());
	}
}
