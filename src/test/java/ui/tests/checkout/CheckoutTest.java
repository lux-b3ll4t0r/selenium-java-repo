package ui.tests.checkout;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.cart.Cart;
import ui.pages.checkout.Checkout;
import ui.pages.common.ProductDetails;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.SignUpLogin;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

@Listeners(common.listeners.TestListener.class)
public class CheckoutTest extends UIBaseTest{

	private Homepage homepage;
	private ProductDetails productDetails;
	private SignUpLogin login;
	private Cart cart;
	private Checkout checkout;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		homepage = new Homepage();
		productDetails = new ProductDetails();
		login = new SignUpLogin();
		cart = new Cart();
		checkout = new Checkout();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {	
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
		
		String email = System.getenv("UI_USER");
		String pass = System.getenv("UI_PASS");
		homepage.navigateToLogin();
		login.login(email, pass);
		
		LogUtil.info("Checking if cart is clear before proceeding.");
		homepage.navigateToCart();
		cart.clearCartIfNotEmpty();
		homepage.navigateToHome();
	}
	
	@Test(groups = {"smoke"})
	public void checkout_loads_smoke_test() {
		LogUtil.info("* Verifying check out page loads successfully.");

		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		SoftAssert softAssert = new SoftAssert();
		checkout.validateCheckoutLoads(softAssert);
		softAssert.assertAll();
		LogUtil.info("Checkout page loaded successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void place_order_smoke_test() {
		LogUtil.info("* Verifying placing order directs to payment page.");

		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		LogUtil.info("Placing order.");
		checkout.placeOrder();
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.PAYMENT);
		LogUtil.info("Directed to payment page successfully.");

	}
	
	@Test(groups = {"functional"})
	public void checkout_delivery_address_functional_test() {
		LogUtil.info("* Verifying address details are visible at checkout.");
		
		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		LogUtil.info("Verifying delivery address details are visible.");
		SoftAssert softAssert = new SoftAssert();
		checkout.validateDeliveryDetails(softAssert);
		checkout.validateBillingDetails(softAssert);
		softAssert.assertAll();
		LogUtil.info("Delivey details are visible.");
	}
	
	@Test(groups = {"functional"})
	public void checkout_item_details_functional_test() {
		LogUtil.info("* Verifying cart item details are visible at checkout.");
	
		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		LogUtil.info("Verifying cart item details are visible.");
		SoftAssert softAssert = new SoftAssert();
		checkout.validateCartItemDetails(softAssert);
		softAssert.assertAll();
		LogUtil.info("Cart item details visible.");
	}
	
	@Test(groups = {"functional"})
	public void checkout_totals_functionality_test() {
		LogUtil.info("* Verifying product and order totals are calculated correctly.");
		
		LogUtil.info("Adding items to cart and navigating to cart.");
		productDetails.addMultipleItemsToCart(2);
		Webtool.get(UrlConstants.CART);
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		SoftAssert softAssert = new SoftAssert();
		checkout.validateProductTotal(softAssert);
		checkout.validateOrderTotal(softAssert);
		softAssert.assertAll();
		LogUtil.info("Product totals and and order total calculated successfully.");
	}
}
