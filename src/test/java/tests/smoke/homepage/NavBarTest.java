package tests.smoke.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		navBar = new NavBar();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	@Test(groups = {"smoke"})
	public void home_link_visible_and_clickable_test() {
		LogUtil.info("* Verifying Home link is visible and clickable.");

		Assert.assertTrue(navBar.isHomeNavVisible());
		LogUtil.info("Home link is visible");
		
		navBar.clickHomeNav();
		LogUtil.info("Home link is clickable");
		
		String baseUrl = UrlConstants.BASE;
		
		Webtool.waitForUrlToBe(baseUrl);
		LogUtil.info("Directed to homepage: [" + baseUrl + "] successfully");
		

	}
	
	@Test(groups = {"smoke"})
	public void products_link_visible_and_clickable_test() {
		LogUtil.info("* Verifying Products link is visible and clickable.");
		
		Assert.assertTrue(navBar.isProductsNavVisible(), "Products link is not visible.");
		LogUtil.info("Products link is visible");
		
		navBar.clickProductsNav();
		LogUtil.info("Products link is clickable");
		
		String productsUrl = UrlConstants.PRODUCTS;
		
		Webtool.waitForUrlToBe(productsUrl);
		LogUtil.info("Directed to Products: " + productsUrl + "] successfully");
		
	}
	
	@Test(groups = {"smoke"})
	public void cart_link_visible_and_clickable_test() {
		LogUtil.info("* Verifying Cart link is visible and clickable.");
		
		Assert.assertTrue(navBar.isCartNavVisible(), "Cart link is not visible.");
		LogUtil.info("Cart link is visible");

		navBar.clickCartNav();
		LogUtil.info("Cart link is clickable");
		
		String cartUrl = UrlConstants.CART;
		
		Webtool.waitForUrlToBe(cartUrl);
		LogUtil.info("Directed to Cart: " + cartUrl + "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void signup_login_visible_and_clickable_test() {
		LogUtil.info("* Verifying Signup/Login link is visible and clickable.");
		
		Assert.assertTrue(navBar.isSignUpLoginNavVisible(), "Signup/Login link is not visible.");
		LogUtil.info("Signup/Login link is visible");

		navBar.clickSignUpLoginNav();
		LogUtil.info("Signup/Login link is clickable");
		
		String loginUrl = UrlConstants.LOGIN;
		
		Webtool.waitForUrlToBe(loginUrl);
		LogUtil.info("Directed to Signup/Login: [" + loginUrl + "] successfully");
		
	}
	
	@Test (groups = {"smoke"})
	public void test_cases_visible_and_clickable_test() {
		LogUtil.info("* Verifying Test Cases link is visible and clickable.");
		
		Assert.assertTrue(navBar.isTestCasesNavVisible(), "Test Cases link is not visible.");
		LogUtil.info("Test Cases link is visible");

		navBar.clickTestCasesNav();
		LogUtil.info("Test Cases link is clickable");
		
		String testCasesUrl = UrlConstants.TEST_CASES;
		
		Webtool.waitForUrlToBe(testCasesUrl);
		LogUtil.info("Directed to Test Cases: [" + testCasesUrl + "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void api_testing_visible_and_clickable_Test() {
		LogUtil.info("* Verifying API Testing link is visible and clickable.");
		
		Assert.assertTrue(navBar.isApiTestingNavVisible(), "API Testing link is not visible.");
		LogUtil.info("API Testing link is visible");
		
		navBar.clickApiTestingNav();
		LogUtil.info("API Testing link is clickable");
		
		String apiTestingUrl = UrlConstants.API_TESTING;
		
		Webtool.waitForUrlToBe(apiTestingUrl);
		LogUtil.info("Directed to API Testing: [" + apiTestingUrl + "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void video_tutorials_visible_and_clickable_test() {
		LogUtil.info("* Verifying Video Tutorials link is visible and clickable.");
		
		Assert.assertTrue(navBar.isVideoTutorialsNavVisible(), "Video Tutorials link is not visible.");
		LogUtil.info("Video Tutorials link is visible");
		
		navBar.clickVideoTutorialsNav();
		LogUtil.info("Video Tutorials link is clickable");
		
		String videoTutUrl = UrlConstants.VIDEO_TUTORIALS;
		
		Webtool.waitForUrlToBe(videoTutUrl);
		LogUtil.info("Directed to Video Tutorials: [" + videoTutUrl + "] successfully");

	}
	
	@Test(groups = {"smoke"})
	public void contact_us_visible_and_clickable_Test() {
		LogUtil.info("* Verifying Contact Us link is visible and clickable.");
		
		Assert.assertTrue(navBar.isContactUsNavVisible(), "Contact Us link is not visiblel");
		LogUtil.info("Contact Us link is visible");
		
		navBar.clickContactUsNav();
		LogUtil.info("Contact Us link is clickable");
		
		String contactUsUrl = UrlConstants.CONTACT_US;
		
		Webtool.waitForUrlToBe(contactUsUrl);
		LogUtil.info("Directed to Contact Us: [" + contactUsUrl + "] successfully");

	}
}
