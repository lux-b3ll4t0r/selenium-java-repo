package ui.tests.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.common.ProductDetails;
import ui.pages.homepage.Homepage;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

@Listeners(common.listeners.TestListener.class)
public class HomepageTest extends UIBaseTest {
	
	private Homepage homepage;
	private ProductDetails productDetails;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		homepage = new Homepage();
		productDetails = new ProductDetails();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void homepage_loading_smoke_test() {
		LogUtil.info("* Verifying Homepage loads successfully.");

		LogUtil.debug("Expected URL: {}, Actual URL: {}", UrlConstants.HOMEPAGE, Webtool.getCurrentUrl());
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.HOMEPAGE, "Current url doesn't match home url.");
			
		Assert.assertTrue(homepage.isHomePageVisible(), "Element(s) not visible in the homepage.");
		LogUtil.info("All main elements of the homepage are visible.");
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void product_list_smoke_test() {
		LogUtil.info("* Verifying all products are listed in the Homepage.");
		
		Assert.assertTrue(productDetails.isProductsListVisible(), "Product list not visible");
		LogUtil.info("Product list visible.");
	}
	
	@Test(groups = {"functional"}, priority = 2)
	public void product_details_functional_test() {
		LogUtil.info("* Verifying product details are visible.");	
		Assert.assertTrue(productDetails.isProductInfoVisible(), "Product information not visible.");
		
		productDetails.moveCursorToProduct();
		Assert.assertTrue(productDetails.isProductOverlayInfoVisible(), "Overlay information not visible.");
		LogUtil.info("Product details and overlay details are visible.");
	}
	
	@Test(groups = {"smoke"})
	public void home_link_smoke_test() {
		LogUtil.info("* Verifying navigation to Homepage.");

		LogUtil.info("Navigating to homepage.");
		homepage.navigateToHome();
		
		String baseUrl = UrlConstants.HOMEPAGE;
		
		Webtool.waitForUrlToBe(baseUrl);
		LogUtil.info("Navigated to homepage successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void products_link_smoke_test() {
		LogUtil.info("* Verifying navigation to Products.");
		
		LogUtil.info("Navigating to Products.");
		homepage.navigateToProducts();
		
		String productsUrl = UrlConstants.PRODUCTS;
		
		Webtool.waitForUrlToBe(productsUrl);
		LogUtil.info("Navigated to Products successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void cart_link_smoke_test() {
		LogUtil.info("* Verifying navigation to Cart.");

		LogUtil.info("Navigating to Cart.");
		homepage.navigateToCart();
		
		String cartUrl = UrlConstants.CART;
		
		Webtool.waitForUrlToBe(cartUrl);
		LogUtil.info("Navigated to Cart successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void signup_login_link_smoke_test() {
		LogUtil.info("* Verifying navigation to Signup/Login.");
		
		LogUtil.info("Navigating to Signup/Login.");
		homepage.navigateToLogin();
		
		String loginUrl = UrlConstants.LOGIN;
		
		Webtool.waitForUrlToBe(loginUrl);
		LogUtil.info("Navigated to Signup/Login successfully.");
	}
	
	@Test (groups = {"smoke"})
	public void test_cases_smoke_test() {
		LogUtil.info("* Verifying navigation to Test Cases.");

		LogUtil.info("Navigating to Test Cases.");
		homepage.navigateToTestCases();
		
		String testCasesUrl = UrlConstants.TEST_CASES;
		
		Webtool.waitForUrlToBe(testCasesUrl);
		LogUtil.info("Navigated to Test Cases successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void api_testing_smoke_Test() {
		LogUtil.info("* Verifying navigation to Api Testing.");		
		
		LogUtil.info("Navigating to Api Testing.");
		homepage.navigateToApiTesting();
		
		String apiTestingUrl = UrlConstants.API_TESTING;
		
		Webtool.waitForUrlToBe(apiTestingUrl);
		LogUtil.info("Navigated to API Testing successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void video_tutorials_smoke_test() {
		LogUtil.info("* Verifying navigation to Video Tutorials.");
		
		LogUtil.info("Navigating to Video Tutorials.");
		homepage.navigateToVideoTutorials();
		
		String videoTutUrl = UrlConstants.VIDEO_TUTORIALS;
		
		Webtool.waitForUrlToBe(videoTutUrl);
		LogUtil.info("Directed to Video Tutorials successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void contact_us_smoke_test() {
		LogUtil.info("* Verifying navigation to Contact Us.");
		
		LogUtil.info("Navigating to Contact Us.");
		homepage.navigateToContactUs();
		
		String contactUsUrl = UrlConstants.CONTACT_US;
		
		Webtool.waitForUrlToBe(contactUsUrl);
		LogUtil.info("Directed to Contact Us successfully.");
	}
}
