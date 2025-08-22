package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.BasePage;

public class Checkout extends BasePage{
	
	public Checkout(WebDriver driver) {
		super(driver);
	}
	
	private By deliveryAddress = By.id("address_delivery");
	private By billingAddress = By.id("address_invoice");
	private By cartInfo = By.id("cart_info");
	private By placeOrderBtn = By.xpath("//a[contains(text(), 'Place Order')]");
	private By orderMsg = By.id("ordermsg");
	
	public boolean isDeliveryAddressVisible() {
		return isElementVisible(deliveryAddress);
	}
	
	public boolean isBillingAddressVisible() {
		return isElementVisible(billingAddress);
	}
	
	public boolean isCartInfoVisible() {
		return isElementVisible(cartInfo);
	}
	
	public boolean isPlaceOrderBtnVisible() {
		return isElementVisible(placeOrderBtn);
	}
	
	public boolean isOrderMsgVisible() {
		return isElementVisible(orderMsg);
	}
}
