package pages.cart;

import org.openqa.selenium.By;

import utils.BasePage;

public class Cart {

	
	private By cartInfo = By.id("cart_info");
	private By checkOutBtn = By.xpath("//a[@class='btn btn-default check_out']");
	
	public boolean isCartInfoVisible() {
		return BasePage.isElementVisible(cartInfo);
	}
	
	public boolean isCheckOutBtnVisible() {
		return BasePage.isElementVisible(checkOutBtn);
	}
	
	public void clickCheckOutBtn() {
		BasePage.clickElement(checkOutBtn);
	}
	
}
