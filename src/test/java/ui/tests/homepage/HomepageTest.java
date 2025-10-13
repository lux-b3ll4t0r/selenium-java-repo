package ui.tests.homepage;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.common.ProductDetails;
import ui.pages.homepage.Homepage;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

public class HomepageTest extends UIBaseTest {
	
	private Homepage homepage;
	private ProductDetails productDetails;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
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
		LogUtil.info("Verifying Homepage loads successfully.");

		LogUtil.debug("Expected URL: {}, Actual URL: {}", UrlConstants.HOMEPAGE, Webtool.getCurrentUrl());
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.HOMEPAGE, "Current url doesn't match home url.");
			
		Assert.assertTrue(homepage.isHomePageVisible(), "Element(s) not visible in the homepage.");
		LogUtil.info("All main elements of the homepage are visible.");
	}
	
	@Test(groups = {"smoke"}, dataProvider = "navbarLinks")
	public void navigation_smoke_test(By nav, String url) {
		LogUtil.info("Verifying successful navigation of navbar.");
		
		LogUtil.info("Navigating to: " + url);
		homepage.navigateTo(nav);;
		
		Webtool.waitForUrlToBe(url);
		LogUtil.info("Directed to: " + url + " successfully.");
	}
	
	@DataProvider (name = "navbarLinks")
	public Object[][] navbarLinks(){
		return new Object[][] {
			{homepage.getProductsNav(), UrlConstants.PRODUCTS},
			{homepage.getHomeNav(), UrlConstants.HOMEPAGE},
			{homepage.getCartNav(), UrlConstants.CART},
			{homepage.getSignUpLoginNav(), UrlConstants.LOGIN},
			{homepage.getTestCasesNav(), UrlConstants.TEST_CASES},
			{homepage.getApiTestingNav(), UrlConstants.API_TESTING},
			{homepage.getVideoTutorialsNav(), UrlConstants.VIDEO_TUTORIALS},
			{homepage.getContactUsNav(), UrlConstants.CONTACT_US},
		};
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void product_list_smoke_test() {
		LogUtil.info("Verifying all products are listed in the Homepage.");
		
		Assert.assertTrue(productDetails.isProductsListVisible(), "Product list not visible");
		LogUtil.info("Product list visible.");
	}
	
	@Test(groups = {"functional"}, priority = 2)
	public void product_details_functional_test() {
		LogUtil.info("Verifying product details are visible.");	
		Assert.assertTrue(productDetails.isProductInfoVisible(), "Product information not visible.");
		
		productDetails.moveCursorToProduct();
		Assert.assertTrue(productDetails.isProductOverlayInfoVisible(), "Overlay information not visible.");
		LogUtil.info("Product details and overlay details are visible.");
	}


}
