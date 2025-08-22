package tests.smoke.cart;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.cart.Cart;
import pages.homepage.FeaturedItems;
import utils.ConfigManager;
import utils.LogUtil;

public class CartItemsDisplayTest extends BaseTest{
	
	private FeaturedItems featuredItems;
	private Cart cart;

	
	@BeforeMethod(alwaysRun = true)
	public void setupResources() {
		LogUtil.debug("Setting up test resources");
		featuredItems = new FeaturedItems(driver);
		cart = new Cart(driver);
		LogUtil.debug("Set up successfully");
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void verifyCartItemsVisible() {
		
		LogUtil.info("[TEST STARTED]: Verifying items added to cart are visible.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		featuredItems.clickAddToCartBtn();
		LogUtil.info("Adding item to cart");
		
		LogUtil.info("Navigating to Cart");
		featuredItems.clickPopupViewCart();
		
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getCartUrl());
		LogUtil.debug("Directed to Cart url successfully");
		
		Assert.assertTrue(cart.isCartInfoVisible());
		LogUtil.info("Added items are visible in cart.");
		
		Assert.assertTrue(cart.isCheckOutBtnVisible());
		LogUtil.info("Proceed to checkout button is visible.");
		
		LogUtil.info("[TEST COMPLETED]");
	}
}
