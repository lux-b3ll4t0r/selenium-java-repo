package pages.products;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.DriverFactory;
import utils.Webtool;

public class AllProducts{
	
	
	private By allProductsHeader = By.xpath("//h2[contains(text(), 'All Products')]");
	private By allProducts = By.xpath("//div[@class='features_items']//div[@class='col-sm-4']");
	private By featuredItems = By.className("features_items");
	private By productsOverlay = By.xpath("//div[@class='features_items']//div[@class='product-overlay']");
	//private By cartModal = By.className("modal-content");
	//private By productAddedTextField = By.className("modal-title w-100");
	private By continueShoppingBtn = By.xpath("//button[contains(text(), 'Continue Shopping')]");
	private By searchProduct = By.id("search_product");
	private By searchProductBtn = By.id("submit_search");
	private By searchedProductsHeader = By.xpath("//div[@class ='features_items']/h2");
	
	public List<WebElement> getAllProducts(){
		return Webtool.waitForVisibilityOfAllElementsLocatedBy(allProducts);
	}
	
	public List<WebElement> getAllProductsOverlay(){
		return Webtool.waitForVisibilityOfAllElementsLocatedBy(productsOverlay);
	}
	
	public void searchProduct(String product) {
		Webtool.sendKeysTo(searchProduct, product);
		Webtool.clickElement(searchProductBtn);
	}
	
	public String getPrice(int productIndex) {
			
		checkIfIndexTooHigh(productIndex);

		WebElement productPrice = getAllProducts().get(productIndex).findElement(By.xpath("//div[@class='productinfo text-center']/h2"));
		return productPrice.getText();
	}
	
	public String getProductType(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		
		WebElement productType = getAllProducts().get(productIndex).findElement(By.xpath("//div[@class='productinfo text-center']/p"));
		return productType.getText();
			
	}
	
	public String getOverlayPrice(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		moveCursorToProduct(productIndex);
		
		Webtool.waitForVisibilityOfElement(getAllProductsOverlay().get(productIndex));
		
		WebElement overlayPrice = getAllProductsOverlay().get(productIndex).findElement(By.xpath("//div[@class='features_items']//div[@class='product-overlay']//h2"));
		return overlayPrice.getText();
	}
	
	public String getOverlayProductType(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		moveCursorToProduct(productIndex);
		
		Webtool.waitForVisibilityOfElement(getAllProductsOverlay().get(productIndex));
		
		WebElement overlayProductType = getAllProductsOverlay().get(productIndex).findElement(By.xpath("//div[@class='features_items']//div[@class='product-overlay']//p"));
		return overlayProductType.getText();
	}
	
	public void clickOverlayAddToCart(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		moveCursorToProduct(productIndex);
		
		Webtool.waitForVisibilityOfElement(getAllProductsOverlay().get(productIndex));
		
		WebElement overlayAddToCartBtn = getAllProductsOverlay().get(productIndex).findElement(By.xpath("//a[@class='btn btn-default add-to-cart']"));
		overlayAddToCartBtn.click();
	}
	
	public void clickViewProduct(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		
		WebElement viewProductBtn = getAllProducts().get(productIndex).findElement(By.xpath("//div[@class='choose']//a[contains(text(), 'View Product')]"));
		viewProductBtn.click();
	}

	public void clickContinueShopping() {
		
		Webtool.clickElement(continueShoppingBtn);
	}
	public void moveCursorToProduct(int productIndex) {
		Actions actions = new Actions(DriverFactory.getDriver());
		checkIfIndexTooHigh(productIndex);
		actions.moveToElement(getAllProducts().get(productIndex)).perform();
	}
	
	public int checkIfIndexTooHigh(int productIndex) {
		if(productIndex >= getAllProducts().size()) {
			productIndex = getAllProducts().size() - 1;
		}
		
		return productIndex;
	}
	
	public boolean isAllProductsHeaderVisible() {
		return Webtool.isElementVisible(allProductsHeader);
	}
	
	public boolean isProductsListVisible() {
		
		return Webtool.isElementVisible(featuredItems);
	}
	
	public boolean isSearchedProductsHeaderVisible() {
		return Webtool.isElementVisible(searchedProductsHeader);
	}
}
