package pages.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BasePage;

public class NavBar extends BasePage{
	
	
	public NavBar(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(partialLinkText = "Home")
	private WebElement homeNav; // having private webelements hides the internal page structure, only exposing well defined actions
								  // "I want to log in" not "Give me the password field so I can type into it"
	@FindBy(partialLinkText = "Products")
	private WebElement productsNav;
	
	@FindBy(partialLinkText = "Cart")
	private WebElement cartNav;
	
	@FindBy(partialLinkText = "Signup / Login")
	private WebElement signUpLoginNav;
	
	@FindBy(partialLinkText = "Test Cases")
	private WebElement testCasesNav;
	
	@FindBy(partialLinkText = "API Testing")
	private WebElement apiTestingNav;
	
	@FindBy(partialLinkText = "Video Tutorials")
	private WebElement videoTutorialsNav;
	
	@FindBy(partialLinkText = "Contact us")
	private WebElement contactUsNav;
	
	@FindBy(xpath = "//img[@alt='Website for automation practice']")
	private WebElement homePageLogo;
	
	@FindBy(xpath = "//a[contains(text(), 'Logged in as')]/b")
	private WebElement loggedInAs;
	
	@FindBy(xpath = "//a[@href='/logout']")
	private WebElement logoutButton;
	
	public WebElement getHomePageLogo() {
		return homePageLogo;
	}
	
	public WebElement getHomeNav() {
		return homeNav;
	}
	
	public WebElement getProductsNav() {
		return productsNav;
	}
	
	public WebElement getCartNav() {
		return cartNav;
	}
	
	public WebElement getSignUpLoginNav() {
		return signUpLoginNav;
	}
	
	public WebElement getTestCasesNav() {
		return testCasesNav;
	}
	
	public WebElement getApiTestingNav() {
		return apiTestingNav;
	}
	
	public WebElement getVideoTutorialsNav() {
		return videoTutorialsNav;
	}
	
	public WebElement getContactUsNav() {
		return contactUsNav;
	}
	
	public WebElement getLoggedInAs() {
		return loggedInAs;
	}
	
	public WebElement getLogoutButton() {
		return logoutButton;
	}
	
	public void clickLogoutButton() {
		logoutButton.click();
	}
	
	public void clickHomeNav() {
		homeNav.click();
	}
	
	public void clickProductsNav() {
		productsNav.click();
	}
	
	public void clickCartNav() {
		cartNav.click();
	}
	
	public void clickSignUpLoginNav() {
		signUpLoginNav.click();
	}
	
	public void clickTestCasesNav() {
		testCasesNav.click();
	}
	
	public void clickApiTestingNav() {
		apiTestingNav.click();
	}
	
	public void clickVideoTutorialsNav() {
		videoTutorialsNav.click();
	}
	
	public void clickContactUsNav() {
		contactUsNav.click();
	}
	
	
	

}
