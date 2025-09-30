package ui.pages.checkout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import common.pojos.Product;
import ui.utils.Webtool;

public class Checkout {
		
	private By allOrderItems = By.xpath("//tr[contains(@id, 'product')]");
	private By deliveryAddress = By.id("address_delivery");
	private By deliveryName = By.xpath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']");
	private By deliveryCompany = By.xpath("//ul[@id='address_delivery']//li[3]");
	private By deliveryAddress1 = By.xpath("//ul[@id='address_delivery']//li[4]");
	private By deliveryAddress2 = By.xpath("//ul[@id='address_delivery']//li[5]");
	private By deliveryCityStateZip = By.xpath("//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']");
	private By deliveryCountry = By.xpath("//ul[@id='address_delivery']//li[@class='address_country_name']");
	private By deliveryPhoneNum = By.xpath("//ul[@id='address_delivery']//li[@class='address_phone']");
	private By billingAddress = By.id("address_invoice");
	private By billingName = By.xpath("//ul[@id='address_invoice']//li[@class='address_firstname address_lastname']");
	private By billingCompany = By.xpath("//ul[@id='address_invoice']//li[3]");
	private By billingAddress1 = By.xpath("//ul[@id='address_invoice']//li[4]");
	private By billingAddress2 = By.xpath("//ul[@id='address_invoice']//li[5]");
	private By billingCityStateZip = By.xpath("//ul[@id='address_invoice']//li[@class='address_city address_state_name address_postcode']");
	private By billingCountry = By.xpath("//ul[@id='address_invoice']//li[@class='address_country_name']");
	private By billingPhoneNum = By.xpath("//ul[@id='address_invoice']//li[@class='address_phone']");
	private By cartInfo = By.id("cart_info");
	private By placeOrderBtn = By.xpath("//a[contains(text(), 'Place Order')]");
	private By productImg = By.xpath("//img[@alt='Product Image']");
	private By productName = By.xpath("//td[@class='cart_description']//a");
	private By productCategory = By.xpath("//td[@class='cart_description']/p");
	private By productPrice = By.xpath("//td[@class='cart_price']/p");
	private By productQuantity = By.xpath("//td[@class='cart_quantity']/button");
	private By productTotal = By.xpath("//td[@class='cart_product']//following-sibling::td[@class='cart_total']/p");
	private By orderTotal = By.xpath("//tr//h4//b[contains(text(),'Total Amount')]/../../following-sibling::td/p");
	private By orderMsg = By.id("ordermsg");
	
	public void placeOrder() {Webtool.clickElement(placeOrderBtn);}
	public String getDeliveryName () {return Webtool.getText(deliveryName);}
	public String getDeliveryCompany () {return Webtool.getText(deliveryCompany);}
	public String getDeliveryAddress1 () {return Webtool.getText(deliveryAddress1);}
	public String getDeliveryAddress2 () {return Webtool.getText(deliveryAddress2);}
	public String getDeliveryCityStateZip () {return Webtool.getText(deliveryCityStateZip);}
	public String getDeliveryCountry () {return Webtool.getText(deliveryCountry);}
	public String getDeliveryPhoneNum () {return Webtool.getText(deliveryPhoneNum);}
	public String getBillingName () {return Webtool.getText(billingName);}
	public String getBillingCompany () {return Webtool.getText(billingCompany);}
	public String getBillingAddress1 () {return Webtool.getText(billingAddress1);}
	public String getBillingAddress2 () {return Webtool.getText(billingAddress2);}
	public String getBillingCityStateZip () {return Webtool.getText(billingCityStateZip);}
	public String getBillingCountry () {return Webtool.getText(billingCountry);}
	public String getBillingPhoneNum () {return Webtool.getText(billingPhoneNum);}
	public String getOrderTotal() {return Webtool.getText(orderTotal).replaceFirst("Rs.", "").trim();}
	public boolean isDeliveryNameVisible() {return Webtool.isElementVisible(deliveryName);};
	public boolean isDeliveryCompanyVisible() {return Webtool.isElementVisible(deliveryCompany);};
	public boolean isDeliveryAddress1Visible() {return Webtool.isElementVisible(deliveryAddress1);};
	public boolean isDeliveryAddress2Visible() {return Webtool.isElementVisible(deliveryAddress2);};
	public boolean isDeliveryCityStateZipVisible() {return Webtool.isElementVisible(deliveryCityStateZip);};
	public boolean isDeliveryCountryVisible() {return Webtool.isElementVisible(deliveryCountry);};
	public boolean isDeliveryPhoneNumVisible() {return Webtool.isElementVisible(deliveryPhoneNum);};
	public boolean isBillingNameVisible() {return Webtool.isElementVisible(billingName);};
	public boolean isBillingCompanyVisible() {return Webtool.isElementVisible(billingCompany);};
	public boolean isBillingAddress1Visible() {return Webtool.isElementVisible(billingAddress1);};
	public boolean isBillingAddress2Visible() {return Webtool.isElementVisible(billingAddress2);};
	public boolean isBillingCityStateZipVisible() {return Webtool.isElementVisible(billingCityStateZip);};
	public boolean isBillingCountryVisible() {return Webtool.isElementVisible(billingCountry);};
	public boolean isBillingPhoneNumVisible() {return Webtool.isElementVisible(billingPhoneNum);};
	public boolean isDeliveryAddressVisible() {return Webtool.isElementVisible(deliveryAddress);}
	public boolean isBillingAddressVisible() {return Webtool.isElementVisible(billingAddress);}
	public boolean isCartInfoVisible() {return Webtool.isElementVisible(cartInfo);}
	public boolean isPlaceOrderBtnVisible() {return Webtool.isElementVisible(placeOrderBtn);}
	public boolean isOrderMsgVisible() {return Webtool.isElementVisible(orderMsg);}
	public boolean isProductImgVisible() {return Webtool.isElementVisible(productImg);}
	public boolean isProductNameVisible() {return Webtool.isElementVisible(productName);}
	public boolean isProductCategoryVisible() {return Webtool.isElementVisible(productCategory);}
	public boolean isProductPriceVisible() {return Webtool.isElementVisible(productPrice);}
	public boolean isProductQuantityVisible() {return Webtool.isElementVisible(productQuantity);}
	public boolean isProductTotalVisible() {return Webtool.isElementVisible(productTotal);}
	public boolean isTotalAmountVisible() {return Webtool.isElementVisible(orderTotal);}
	
	public void validateCheckoutLoads(SoftAssert softAssert) {
		softAssert.assertTrue(isDeliveryAddressVisible(), "Delivery address not visible.");
		softAssert.assertTrue(isBillingAddressVisible(), "Billing address not visible.");
		softAssert.assertTrue(isCartInfoVisible(), "Item info not visible.");
		softAssert.assertTrue(isOrderMsgVisible(), "Order message not visible.");
		softAssert.assertTrue(isPlaceOrderBtnVisible(), "Place order button not visible.");
	}
	
	public void validateDeliveryDetails(SoftAssert softAssert) {
		softAssert.assertTrue(isDeliveryNameVisible(), "Delivery name not visible.");
		softAssert.assertTrue(isDeliveryCompanyVisible(), "Delivery company not visible.");
		softAssert.assertTrue(isDeliveryAddress1Visible(), "Delivery address1 not visible.");
		softAssert.assertTrue(isDeliveryAddress2Visible(), "Delivery address2 not visible.");
		softAssert.assertTrue(isDeliveryCityStateZipVisible(), "Delivery city/state/zip not visible.");
		softAssert.assertTrue(isDeliveryCountryVisible(), "Delivery country not visible.");
		softAssert.assertTrue(isDeliveryPhoneNumVisible(), "Delivery phone number not visible.");
	
	}
	
	public void validateBillingDetails(SoftAssert softAssert) {
		softAssert.assertTrue(isBillingNameVisible(), "Billing name not visible.");
		softAssert.assertTrue(isBillingCompanyVisible(), "Billing company not visible.");
		softAssert.assertTrue(isBillingAddress1Visible(), "Billing address1 not visible.");
		softAssert.assertTrue(isBillingAddress2Visible(), "Billing address2 not visible.");
		softAssert.assertTrue(isBillingCityStateZipVisible(), "Billing city/state/zip not visible.");
		softAssert.assertTrue(isBillingCountryVisible(), "Billing country not visible.");
		softAssert.assertTrue(isBillingPhoneNumVisible(), "Billing phone number not visible.");
	}
	
	public void validateCartItemDetails(SoftAssert softAssert) {
		softAssert.assertTrue(isProductImgVisible());
		softAssert.assertTrue(isProductNameVisible());
		softAssert.assertTrue(isProductCategoryVisible());
		softAssert.assertTrue(isProductPriceVisible());
		softAssert.assertTrue(isProductQuantityVisible());
		softAssert.assertTrue(isProductTotalVisible());
		softAssert.assertTrue(isTotalAmountVisible());
	}
	
	public void validateProductTotal(SoftAssert softAssert) {
		List<Product> orderItems = getAllOrderItemsData();
		int expectedTotal = 0;
		
		for(Product each : orderItems) {
			expectedTotal = Integer.valueOf(each.getPrice()) * Integer.valueOf(each.getQuantity());
			softAssert.assertEquals((int) expectedTotal, (int) Integer.valueOf(each.getTotal()), each.getName() + " total does not reflected expected price and quantity total.");
		}
	}
	
	public void validateOrderTotal(SoftAssert softAssert) {
		List<Product> orderItems = getAllOrderItemsData();
		int expectedTotal = 0;
		
		for(Product each : orderItems) {
			expectedTotal = expectedTotal + Integer.valueOf(each.getTotal());
		}
		softAssert.assertEquals((int) Integer.valueOf(getOrderTotal()), (int) expectedTotal, "Order total does not reflect expected total: " + expectedTotal);
	}
	
	public List<WebElement> getAllOrderItems(){
		return Webtool.waitForVisibilityOfAllElementsLocatedBy(allOrderItems);
	}
	
	public List<Product> getAllOrderItemsData(){
		List<WebElement> list = getAllOrderItems();
		List<Product> productList = new ArrayList<>();
	
		for(WebElement each : list) {
			String img = each.findElement(By.xpath(".//img[@alt='Product Image']")).getAttribute("src");
			String name = each.findElement(By.xpath(".//td[@class='cart_description']//a")).getText().trim();
			String category = each.findElement(By.xpath(".//td[@class='cart_description']/p")).getText().trim();
			String price = each.findElement(By.xpath(".//td[@class='cart_price']/p")).getText().replaceFirst("Rs.", "").trim();
			String quantity = each.findElement(By.xpath(".//td[@class='cart_quantity']/button")).getText().trim();
			String total = each.findElement(By.xpath(".//td[@class='cart_product']//following-sibling::td[@class='cart_total']/p")).getText().replaceFirst("Rs.", "").trim();

			Product product = new Product.Builder().img(img).name(name).category(category)
					.price(price).quantity(quantity).total(total).build();
			productList.add(product);
		}
		
		return productList;
	}
	
	public List<String> getDeliveryAddressAsList(){
		List<String> list = Arrays.asList(getDeliveryName(), getDeliveryCompany(), getDeliveryAddress1(), 
				getDeliveryAddress2(), getDeliveryCityStateZip(), getDeliveryCountry(), getDeliveryPhoneNum());
		Collections.sort(list);
		return list;
	}

	public List<String> getBillingAddressAsList(){
		List<String> list = Arrays.asList(getDeliveryName(), getDeliveryCompany(), getDeliveryAddress1(), 
				getDeliveryAddress2(), getDeliveryCityStateZip(), getDeliveryCountry(), getDeliveryPhoneNum());
		Collections.sort(list);
		return list;
	}
}
