package pages.homepage;

import org.openqa.selenium.By;

import utils.Webtool;

public class FeaturedItems {
	
	private By featuredItems = By.className("features_items");
	private By addToCartBtn = By.xpath("//a[@class='btn btn-default add-to-cart']");
	private By continueShoppingBtn = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
	private By popupViewCart = By.xpath("//div[@class='modal-content']//a[@href='/view_cart']");
	
	
	public boolean isFeaturedItemsVisible() {
		return Webtool.isElementVisible(featuredItems);
	}
	
	public void clickAddToCartBtn() {
		Webtool.clickElement(addToCartBtn);
	}
	
	public void clickContinueShoppingBtn() {
		Webtool.clickElement(continueShoppingBtn);
	}
	
	public void clickPopupViewCart() {
		Webtool.clickElement(popupViewCart);
	}
}
