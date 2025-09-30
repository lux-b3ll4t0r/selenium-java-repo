package ui.pages.cart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.pojos.Product;
import ui.utils.Webtool;

public class Cart {

	private By cartInfo = By.id("cart_info");
	private By checkOutBtn = By.xpath("//a[@class='btn btn-default check_out']");
	private By emptyCart = By.id("empty_cart");
	private By productImg = By.xpath("//img[@class='product_image']");
	private By productName = By.xpath("//td[@class='cart_description']//a");
	private By productCategory = By.xpath("//td[@class='cart_description']/p");
	private By productPrice = By.xpath("//td[@class='cart_price']/p");
	private By productQuantity = By.xpath("//td[@class='cart_quantity']/button");
	private By cartTotal = By.xpath("//td[@class='cart_total']/p[@class='cart_total_price']");
	private By cartItemDeleteBtn = By.xpath("//table[@id='cart_info_table']/tbody//a[@class='cart_quantity_delete']");
	
	public void clickCheckOutBtn() {Webtool.clickElement(checkOutBtn);}
	public void removeCartItem() {Webtool.clickElement(cartItemDeleteBtn);}
	public String getCartTotal() {return Webtool.getText(cartTotal).replaceFirst("Rs.", "").trim();}
	public String getProductCategory() {return Webtool.getText(productCategory).trim();}
	public String getProductImg() {return Webtool.getElementAttribute(productImg, "src").trim();}
	public String getProductName() {return Webtool.getText(productName).trim();}
	public String getProductPrice() {return Webtool.getText(productPrice).replaceFirst("Rs.", "").trim();}
	public String getProductQuantity() {return Webtool.getText(productQuantity).trim();}
	public boolean isCartInfoVisible() {return Webtool.isElementVisible(cartInfo);}
	public boolean isCheckOutBtnVisible() {return Webtool.isElementVisible(checkOutBtn);}
	
	public boolean isCartEmpty() {
		try {
			Webtool.waitForVisibitlityOfElementLocated(emptyCart, 1);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public Product getProductDetails() {
		return new Product.Builder()
				.img(getProductImg())
				.name(getProductName())
				.category(getProductCategory())
				.price(getProductPrice())
				.quantity(getProductQuantity())
				.build();
	}
	
	public List<String> getProductDetailsAsList(){
		List<String> details = new ArrayList<>(Arrays.asList(getProductImg(), getProductName(), getProductCategory(), getProductPrice(),
				String.valueOf(getProductQuantity())));
		Collections.sort(details);
		return details;
	}
	
	public void clearCartIfNotEmpty() {
		if(!isCartEmpty()) {
			removeAllCartItems();
		}
	}
	
	public void removeAllCartItems() {
		List<WebElement> deleteBtns = Webtool.getElementsAsList(cartItemDeleteBtn);
		
		for(WebElement each : deleteBtns) {
			Webtool.waitForElementToBeClickable(each);
			Webtool.clickElement(each);
		}
	}
	
	
}

