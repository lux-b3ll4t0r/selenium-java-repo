package ui.tests.homepage;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import common.constants.NegativeValues;
import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.common.ProductDetails;
import ui.pages.common.ViewProduct;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

@Listeners(common.listeners.TestListener.class)
public class ViewProductTest extends UIBaseTest{

	private ViewProduct viewProduct;
	private ProductDetails productDetails;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up class resources.");
		viewProduct = new ViewProduct();
		productDetails = new ProductDetails();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void view_product_smoke_test() {
		LogUtil.info("* Verifying view products link directs and displays the product is visible.");
	
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		Assert.assertTrue(Webtool.getCurrentUrl().contains(UrlConstants.PRODUCT_DETAILS), "Incorrect URL.");
		
		Assert.assertTrue(viewProduct.isProductDetailsContainerVisible(), "Product details are not visible.");
		LogUtil.info("Product details are visible.");	
	}
	
	@Test(groups = {"functional"}, priority = 1)
	public void view_product_functional_test() {
		LogUtil.info("* Verifying correct product information is displayed when viewing product details.");
		
		String img = productDetails.getProductImgSrc();
		String price = productDetails.getProductPrice();
		String name = productDetails.getProductName();
			
		LogUtil.info("(Product: Image: " + img + " Price: " + price + " Name: " + name + ")");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		Assert.assertEquals(img, viewProduct.getImg(), "Image does not match view product page image.");
		Assert.assertEquals(price, viewProduct.getPrice(), "Price does not match view product page price.");
		Assert.assertEquals(name, viewProduct.getName(), "Name does not match view product page name.");

		Assert.assertTrue(viewProduct.isProductDetailsVisible(), "Product details are not visible.");
		LogUtil.info("Product details match and all other details are visible.");	
	}
	
	@Test(groups = {"smoke"}, priority = 2)
	public void view_product_quantity_smoke_test() {
		LogUtil.info("* Verifying quantity is visible in the view product page.");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		Assert.assertTrue(viewProduct.isQuantityVisible());
		LogUtil.info("Quantity is visible.");
	}
	
	@Test(groups = {"functional"}, priority = 3, dataProvider = "validQuantities")
	public void view_product_quantity_functional_test(int quantity) {
		LogUtil.info("* Verifying quantity can be populated with valid inputs.");		
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		viewProduct.setQuantity(quantity);
		
		Assert.assertEquals(Integer.valueOf(viewProduct.getQuantity()), quantity, "Quantity does not match expected.");
		LogUtil.info("Quantity was updated successfully.");
	}
	
	@DataProvider(name = "validQuantities")
	public Object[][] validQuantities(){
		return new Object[][] {
			{2},
			{10},
			{100},
			{1000}
		};
	}
	
	@Test(groups = {"negative"}, priority = 4)
	public void view_product_quantity_negative_test() {
		LogUtil.info("* Verifying product quantity negative values.");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		for(Object each : NegativeValues.NUMBER_INPUT) {
			viewProduct.setQuantity(each);
			Assert.assertTrue(viewProduct.isAddedToCartModalNotVisible());
		}
		LogUtil.info("Negative values for Quantity rejected successfully.");
	}
	
	@Test(groups = {"smoke"}, priority = 5)
	public void view_product_write_review_smoke_test() {
		LogUtil.info("* Verifying write review section can be submitted successfully.");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
	
		viewProduct.writeRandomReview();
		Assert.assertTrue(viewProduct.isReviewSuccessMsgVisible(), "Review success message not visible.");
		LogUtil.info("Review submitted successfully.");
	}
	
	@Test(groups = {"smoke"}, priority = 6)
	public void view_product_write_review_negative_smoke_test() {
		LogUtil.info("* Verifying negative inputs.");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		LogUtil.info("Clicking submit without populating any fields.");
		viewProduct.clickSubmitReviewBtn();
		
		Assert.assertTrue(viewProduct.isReviewNameErrorVisible(), "Review name error not visible.");
		LogUtil.info("Form successfully rejected review. -> Message: " + viewProduct.getReviewNameErrorMessage());
	}
	
	@Test(groups = {"functional"}, priority = 8)
	public void view_product_write_review_name_negative_test() {
		LogUtil.info("* Verifying an error message is displayed when submitting with no name.");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		LogUtil.info("Submitting review without entering name.");
		viewProduct.setReviewEmail(new Faker().internet().emailAddress());
		viewProduct.setReviewMsg(new Faker().lorem().paragraph());
		viewProduct.clickSubmitReviewBtn();

		Assert.assertTrue(viewProduct.isReviewNameErrorVisible(), "Review name error not visible.");
		LogUtil.info("Form successfully rejected review. -> Message: " + viewProduct.getReviewNameErrorMessage());
	}	
	
	@Test(groups = {"functional"}, priority = 9)
	public void view_product_write_review_email_negative_test() {
		LogUtil.info("* Verifying an error message is displayed when submitting with no email.");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		LogUtil.info("Submitting review without entering email.");
		viewProduct.setReviewName(new Faker().name().fullName());
		viewProduct.setReviewMsg(new Faker().lorem().paragraph());
		viewProduct.clickSubmitReviewBtn();

		Assert.assertTrue(viewProduct.isReviewEmailErrorVisible(), "Review email error not visible.");
		LogUtil.info("Form successfully rejected review. -> Message: " + viewProduct.getReviewEmailErrorMessage());
	}
	
	@Test(groups = {"functional"}, priority = 10)
	public void view_product_write_review_msg_negative_test() {
		LogUtil.info("* Verifying an error message is displayed when submitting with no message.");
		
		LogUtil.info("Clicking View Product.");
		productDetails.clickViewProduct();
		
		LogUtil.info("Submitting review without entering message.");
		viewProduct.setReviewName(new Faker().name().fullName());
		viewProduct.setReviewEmail(new Faker().internet().emailAddress());
		viewProduct.clickSubmitReviewBtn();

		Assert.assertTrue(viewProduct.isReviewMsgErrorVisible(), "Review message error not visible.");
		LogUtil.info("Form successfully rejected review. -> Message: " + viewProduct.getReviewMsgErrorMessage());
	}
	
}
