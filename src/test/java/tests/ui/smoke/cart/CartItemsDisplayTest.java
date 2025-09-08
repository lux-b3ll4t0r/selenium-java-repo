package tests.ui.smoke.cart;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.UIBaseTest;
import constants.ui.UrlConstants;
import pages.cart.Cart;
import pages.homepage.FeaturedItems;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class CartItemsDisplayTest extends UIBaseTest{
	
	private FeaturedItems featuredItems;
	private Cart cart;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		featuredItems = new FeaturedItems();
		cart = new Cart();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	@Test (groups = {"smoke"})
	public void cart_item_added_visibility_test() {
		
		LogUtil.info("* Verifying items added to cart are visible.");
		
		featuredItems.clickAddToCartBtn();
		LogUtil.info("Adding item to Cart.");
		
		LogUtil.info("Navigating to Cart.");
		featuredItems.clickPopupViewCart();
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.CART);
		LogUtil.debug("Directed to Cart url successfully.");
		
		Assert.assertTrue(cart.isCartInfoVisible());
		LogUtil.info("Added items are visible in Cart.");
		
		Assert.assertTrue(cart.isCheckOutBtnVisible());
		LogUtil.info("Proceed to Checkout button is visible.");
		

	}
}
