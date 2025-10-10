package ui.pages.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ui.constants.UrlConstants;
import ui.pojos.UIProduct;
import ui.utils.DriverFactory;
import ui.utils.Webtool;

public class ProductDetails {
		
	public static void main(String[] args) {
		
		DriverFactory.setupDriver();
		Webtool.get(UrlConstants.HOMEPAGE);
		
		ProductDetails pd = new ProductDetails();
		
		List<WebElement> list = pd.getAllAddToCartBtns();
		System.out.println(list);
		DriverFactory.quitDriver();
		
	}
	
	private By addToCartBtn = By.xpath("//div[@class='features_items']//div[@class='productinfo text-center']//a[@class='btn btn-default add-to-cart']");
	private By addToCartOverlayBtn = By.xpath("//div[@class='overlay-content']/a[@class= 'btn btn-default add-to-cart']");
	private By allProducts = By.xpath("//div[@class='features_items']//div[@class='col-sm-4']");
	private By continueShoppingBtn = By.xpath("//button[contains(text(), 'Continue Shopping')]");
	private By popupViewCart = By.xpath("//div[@class='modal-content']//a[@href='/view_cart']");
	private By product = By.className("single-products");
	private By productImg = By.xpath("//div[@class='productinfo text-center']//img");
	private By productList = By.xpath("//div[@class='features_items']/div[@class='col-sm-4']");
	private By productName = By.xpath("//div[@class='productinfo text-center']/p");
	private By productOverlayPrice = By.xpath("//div[@class='overlay-content']/h2");
	private By productOverlayName = By.xpath("//div[@class='overlay-content']/p");
	private By productOverlay = By.xpath("//div[@class='features_items']//div[@class='product-overlay']");
	private By productPrice = By.xpath("//div[@class='productinfo text-center']/h2");
	private By viewProduct = By.xpath("//a[contains(text(), 'View Product')]");
	
	public void clickAddToCartBtn() {Webtool.clickElement(addToCartBtn);}
	public void clickContinueShoppingBtn() {Webtool.clickElement(continueShoppingBtn);}
	public void clickPopupViewCart() {Webtool.clickElement(popupViewCart);}
	public void clickViewProduct() {Webtool.clickElement(viewProduct);}
	public String getProductPrice() {return Webtool.getText(productPrice).replaceFirst("Rs.", "").trim();}
	public String getProductName() {return Webtool.getText(productName).trim();}
	public String getProductImgSrc() {return Webtool.getElementAttribute(productImg, "src").trim();}
	public String getProductOverlayPrice() {return Webtool.getText(productOverlayPrice);}
	public String getProductOverlayName() {return Webtool.getText(productOverlayName);}
	public boolean isViewProductLinkVisible() {return Webtool.isElementVisible(viewProduct);}
	public boolean isProductVisible() {return Webtool.isElementVisible(product);}
	public boolean isFeaturedItemsVisible() {return Webtool.isElementVisible(productList);}
	public boolean isProductOverlayPriceVisible() {return Webtool.isElementVisible(productOverlayPrice);}
	public boolean isProductOverlayNameVisible() {return Webtool.isElementVisible(productOverlayName);}
	public boolean isAddToCartOverlayBtnVisible() {return Webtool.isElementVisible(addToCartOverlayBtn);}
	public boolean isProductPriceVisible() {return Webtool.isElementVisible(productPrice);}
	public boolean isProductNameVisible() {return Webtool.isElementVisible(productName);}
	public boolean isProductImgVisible() {return Webtool.isElementVisible(productImg);}
	public boolean isAddToCartBtnVisible() {return Webtool.isElementVisible(addToCartBtn);}
	public List<WebElement> getAllProductsOverlay() {return Webtool.waitForVisibilityOfAllElementsLocatedBy(productOverlay);}
	public List<WebElement> getAllProducts() {return Webtool.waitForVisibilityOfAllElementsLocatedBy(allProducts);}
	public List<WebElement> getAllAddToCartBtns() {return Webtool.waitForVisibilityOfAllElementsLocatedBy(addToCartBtn);}
	
	public void addMultipleItemsToCart(int count) {
		List<WebElement> allBtns = getAllAddToCartBtns();
		
		for(int i = 0; i < count; i++) {
			Webtool.clickElement(allBtns.get(i));
			clickContinueShoppingBtn();
		}
	}
	
	public UIProduct getProductDetails() {
		return new UIProduct.Builder()
		.img(getProductImgSrc())
		.price(getProductPrice())
		.name(getProductName())
		.build();
	}
	
	public List<String> getProductDetailsAsList() {
		return new ArrayList<>(Arrays.asList(getProductImgSrc(), getProductPrice(), getProductName()));
	}
	
	public void moveCursorToProduct() {
		Actions actions = new Actions(DriverFactory.getDriver());
		
		WebElement element = Webtool.getElement(product);
		actions.moveToElement(element).perform();
	}
	
	public boolean isProductInfoVisible() {
		return isProductImgVisible() && isProductPriceVisible()
		&& isProductNameVisible() && isAddToCartBtnVisible() && isViewProductLinkVisible();
	}

	public boolean isProductOverlayInfoVisible() {
		return isProductOverlayNameVisible() && isProductOverlayPriceVisible() && isAddToCartOverlayBtnVisible();
	}
	
	public boolean isProductsListVisible() {
		List<WebElement> list = Webtool.getElementsAsList(productList);
		return !list.isEmpty();
	}
	
	public void clickViewProduct(int productIndex) {
		checkIfIndexTooHigh(productIndex);

		WebElement viewProductBtn = getAllProducts().get(productIndex)
				.findElement(By.xpath("//div[@class='choose']//a[contains(text(), 'View Product')]"));
		viewProductBtn.click();
	}
	
	public void clickOverlayAddToCart(int productIndex) {
		checkIfIndexTooHigh(productIndex);
		moveCursorToProduct(productIndex);

		Webtool.waitForVisibilityOfElement(getAllProductsOverlay().get(productIndex));

		WebElement overlayAddToCartBtn = getAllProductsOverlay().get(productIndex)
				.findElement(By.xpath("//a[@class='btn btn-default add-to-cart']"));
		overlayAddToCartBtn.click();
	}
	
	
	public void moveCursorToProduct(int productIndex) {
		Actions actions = new Actions(DriverFactory.getDriver());
		checkIfIndexTooHigh(productIndex);
		actions.moveToElement(getAllProducts().get(productIndex)).perform();
	}
	
	public int checkIfIndexTooHigh(int productIndex) {
		if (productIndex >= getAllProducts().size()) {
			productIndex = getAllProducts().size() - 1;
		}
		return productIndex;
	}
	
	public String getPrice(int productIndex) {
		checkIfIndexTooHigh(productIndex);

		WebElement productPrice = getAllProducts().get(productIndex)
				.findElement(By.xpath("//div[@class='productinfo text-center']/h2"));
		return productPrice.getText();
	}
	
	public String getProductType(int productIndex) {
		checkIfIndexTooHigh(productIndex);

		WebElement productType = getAllProducts().get(productIndex)
				.findElement(By.xpath("//div[@class='productinfo text-center']/p"));
		return productType.getText();

	}
	
	public String getOverlayPrice(int productIndex) {
		checkIfIndexTooHigh(productIndex);
		moveCursorToProduct(productIndex);

		Webtool.waitForVisibilityOfElement(getAllProductsOverlay().get(productIndex));

		WebElement overlayPrice = getAllProductsOverlay().get(productIndex)
				.findElement(By.xpath("//div[@class='features_items']//div[@class='product-overlay']//h2"));
		return overlayPrice.getText();
	}
	
	public String getOverlayProductType(int productIndex) {
		checkIfIndexTooHigh(productIndex);
		moveCursorToProduct(productIndex);

		Webtool.waitForVisibilityOfElement(getAllProductsOverlay().get(productIndex));

		WebElement overlayProductType = getAllProductsOverlay().get(productIndex)
				.findElement(By.xpath("//div[@class='features_items']//div[@class='product-overlay']//p"));
		return overlayProductType.getText();
	}


}
