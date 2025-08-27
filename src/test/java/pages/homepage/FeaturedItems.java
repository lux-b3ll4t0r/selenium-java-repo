package pages.homepage;

import org.openqa.selenium.By;

import utils.BasePage;

public class FeaturedItems {
	
	private By featuredItems = By.className("features_items");
	private By addToCartBtn = By.xpath("//a[@class='btn btn-default add-to-cart']");
	private By continueShoppingBtn = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
	private By popupViewCart = By.xpath("//div[@class='modal-content']//a[@href='/view_cart']");
	
	
	public boolean isFeaturedItemsVisible() {
		return BasePage.isElementVisible(featuredItems);
	}
	
	public void clickAddToCartBtn() {
		BasePage.clickElement(addToCartBtn);
	}
	
	public void clickContinueShoppingBtn() {
		BasePage.clickElement(continueShoppingBtn);
	}
	
	public void clickPopupViewCart() {
		BasePage.clickElement(popupViewCart);
	}
}
