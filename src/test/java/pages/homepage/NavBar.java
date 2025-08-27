package pages.homepage;

import org.openqa.selenium.By;

import utils.Webtool;

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
	private By loggedInAs = By.xpath("//i[@class='fa fa-user']/parent::a");
	private By logoutButton = By.xpath("//a[@href='/logout']");
	
	
	
	public void clickLogoutButton() {
		Webtool.clickElement(logoutButton);
	}
	
	public void clickHomeNav() {
		Webtool.clickElement(homeNav);
	}
	
	public void clickProductsNav() {
		Webtool.clickElement(productsNav);
	}
	
	public void clickCartNav() {
		Webtool.clickElement(cartNav);
	}
	
	public void clickSignUpLoginNav() {
		Webtool.clickElement(signUpLoginNav);
	}
	
	public void clickTestCasesNav() {
		Webtool.clickElement(testCasesNav);
	}
	
	public void clickApiTestingNav() {
		Webtool.clickElement(apiTestingNav);
	}
	
	public void clickVideoTutorialsNav() {
		Webtool.clickElement(videoTutorialsNav);
	}
	
	public void clickContactUsNav() {
		Webtool.clickElement(contactUsNav);
	}
	
	public boolean isHomeNavVisible() {
		return Webtool.isElementVisible(homeNav);
	}
	
	public boolean isProductsNavVisible() {
		return Webtool.isElementVisible(productsNav);
	}
	
	public boolean isCartNavVisible() {
		return Webtool.isElementVisible(cartNav);
	}
	
	public boolean isSignUpLoginNavVisible() {
		return Webtool.isElementVisible(signUpLoginNav);
	}
	
	public boolean isTestCasesNavVisible() {
		return Webtool.isElementVisible(testCasesNav);
	}
	
	public boolean isApiTestingNavVisible() {
		return Webtool.isElementVisible(apiTestingNav);
	}
	
	public boolean isVideoTutorialsNavVisible() {
		return Webtool.isElementVisible(videoTutorialsNav);
	}
	
	public boolean isContactUsNavVisible() {
		return Webtool.isElementVisible(contactUsNav);
	}
	
	public boolean isLogoutButtonVisible() {
		return Webtool.isElementVisible(logoutButton);
	}
	
	public boolean isLoggedInAsVisible() {
		return Webtool.isElementVisible(loggedInAs);
	}
	
	public boolean isHomePageLogoVisible() {
		return Webtool.isElementVisible(homePageLogo);
	}
	
	public By getLoggedInAs() {
		return loggedInAs;
	}
	
	public By getLogoutButton() {
		return logoutButton;
	}
	
}





