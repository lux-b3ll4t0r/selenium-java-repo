package tests.smoke.checkout;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.cart.Cart;
import pages.checkout.Checkout;
import pages.homepage.FeaturedItems;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.ConfigManager;
import utils.LogUtil;

public class CheckoutDisplayTest extends BaseTest{

	private FeaturedItems featuredItems;
	private NavBar navBar;
	private SignUpLogin login;
	private Cart cart;
	private Checkout checkout;

	
	@BeforeMethod(alwaysRun = true)
	public void setupResources() {
		LogUtil.debug("Setting up test resources");
		featuredItems = new FeaturedItems(driver);
		navBar = new NavBar(driver);
		login = new SignUpLogin(driver);
		cart = new Cart(driver);
		checkout = new Checkout(driver);
		LogUtil.debug("Set up successfully");
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void checkOutVisibleTest() {
		
		LogUtil.info("[TEST STARTED]: Verifying items are visible during checkout.");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
	
		LogUtil.info("Logging in user since checking out is only available to logged in users.");
		String email = ConfigManager.getEmail();
		String pass = ConfigManager.getPassword();
		
		navBar.clickSignUpLoginNav();
		login.login(email, pass);
		
		LogUtil.info("Adding item to cart and navigating to cart");
		featuredItems.clickAddToCartBtn();
		featuredItems.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout");
		cart.clickCheckOutBtn();
		
		Assert.assertTrue(checkout.isDeliveryAddressVisible(), "Delivery address is not visible");
		LogUtil.info("Delivery address is visible");
		
		Assert.assertTrue(checkout.isBillingAddressVisible(), "Billing address is not visible");
		LogUtil.info("Billing address is visible");
		
		Assert.assertTrue(checkout.isCartInfoVisible(), "Cart info is not visible");
		LogUtil.info("Cart info is visible");
		
		Assert.assertTrue(checkout.isOrderMsgVisible(), "Order message is not visible");
		LogUtil.info("Order message is visible");
		
		Assert.assertTrue(checkout.isPlaceOrderBtnVisible(), "Place order button is not visible");
		LogUtil.info("Place order button is visible");
		
		LogUtil.info("[TEST COMPLETED]");
	}
}
