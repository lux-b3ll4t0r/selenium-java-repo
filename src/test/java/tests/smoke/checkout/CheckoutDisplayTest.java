package tests.smoke.checkout;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.UrlConstants;
import pages.cart.Cart;
import pages.checkout.Checkout;
import pages.homepage.FeaturedItems;
import pages.homepage.NavBar;
import pages.signup_login.SignUpLogin;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class CheckoutDisplayTest extends BaseTest{

	private FeaturedItems featuredItems;
	private NavBar navBar;
	private SignUpLogin login;
	private Cart cart;
	private Checkout checkout;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		featuredItems = new FeaturedItems();
		navBar = new NavBar();
		login = new SignUpLogin();
		cart = new Cart();
		checkout = new Checkout();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {	
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void checkout_details_visibility_test() {
		LogUtil.info("* Verifying added items and customer details are visible during checkout.");
	
		LogUtil.info("Logging in user.");
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		
		navBar.clickSignUpLoginNav();
		login.login(email, pass);
		
		LogUtil.info("Adding item to cart and navigating to cart.");
		featuredItems.clickAddToCartBtn();
		featuredItems.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		Assert.assertTrue(checkout.isDeliveryAddressVisible(), "Delivery address is not visible.");
		LogUtil.info("Delivery address is visible.");
		
		Assert.assertTrue(checkout.isBillingAddressVisible(), "Billing address is not visible.");
		LogUtil.info("Billing address is visible.");
		
		Assert.assertTrue(checkout.isCartInfoVisible(), "Item info is not visible.");
		LogUtil.info("Item info is visible.");
		
		Assert.assertTrue(checkout.isOrderMsgVisible(), "Order message is not visible.");
		LogUtil.info("Order message is visible.");
		
		Assert.assertTrue(checkout.isPlaceOrderBtnVisible(), "Place order button is not visible.");
		LogUtil.info("Place order button is visible.");

	}
}
