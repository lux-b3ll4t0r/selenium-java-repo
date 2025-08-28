package tests.smoke.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.UrlConstants;
import pages.homepage.HomePage;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class LoadingTest extends BaseTest {
	
	private HomePage homePage;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.trace("Setting up test resources");
		homePage = new HomePage();
		LogUtil.trace("Set up successfully");
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	@Test(groups = {"smoke"})
	public void homepage_loading_test() {
		LogUtil.info("* Verifying Homepage loads successfully.");

		LogUtil.debug("Expected URL: {}, Actual URL: {}", UrlConstants.BASE, Webtool.getCurrentUrl());
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.BASE, "Current url doesn't match base url.");
			
		Assert.assertTrue(homePage.isHomePageVisible());
		LogUtil.info("All main elements of the homepage are visible. " + homePage.getBody());
	}
}
