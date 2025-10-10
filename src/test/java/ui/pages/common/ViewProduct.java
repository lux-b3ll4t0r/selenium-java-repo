package ui.pages.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

import ui.pojos.UIProduct;
import ui.utils.DriverFactory;
import ui.utils.Webtool;
	
public class ViewProduct {
	
	public static void main(String[] args) {
		
		DriverFactory.setupDriver();
		WebDriver driver = DriverFactory.getDriver();
		driver.get("https://automationexercise.com/product_details/1");
		
		ViewProduct vp = new ViewProduct();
		WebElement element = driver.findElement(By.id("name"));
		
		vp.clickSubmitReviewBtn();
		String message = (String) ((JavascriptExecutor) DriverFactory.getDriver())
		        .executeScript("return arguments[0].validationMessage;", element);
		
		System.out.println(message);
		DriverFactory.quitDriver();
	}
	
	private By img = By.xpath("//div[@class='view-product']/img");
	private By name = By.xpath("//div[@class='product-information']/h2");
	private By category = By.xpath("//div[@class='product-information']/p[contains(text(), 'Category')]");
	private By rating = By.xpath("//div[@class='product-information']//img[@src='/static/images/product-details/rating.png']");
	private By price = By.xpath("//div[@class='product-information']//span[contains(text(), 'Rs')]"); 
	private By quantityInput = By.id("quantity");
	private By availability = By.xpath("//div[@class='product-information']/p/b[contains(text(), 'Availability')]//..");
	private By condition = By.xpath("//div[@class='product-information']/p/b[contains(text(), 'Condition')]//..");
	private By brand = By.xpath("//div[@class='product-information']/p/b[contains(text(), 'Brand')]//..");
	private By addToCartBtn = By.xpath("//button[@class='btn btn-default cart']");
	private By productDetails = By.className("product-details");
	private By writeReview = By.xpath("//div[@class='category-tab shop-details-tab']");
	private By reviewName = By.id("name");
	private By reviewEmail = By.id("email");
	private By reviewMsg = By.id("review");
	private By reviewSubmitBtn = By.id("button-review");
	private By reviewSuccessMsg = By.xpath("//div[@id='review-section']//div[@class='alert-success alert']/span");
	private By addedToCartModal = By.xpath("//div[@id='cartModal']//div[@class='modal-content']");
	private By continueShoppingBtn = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
	private By viewCartModalLink = By.xpath("//div[@class='modal-body']//a[@href='/view_cart']");
	
	public void setQuantity(int quantity) {Webtool.sendKeysTo(quantityInput, String.valueOf(quantity));}
	public void setQuantity(Object quantity) {Webtool.sendKeysTo(quantityInput, String.valueOf(quantity));}
	public void setReviewName(String name) {Webtool.sendKeysTo(reviewName, name);}
	public void setReviewEmail(String email) {Webtool.sendKeysTo(reviewEmail, email);}
	public void setReviewMsg(String msg) {Webtool.sendKeysTo(reviewMsg, msg);}
	public void clickAddToCart() {Webtool.clickElement(addToCartBtn);}
	public void clickViewContinueShoppingBtn() {Webtool.clickElement(continueShoppingBtn);}
	public void clickSubmitReviewBtn() {Webtool.clickElement(reviewSubmitBtn);}
	public void clickViewCartModalLink() {Webtool.clickElement(viewCartModalLink);}
	public String getQuantity() {return Webtool.getInputText(quantityInput).trim();}
	public String getImg() {return Webtool.getElementAttribute(img, "src");}
	public String getName() {return Webtool.getText(name).trim();}
	public String getCategory() {return Webtool.getText(category).replaceFirst("Category:", "").trim();}
	public String getRatingImg() {return Webtool.getElementAttribute(rating, "src");}
	public String getPrice() {return Webtool.getText(price).replaceFirst("Rs.", "").trim();}
	public String getAvailability() {return Webtool.getText(availability).replaceAll("Availability:", "").trim();}
	public String getCondition() {return Webtool.getText(condition).replaceAll("Condition:", "").trim();}
	public String getBrand() {return Webtool.getText(brand).replaceAll("Brand:", "").trim();}
	public boolean isImgVisible() {return Webtool.isElementVisible(img);}
	public boolean isNameVisible() {return Webtool.isElementVisible(name);}
	public boolean isCategoryVisible() {return Webtool.isElementVisible(category);}
	public boolean isRatingVisible() {return Webtool.isElementVisible(rating);}
	public boolean isAddToCartVisible() {return Webtool.isElementVisible(addToCartBtn);}
	public boolean isAvailabilityVisible() {return Webtool.isElementVisible(availability);}
	public boolean isConditionVisible() {return Webtool.isElementVisible(condition);}
	public boolean isBrandVisible() {return Webtool.isElementVisible(brand);}
	public boolean isQuantityVisible() {return Webtool.isElementVisible(quantityInput);}
	public boolean isPriceVisible() {return Webtool.isElementVisible(price);}
	public boolean isProductDetailsContainerVisible() {return Webtool.isElementVisible(productDetails);}
	public boolean isAddedToCartModalNotVisible() {return Webtool.isElementNotVisible(addedToCartModal);}
	public boolean isReviewVisible() {return Webtool.isElementVisible(writeReview);}
	public boolean isReviewSuccessMsgVisible() {return Webtool.isElementVisible(reviewSuccessMsg);}
	public boolean isReviewSubmitBtnClickable() {return Webtool.isElementClickable(reviewSubmitBtn);}
	public boolean isReviewNameErrorVisible() {return !getReviewNameErrorMessage().isEmpty();}
	public boolean isReviewEmailErrorVisible() {return !getReviewEmailErrorMessage().isEmpty();}
	public boolean isReviewMsgErrorVisible() {return !getReviewMsgErrorMessage().isEmpty();}
	
	public UIProduct getProduct() {
		return new UIProduct.Builder()
								.img(getImg())
								.name(getName())
								.category(getCategory())
								.price(getPrice())
								.quantity(getQuantity())
								.availability(getAvailability())
								.condition(getCondition())
								.brand(getBrand())
								.build();
	}
	
	public List<String> getProductDetailsAsList(){
		List<String> details = new ArrayList<>(Arrays.asList(getImg(), getName(), getCategory(), getPrice(),
				String.valueOf(getQuantity()), getAvailability(), getCondition(), getBrand()));
		Collections.sort(details);
		return details;
	}
	
	public List<String> getProductDetailsAsListForCart(){
		List<String> details = new ArrayList<>(Arrays.asList(getImg(), getName(), getCategory(), getPrice(),
				String.valueOf(getQuantity())));
		Collections.sort(details);
		return details;
	}
	
	public void writeRandomReview() {
		Webtool.sendKeysTo(reviewName, new Faker().name().fullName());
		Webtool.sendKeysTo(reviewEmail, new Faker().internet().emailAddress());
		Webtool.sendKeysTo(reviewMsg, new Faker().lorem().paragraph());
		clickSubmitReviewBtn();
	}
	
	public void writeRandomReviewNoSubmit() {
		Webtool.sendKeysTo(reviewName, new Faker().name().fullName());
		Webtool.sendKeysTo(reviewEmail, new Faker().internet().emailAddress());
		Webtool.sendKeysTo(reviewMsg, new Faker().lorem().paragraph());
	}
	
	public String getReviewNameErrorMessage() {
		WebElement element = Webtool.waitForVisibitlityOfElementLocated(reviewName);
		
		String message = (String) ((JavascriptExecutor) DriverFactory.getDriver())
		        .executeScript("return arguments[0].validationMessage;", element);
		
		return message;
	}
	
	public String getReviewEmailErrorMessage() {
		WebElement element = Webtool.waitForVisibitlityOfElementLocated(reviewEmail);
		
		String message = (String) ((JavascriptExecutor) DriverFactory.getDriver())
		        .executeScript("return arguments[0].validationMessage;", element);
		
		return message;
	}
	
	public String getReviewMsgErrorMessage() {
		WebElement element = Webtool.waitForVisibitlityOfElementLocated(reviewMsg);
		
		String message = (String) ((JavascriptExecutor) DriverFactory.getDriver())
		        .executeScript("return arguments[0].validationMessage;", element);
		
		return message;
	}
	
	public boolean isProductDetailsVisible() {return isImgVisible() && isNameVisible() && isCategoryVisible() 
			&& isRatingVisible() && isPriceVisible() && isQuantityVisible() && isAddToCartVisible() 
			&& isAvailabilityVisible() &&isConditionVisible() && isBrandVisible();
	}
	
}
