package pages.products;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.BasePage;

public class AllProducts extends BasePage{
	
	private Actions actions;
	
	public AllProducts(WebDriver driver) {
		super(driver);
		actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h2[contains(text(), 'All Products')]")
	private WebElement allProductsHeader;
	
	@FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']")
	private List<WebElement> allProducts;
	
	@FindBy(xpath = "//div[@class='features_items']//div[@class='product-overlay']")
	private List<WebElement> productsOverlay;
	
	@FindBy(className = "modal-content")
	private WebElement cartModal;
	
	@FindBy(className = "modal-title w-100")
	private WebElement productAddedTextField;
	
	@FindBy(className = "btn btn-success close-modal btn-block")
	private WebElement continueShoppingBtn;
	
	public String getPrice(int productIndex) {
			
		checkIfIndexTooHigh(productIndex);
		
		WebElement productPrice = allProducts.get(productIndex).findElement(By.xpath("//div[@class='productinfo text-center']/h2"));
		return productPrice.getText();
	}
	
	public String getProductType(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		
		WebElement productType = allProducts.get(productIndex).findElement(By.xpath("//div[@class='productinfo text-center']/p"));
		return productType.getText();
			
	}
	
	public String getOverlayPrice(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		
		moveCursorToProduct(productIndex);
		wait.until(ExpectedConditions.visibilityOf(productsOverlay.get(productIndex)));
		
		WebElement overlayPrice = productsOverlay.get(productIndex).findElement(By.xpath("//div[@class='features_items']//div[@class='product-overlay']//h2"));
		return overlayPrice.getText();
	}
	
	public String getOverlayProductType(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		
		moveCursorToProduct(productIndex);
		wait.until(ExpectedConditions.visibilityOf(productsOverlay.get(productIndex)));
		
		WebElement overlayProductType = productsOverlay.get(productIndex).findElement(By.xpath("//div[@class='features_items']//div[@class='product-overlay']//p"));
		return overlayProductType.getText();
	}
	
	public void clickOverlayAddToCart(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		
		moveCursorToProduct(productIndex);
		wait.until(ExpectedConditions.visibilityOf(productsOverlay.get(productIndex)));
		
		WebElement overlayAddToCartBtn = productsOverlay.get(productIndex).findElement(By.xpath("//a[@class='btn btn-default add-to-cart']"));
		overlayAddToCartBtn.click();
	}
	
	public void clickViewProduct(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		
		WebElement viewProductBtn = allProducts.get(productIndex).findElement(By.xpath("//div[@class='choose']//a[contains(text(), 'View Product')]"));
		viewProductBtn.click();
	}

	public void clickContinueShopping() {
		
		continueShoppingBtn.click();
	}
	public void moveCursorToProduct(int productIndex) {
		
		checkIfIndexTooHigh(productIndex);
		actions.moveToElement(allProducts.get(productIndex)).perform();
	}
	
	public int checkIfIndexTooHigh(int productIndex) {
		if(productIndex >= allProducts.size()) {
			productIndex = allProducts.size() - 1;
		}
		
		return productIndex;
	}
}
