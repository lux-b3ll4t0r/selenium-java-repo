package pages.homepage;

import org.openqa.selenium.By;

import utils.BasePage;

public class NavBar {
	
	private By homeNav = By.partialLinkText("Home");
	private By productsNav = By.xpath("//a[@href='/products']");	
	private By cartNav = By.xpath("//a[@href='/view_cart']");	
	private By signUpLoginNav = By.xpath("//a[@href='/login']");
	private By testCasesNav = By.xpath("//a[@href='/test_cases']");
	private By apiTestingNav = By.xpath("//a[@href='/api_list']");
	private By videoTutorialsNav = By.partialLinkText("Video Tutorials");
	private By contactUsNav = By.xpath("//a[@href='/contact_us']");
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
	
	public By getLoggedInAs() {
		return loggedInAs;
	}
	
	public By getLogoutButton() {
		return logoutButton;
	}
	
}





