package ui.tests.cart;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import common.pojos.UIProduct;
import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.cart.Cart;
import ui.pages.common.ProductDetails;
import ui.pages.common.ViewProduct;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.SignUpLogin;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

@Listeners(common.listeners.TestListener.class)
public class CartTest extends UIBaseTest{

	private Homepage homepage;
	private ProductDetails productDetails;
	private ViewProduct viewProduct;
	private Cart cart;
	private SignUpLogin login; 
	
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		homepage = new Homepage();
		productDetails = new ProductDetails();
		viewProduct = new ViewProduct();
		cart = new Cart();
		login = new SignUpLogin();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}
	
	@Test (groups = {"smoke"}, priority = 0)
	public void cart_smoke_test() {
		LogUtil.info("* Verifying cart is visible with no items added.");
		
		LogUtil.info("Navigating to Cart.");
		homepage.navigateToCart();
		
		Assert.assertTrue(cart.isCartInfoVisible());
		LogUtil.info("Cart is visible.");
	}
	
	@Test (groups = {"smoke"}, priority = 1)
	public void cart_item_added_smoke_test() {
		LogUtil.info("* Verifying items added to cart are visible.");
		
		LogUtil.info("Adding item to cart.");
		productDetails.clickAddToCartBtn();
		
		LogUtil.info("Navigating to cart.");
		productDetails.clickPopupViewCart();

		Assert.assertTrue(cart.isCartInfoVisible());
		LogUtil.info("Added items are visible in cart.");
	}
	
	@Test (groups = {"smoke"}, priority = 2)
	public void cart_item_removed_smoke_test() {
		LogUtil.info("* Verifying items removed from cart are removed.");
		
		productDetails.clickAddToCartBtn();
		LogUtil.info("Added item to cart.");
		
		LogUtil.info("Navigating to cart.");
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Removing item from cart.");
		cart.removeCartItem();
		
		Assert.assertTrue(cart.isCartEmpty());
		LogUtil.info("Item removed from cart successfully.");
		
	}
	
	@Test (groups = {"smoke"}, priority = 3)
	public void cart_directs_to_checkout_smoke_test() {
		LogUtil.info("* Verifying Cart directs to Checkout when proceeding to checkout.");
		
		homepage.navigateToLogin();
		
		LogUtil.info("Logging user in.");
		login.login();
		
		LogUtil.info("Adding item to cart.");
		productDetails.clickAddToCartBtn();
		
		LogUtil.info("Navigating to cart.");
		productDetails.clickPopupViewCart();
		
		LogUtil.info("Proceeding to checkout.");
		cart.clickCheckOutBtn();
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.CHECKOUT, "Cart did not direct to checkout.");
		LogUtil.info("Directed to checkout successfully.");
		
	}
	
	@Test (groups = {"functional"})
	public void cart_refresh_functional_test() {
		LogUtil.info("* Verifying cart items persist when refreshing the page.");
		
		UIProduct product = productDetails.getProductDetails();
		
		LogUtil.info("Adding item to cart.");
		productDetails.clickAddToCartBtn();
		
		LogUtil.info("Navigating to cart.");
		productDetails.clickPopupViewCart();
		
		UIProduct cartProduct = cart.getProductDetails();

		Assert.assertEquals(cartProduct.getImg(), product.getImg(),"Product image in cart does not match.");
		Assert.assertEquals(cartProduct.getName(),product.getName(), "Product name in cart does not match.");
		Assert.assertEquals(cartProduct.getPrice(), product.getPrice(), "Product image in cart does not match.");
		
		LogUtil.info("Refreshed page retained cart items.");
	}
	
	@Test (groups = {"functional"})
	public void homepage_cart_item_details_functional_test() {
		LogUtil.info("* Verifying product details match when adding to cart from Homepage.");
		
		UIProduct homepageProduct = productDetails.getProductDetails();
		
		LogUtil.info("Adding item to cart.");
		productDetails.clickAddToCartBtn();
		
		LogUtil.info("Navigating to cart.");
		productDetails.clickPopupViewCart();
		
		UIProduct cartProduct = cart.getProductDetails();

		Assert.assertEquals(cartProduct.getImg(), homepageProduct.getImg(),"Product image in cart does not match.");
		Assert.assertEquals(cartProduct.getName(),homepageProduct.getName(), "Product name in cart does not match.");
		Assert.assertEquals(cartProduct.getPrice(), homepageProduct.getPrice(), "Product image in cart does not match.");
		LogUtil.info("Cart product details match product details in homepage.");
	}
	
	@Test (groups = {"functional"})
	public void viewpage_cart_item_details_functional_test() {
		LogUtil.info("* Verifying product details match when adding to cart from View Product page.");
		
		productDetails.clickViewProduct();
		
		List<String> viewProductDetails = viewProduct.getProductDetailsAsListForCart();
	
		viewProduct.clickAddToCart();
		viewProduct.clickViewCartModalLink();

		List<String> cartProductDetails = cart.getProductDetailsAsList();
		Assert.assertEquals(cartProductDetails, viewProductDetails);
		LogUtil.info("Cart product details match View Product page details");
	}
	
	@Test (groups = {"functional"})
	public void cart_mutliple_items_removed_smoke_test() {
		LogUtil.info("* Verifying multiple items can be removed from cart.");
		
		LogUtil.info("Adding multiple items to cart.");
		productDetails.addMultipleItemsToCart(2);;
		
		LogUtil.info("Navigating to cart.");
		Webtool.get(UrlConstants.CART);
		
		LogUtil.info("Removing items from cart.");
		cart.removeAllCartItems();
		
		Assert.assertTrue(cart.isCartEmpty());
		LogUtil.info("All items removed from cart successfully.");
		
	}
	
	@Test (groups = {"functional"})
	public void cart_item_total_functional_test() {
		LogUtil.info("* Verifying item total reflects the quantity amount.");
		
		int quantity = 2;
		
		productDetails.clickViewProduct();
		
		LogUtil.info("Setting quantity to: " + quantity);
		viewProduct.setQuantity(quantity);
		viewProduct.clickAddToCart();
		viewProduct.clickViewCartModalLink();
		
		int expectedTotal = Integer.valueOf(cart.getProductPrice()) * Integer.valueOf(cart.getProductQuantity());
		int actualTotal = Integer.valueOf(cart.getCartTotal());
		LogUtil.info("Expected total should be: " + expectedTotal);
		
		Assert.assertEquals(actualTotal, expectedTotal, "Item total does not reflect expected total: " + expectedTotal);
		LogUtil.info("Item total reflects expected total.");
	}
}
