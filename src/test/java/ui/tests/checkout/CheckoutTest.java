package ui.tests.checkout;


import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.services.AccountApi;
import api.utils.ResponseValidator;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import ui.constants.UrlConstants;
import ui.pages.cart.Cart;
import ui.pages.checkout.Checkout;
import ui.pages.common.ProductDetails;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.SignUpLogin;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;


public class CheckoutTest extends UIBaseTest{

	private Homepage homepage;
	private ProductDetails productDetails;
	private SignUpLogin login;
	private Cart cart;
	private Checkout checkout;
	private List<User> createdUsers = new ArrayList<>();
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		homepage = new Homepage();
		productDetails = new ProductDetails();
		login = new SignUpLogin();
		cart = new Cart();
		checkout = new Checkout();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		User user = UserDataGenerator.randomUser();
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(user));
		createdUsers.add(user);
		
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
		
		String email = user.getEmail();
		String pass = user.getPassword();
		homepage.navigateToLogin();
		login.login(email, pass);
	}
	
	@Test(groups = {"smoke"})
	public void checkout_loads_smoke_test() {
		LogUtil.info("Verifying check out page loads successfully.");

		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		checkout.validateCheckoutLoads();
		LogUtil.info("Checkout page loaded successfully.");
	}
	
	@Test(groups = {"smoke"})
	public void place_order_smoke_test() {
		LogUtil.info("Verifying placing order directs to payment page.");

		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		LogUtil.info("Placing order.");
		checkout.placeOrder();
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.PAYMENT, "Didn't direct to payment page.");
		LogUtil.info("Directed to payment page successfully.");

	}
	
	@Test(groups = {"functional"})
	public void checkout_delivery_address_functional_test() {
		LogUtil.info("Verifying address details are visible at checkout.");
		
		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		LogUtil.info("Verifying delivery address details are visible.");
		checkout.validateDeliveryDetails();
		checkout.validateBillingDetails();
		LogUtil.info("Delivey details are visible.");
	}
	
	@Test(groups = {"functional"})
	public void checkout_item_details_functional_test() {
		LogUtil.info("Verifying cart item details are visible at checkout.");
	
		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		LogUtil.info("Verifying cart item details are visible.");
		checkout.validateCartItemDetails();
		LogUtil.info("Cart item details visible.");
	}
	
	@Test(groups = {"functional"})
	public void checkout_totals_functionality_test() {
		LogUtil.info("Verifying product and order totals are calculated correctly.");
		
		LogUtil.info("Adding items to cart and navigating to cart.");
		productDetails.addItemsToCart(2);
		Webtool.get(UrlConstants.CART);
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		checkout.validateProductTotal();
		checkout.validateOrderTotal();
		LogUtil.info("Product totals and and order total calculated successfully.");
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanUpUsers() {
		LogUtil.info("Cleaning up users.");
		AccountApi.cleanUpUsers(createdUsers);
	}
}
