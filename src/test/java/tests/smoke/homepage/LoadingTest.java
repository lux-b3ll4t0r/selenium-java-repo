package tests.smoke.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		homePage = new HomePage();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	@Test(groups = {"smoke"})
	public void homepage_loading_test() {
		LogUtil.info("* Verifying Homepage loads successfully.");

		LogUtil.debug("Expected URL: {}, Actual URL: {}", UrlConstants.BASE, Webtool.getCurrentUrl());
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.BASE, "Current url doesn't match home url.");
			
		Assert.assertTrue(homePage.isHomePageVisible(), "Element(s) not visible in the homepage.");
		LogUtil.info("All main elements of the homepage are visible.");
	}
}
