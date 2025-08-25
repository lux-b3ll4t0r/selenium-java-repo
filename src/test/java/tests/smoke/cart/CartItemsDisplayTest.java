package tests.smoke.cart;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.cart.Cart;
import pages.homepage.FeaturedItems;
import utils.ConfigManager;
import utils.LogUtil;

@Listeners(utils.TestListener.class)
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
	public void verifying_added_items_() {
		
		LogUtil.info("* Verifying items added to cart are visible.");
		
		LogUtil.info("Navigating to: " + ConfigManager.getBaseUrl());
		driver.get(ConfigManager.getBaseUrl());
		
		featuredItems.clickAddToCartBtn();
		LogUtil.info("Adding item to Cart.");
		
		LogUtil.info("Navigating to Cart.");
		featuredItems.clickPopupViewCart();
		
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getCartUrl());
		LogUtil.debug("Directed to Cart url successfully.");
		
		Assert.assertTrue(cart.isCartInfoVisible());
		LogUtil.info("Added items are visible in Cart.");
		
		Assert.assertTrue(cart.isCheckOutBtnVisible());
		LogUtil.info("Proceed to Checkout button is visible.");
		

	}
}
