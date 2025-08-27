package pages.checkout;

import org.openqa.selenium.By;

import utils.Webtool;

public class Checkout {

	
	private By deliveryAddress = By.id("address_delivery");
	private By billingAddress = By.id("address_invoice");
	private By cartInfo = By.id("cart_info");
	private By placeOrderBtn = By.xpath("//a[contains(text(), 'Place Order')]");
	private By orderMsg = By.id("ordermsg");
	
	public boolean isDeliveryAddressVisible() {
		return Webtool.isElementVisible(deliveryAddress);
	}
	
	public boolean isBillingAddressVisible() {
		return Webtool.isElementVisible(billingAddress);
	}
	
	public boolean isCartInfoVisible() {
		return Webtool.isElementVisible(cartInfo);
	}
	
	public boolean isPlaceOrderBtnVisible() {
		return Webtool.isElementVisible(placeOrderBtn);
	}
	
	public boolean isOrderMsgVisible() {
		return Webtool.isElementVisible(orderMsg);
	}
}
