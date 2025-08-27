package pages.homepage;

import org.openqa.selenium.By;

import utils.BasePage;

public class NavBar {
	
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
		BasePage.clickElement(logoutButton);
	}
	
	public void clickHomeNav() {
		BasePage.clickElement(homeNav);
	}
	
	public void clickProductsNav() {
		BasePage.clickElement(productsNav);
	}
	
	public void clickCartNav() {
		BasePage.clickElement(cartNav);
	}
	
	public void clickSignUpLoginNav() {
		BasePage.clickElement(signUpLoginNav);
	}
	
	public void clickTestCasesNav() {
		BasePage.clickElement(testCasesNav);
	}
	
	public void clickApiTestingNav() {
		BasePage.clickElement(apiTestingNav);
	}
	
	public void clickVideoTutorialsNav() {
		BasePage.clickElement(videoTutorialsNav);
	}
	
	public void clickContactUsNav() {
		BasePage.clickElement(contactUsNav);
	}
	
	public boolean isHomeNavVisible() {
		return BasePage.isElementVisible(homeNav);
	}
	
	public boolean isProductsNavVisible() {
		return BasePage.isElementVisible(productsNav);
	}
	
	public boolean isCartNavVisible() {
		return BasePage.isElementVisible(cartNav);
	}
	
	public boolean isSignUpLoginNavVisible() {
		return BasePage.isElementVisible(signUpLoginNav);
	}
	
	public boolean isTestCasesNavVisible() {
		return BasePage.isElementVisible(testCasesNav);
	}
	
	public boolean isApiTestingNavVisible() {
		return BasePage.isElementVisible(apiTestingNav);
	}
	
	public boolean isVideoTutorialsNavVisible() {
		return BasePage.isElementVisible(videoTutorialsNav);
	}
	
	public boolean isContactUsNavVisible() {
		return BasePage.isElementVisible(contactUsNav);
	}
	
	public boolean isLogoutButtonVisible() {
		return BasePage.isElementVisible(logoutButton);
	}
	
	public boolean isLoggedInAsVisible() {
		return BasePage.isElementVisible(loggedInAs);
	}
	
	public boolean isHomePageLogoVisible() {
		return BasePage.isElementVisible(homePageLogo);
	}
	
	
}





