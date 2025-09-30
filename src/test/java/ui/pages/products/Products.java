package ui.pages.products;

import org.openqa.selenium.By;
import ui.utils.Webtool;

public class Products {


	private By allProductsHeader = By.xpath("//h2[contains(text(), 'All Products')]");
	private By searchProduct = By.id("search_product");
	private By searchProductBtn = By.id("submit_search");
	private By searchedProductsHeader = By.xpath("//div[@class ='features_items']/h2");

	public boolean isAllProductsHeaderVisible() {return Webtool.isElementVisible(allProductsHeader);}
	public boolean isSearchBarVisible() {return Webtool.isElementVisible(searchProduct);}
	public boolean isSearchedProductsHeaderVisible() {return Webtool.isElementVisible(searchedProductsHeader);}

	public void searchProduct(String product) {
		Webtool.waitForVisibitlityOfElementLocated(searchProduct);
		Webtool.sendKeysTo(searchProduct, product);
		Webtool.clickElement(searchProductBtn);
	}


}
