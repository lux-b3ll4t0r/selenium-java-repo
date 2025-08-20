package pages.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.BasePage;

public class NavBar extends BasePage{
	
	
	public NavBar(WebDriver driver) {
		super(driver);
	}
	
	private By homeNav = By.partialLinkText("Home");
	private By productsNav = By.partialLinkText("Products");	
	private By cartNav = By.partialLinkText("Cart");	
	private By signUpLoginNav = By.partialLinkText("Signup / Login");
	private By testCasesNav = By.partialLinkText("Test Cases");
	private By apiTestingNav = By.partialLinkText("API Testing");
	private By videoTutorialsNav = By.partialLinkText("Video Tutorials");
	private By contactUsNav = By.partialLinkText("Contact us");
	private By homePageLogo = By.xpath("//img[@alt='Website for automation practice']");
	private By loggedInAs = By.xpath("//a[contains(text(), 'Logged in as')]/b");
	private By logoutButton = By.xpath("//a[@href='/logout']");
	
	
	
	public void clickLogoutButton() {
		clickElement(logoutButton);
	}
	
	public void clickHomeNav() {
		clickElement(homeNav);
	}
	
	public void clickProductsNav() {
		clickElement(productsNav);
	}
	
	public void clickCartNav() {
		clickElement(cartNav);
	}
	
	public void clickSignUpLoginNav() {
		clickElement(signUpLoginNav);
	}
	
	public void clickTestCasesNav() {
		clickElement(testCasesNav);
	}
	
	public void clickApiTestingNav() {
		clickElement(apiTestingNav);
	}
	
	public void clickVideoTutorialsNav() {
		clickElement(videoTutorialsNav);
	}
	
	public void clickContactUsNav() {
		clickElement(contactUsNav);
	}
	
	public boolean isHomeNavVisible() {
		return isElementVisible(homeNav);
	}
	
	public boolean isProductsNavVisible() {
		return isElementVisible(productsNav);
	}
	
	public boolean isCartNavVisible() {
		return isElementVisible(cartNav);
	}
	
	public boolean isSignUpLoginNavVisible() {
		return isElementVisible(signUpLoginNav);
	}
	
	public boolean isTestCasesNavVisible() {
		return isElementVisible(testCasesNav);
	}
	
	public boolean isApiTestingNavVisible() {
		return isElementVisible(apiTestingNav);
	}
	
	public boolean isVideoTutorialsNavVisible() {
		return isElementVisible(videoTutorialsNav);
	}
	
	public boolean isContactUsNavVisible() {
		return isElementVisible(contactUsNav);
	}
	
	public boolean isLogoutButtonVisible() {
		return isElementVisible(logoutButton);
	}
	
	public boolean isLoggedInAsVisible() {
		return isElementVisible(loggedInAs);
	}
	
	public boolean isHomePageLogoVisible() {
		return isElementVisible(homePageLogo);
	}
	
	
}





