package e2e;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
import ui.pages.payment.Payment;
import ui.pages.signup_login.SignUpLogin;
import ui.utils.Webtool;

public class CheckoutE2ETest extends E2EBaseTest{
	
	private Homepage homepage;
	private ProductDetails productDetails;
	private SignUpLogin login;
	private Cart cart;
	private Checkout checkout;
	private Payment payment;
	private List<User> createdUsers = new ArrayList<>();
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		homepage = new Homepage();
		productDetails = new ProductDetails();
		login = new SignUpLogin();
		cart = new Cart();
		checkout = new Checkout();
		payment = new Payment();
	}
	
	@Test (groups = {"E2E"}, priority = 0)
	public void verify_checkout_e2e_test() {
		User user = UserDataGenerator.randomUser();
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(user));
		createdUsers.add(user);
		
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
		
		homepage.navigateToLogin();
		login.login(user.getEmail(), user.getPassword());
		
		LogUtil.info("Adding item to cart and navigating to cart.");
		productDetails.clickAddToCartBtn();
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		LogUtil.info("Placing order.");
		checkout.placeOrder();
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.PAYMENT);
		LogUtil.info("Directed to payment page successfully.");
		
		LogUtil.info("Submitting payment information.");
		payment.submitPaymentInfo();
		
		LogUtil.info("Verifying order was placed.");
		Assert.assertTrue(payment.isOrderPlacedVisible());
		
		LogUtil.info("Order was placed successfully.");
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanUpUsers() {
		LogUtil.info("Cleaning up users.");
		AccountApi.cleanUpUsers(createdUsers);
	}
}
