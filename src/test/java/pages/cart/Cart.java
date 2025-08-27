package pages.cart;

import org.openqa.selenium.By;

import utils.Webtool;

public class Cart {

	
	private By cartInfo = By.id("cart_info");
	private By checkOutBtn = By.xpath("//a[@class='btn btn-default check_out']");
	
	public boolean isCartInfoVisible() {
		return Webtool.isElementVisible(cartInfo);
	}
	
	public boolean isCheckOutBtnVisible() {
		return Webtool.isElementVisible(checkOutBtn);
	}
	
	public void clickCheckOutBtn() {
		Webtool.clickElement(checkOutBtn);
	}
	
}
