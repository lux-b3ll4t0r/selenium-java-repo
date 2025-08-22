package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.BasePage;

public class Cart extends BasePage{

	public Cart(WebDriver driver) {
		super(driver);
	}
	
	private By cartInfo = By.id("cart_info");
	private By checkOutBtn = By.xpath("//a[@class='btn btn-default check_out']");
	
	public boolean isCartInfoVisible() {
		return isElementVisible(cartInfo);
	}
	
	public boolean isCheckOutBtnVisible() {
		return isElementVisible(checkOutBtn);
	}
	
	public void clickCheckOutBtn() {
		clickElement(checkOutBtn);
	}
	
}
