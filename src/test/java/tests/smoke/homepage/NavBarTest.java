package tests.smoke.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.UrlConstants;
import pages.homepage.NavBar;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class NavBarTest extends BaseTest{
	
	private NavBar navBar;
	
	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() {
		LogUtil.debug("Setting up test resources");
		navBar = new NavBar();
		LogUtil.debug("Set up successfully");
		
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	@Test(groups = {"smoke"})
	public void homeNavBarVisibleAndClickable() {
		LogUtil.info("* Verifying Home navbar link is visible and clickable.");

		Assert.assertTrue(navBar.isHomeNavVisible());
		LogUtil.info("Home nav link is visible");
		
		navBar.clickHomeNav();
		LogUtil.info("Home nav link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.BASE);
		LogUtil.info("Directed to homepage: [" + UrlConstants.BASE + "] successfully");
		

	}
	
	@Test(groups = {"smoke"})
	public void productsNavBarVisibleAndClickable() {
		LogUtil.info("* Verifying Products navbar link is visible and clickable.");
		
		Assert.assertTrue(navBar.isProductsNavVisible());
		LogUtil.info("Products nav link is visible");
		
		navBar.clickProductsNav();
		LogUtil.info("Products nav link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.PRODUCTS);
		LogUtil.info("Directed to Products: " + UrlConstants.PRODUCTS + "] successfully");
		
	}
	
	@Test(groups = {"smoke"})
	public void cart_navigation_test() {
		LogUtil.info("* Verifying Cart navbar link is visible and clickable.");
		
		Assert.assertTrue(navBar.isCartNavVisible());
		LogUtil.info("Cart nav link is visible");

		navBar.clickCartNav();
		LogUtil.info("Cart nav link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.CART);
		LogUtil.info("Directed to Cart: " + UrlConstants.CART + "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void signup_login_navigation_test() {
		LogUtil.info("* Verifying Signup Login navbar link is visible and clickable.");
		
		Assert.assertTrue(navBar.isSignUpLoginNavVisible());
		LogUtil.info("Signup Login nav link is visible");

		navBar.clickSignUpLoginNav();
		LogUtil.info("Signup Login nav link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.LOGIN);
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.LOGIN);
		LogUtil.info("Directed to Signup / Login: [" + UrlConstants.LOGIN + "] successfully");
		
	}
	
	@Test (groups = {"smoke"})
	public void test_cases_navigation_test() {
		LogUtil.info("* Verifying Test Cases navbar link is visible and clickable.");
		
		Assert.assertTrue(navBar.isTestCasesNavVisible());
		LogUtil.info("Test Cases nav link is visible");

		navBar.clickTestCasesNav();
		LogUtil.info("Test Cases nav link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.TEST_CASES);
		LogUtil.info("Directed to Test Cases: [" + UrlConstants.TEST_CASES + "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void api_testing_navigation_Test() {
		LogUtil.info("* Verifying API Testing navbar link is visible and clickable.");
		
		Assert.assertTrue(navBar.isApiTestingNavVisible());
		LogUtil.info("API Testing nav link is visible");
		
		navBar.clickApiTestingNav();
		LogUtil.info("API Testing nav link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.API_TESTING);
		LogUtil.info("Directed to API Testing: [" + UrlConstants.API_TESTING + "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void video_tutorials_navigation_test() {
		LogUtil.info("* Verifying Video Tutorials navbar link is visible and clickable.");
		
		Assert.assertTrue(navBar.isVideoTutorialsNavVisible());
		LogUtil.info("Video Tutorials navbar link is visible");
		
		navBar.clickVideoTutorialsNav();
		LogUtil.info("Video Tutorials navbar link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.VIDEO_TUTORIALS);
		LogUtil.info("Directed to Video Tutorials: [" + UrlConstants.VIDEO_TUTORIALS+ "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void contact_us_navigation_Test() {
		LogUtil.info("* Verifying Contact Us navbar link is visible and clickable.");
		
		Assert.assertTrue(navBar.isContactUsNavVisible());
		LogUtil.info("Contact Us navbar link is visible");
		
		navBar.clickContactUsNav();
		LogUtil.info("Contact Us navbar link is clickable");
		
		Webtool.waitForUrlToBe(UrlConstants.CONTACT_US);
		LogUtil.info("Directed to Contact Us: [" + UrlConstants.CONTACT_US + "] successfully");

	}
}
