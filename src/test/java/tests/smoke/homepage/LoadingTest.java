package tests.smoke.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.ConfigManager;
import utils.LogUtil;

public class LoadingTest extends BaseTest {

	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.debug("Setting up test resources");
		LogUtil.debug("Set up successfully");
	}
	
	@Test(groups = {"smoke"})
	public void testHomePageLoads() {
		LogUtil.info("[TEST STARTED]: Verifying Homepage loads successfully.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());

		LogUtil.debug("Expected URL: {}, Actual URL: {}", ConfigManager.getBaseUrl(),driver.getCurrentUrl());
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getBaseUrl());
		LogUtil.info("Current url matches expected url");
		
		LogUtil.debug("Expected title: {Automation Exercise}, Actual title: {" + driver.getTitle() + "}");
		Assert.assertEquals(driver.getTitle(), "Automation Exercise");
		LogUtil.info("Title matches expected title");
		
		LogUtil.info("[TEST COMPLETED]");
	}
}
