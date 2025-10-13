package ui.pages.homepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import ui.utils.Webtool;

public class Homepage {
	
	private By mainHeader = By.id("header");
	private By sliderSection = By.id("slider");
	private By leftSidebar = By.className("left-sidebar");
	private By productList = By.className("features_items");
	private By footer = By.id("footer");
	private By homeNav = By.partialLinkText("Home");
	private By productsNav = By.xpath("//a[@href='/products']");	
	private By cartNav = By.xpath(" //li//a[@href='/view_cart']");	
	private By signUpLoginNav = By.xpath("//a[@href='/login']");
	private By testCasesNav = By.xpath("//a[@href='/test_cases']");
	private By apiTestingNav = By.xpath("//a[@href='/api_list']");
	private By videoTutorialsNav = By.partialLinkText("Video Tutorials");
	private By contactUsNav = By.xpath("//a[@href='/contact_us']");
	private By homePageLogo = By.xpath("//img[@alt='Website for automation practice']");
	private By loggedInAs = By.xpath("//i[@class='fa fa-user']/parent::a");
	private By logoutButton = By.xpath("//a[@href='/logout']");
	private By deleteButton = By.xpath("//a[@href='/delete_account']");
	
	public By getLoggedInAs() {return loggedInAs;}
	public By getLogoutButton() {return logoutButton;}
	public By getHomeNav() {return homeNav;}
	public By getProductsNav() {return productsNav;}
	public By getCartNav() {return cartNav;}
	public By getSignUpLoginNav() {return signUpLoginNav;}
	public By getTestCasesNav() {return testCasesNav;}
	public By getApiTestingNav() {return apiTestingNav;}
	public By getVideoTutorialsNav() {return videoTutorialsNav;}
	public By getContactUsNav() {return contactUsNav;}
	public void deleteUser() {Webtool.clickElement(deleteButton);}
	public void logout() {Webtool.clickElement(logoutButton);}
	public void navigateTo(By nav) {Webtool.clickElement(nav);}
	public void navigateToHome() {Webtool.clickElement(homeNav);}
	public void navigateToProducts() {Webtool.clickElement(productsNav);}
	public void navigateToCart() {Webtool.clickElement(cartNav);}
	public void navigateToLogin() {Webtool.clickElement(signUpLoginNav);}
	public void navigateToSignup() {Webtool.clickElement(signUpLoginNav);}
	public void navigateToTestCases() {Webtool.clickElement(testCasesNav);}
	public void navigateToApiTesting() {Webtool.clickElement(apiTestingNav);}
	public void navigateToVideoTutorials() {Webtool.clickElement(videoTutorialsNav);}
	public void navigateToContactUs() {Webtool.clickElement(contactUsNav);}
	public boolean isMainHeaderVisible() {return Webtool.isElementVisible(mainHeader);}	
	public boolean isSliderSectionVisible() {return Webtool.isElementVisible(sliderSection);}	
	public boolean isLeftSiderBarVisible() {return Webtool.isElementVisible(leftSidebar);}
	public boolean isItemsContainerVisible() {return Webtool.isElementVisible(productList);}
	public boolean isFooterVisible() {return Webtool.isElementVisible(footer);}
	public boolean isHomeNavVisible() {return Webtool.isElementVisible(homeNav);}
	public boolean isProductsNavVisible() {return Webtool.isElementVisible(productsNav);}
	public boolean isCartNavVisible() {return Webtool.isElementVisible(cartNav);}
	public boolean isSignUpLoginNavVisible() {return Webtool.isElementVisible(signUpLoginNav);}
	public boolean isTestCasesNavVisible() {return Webtool.isElementVisible(testCasesNav);}
	public boolean isApiTestingNavVisible() {return Webtool.isElementVisible(apiTestingNav);}
	public boolean isVideoTutorialsNavVisible() {return Webtool.isElementVisible(videoTutorialsNav);}
	public boolean isContactUsNavVisible() {return Webtool.isElementVisible(contactUsNav);}
	public boolean isLogoutButtonVisible() {return Webtool.isElementVisible(logoutButton);}
	public boolean isLoggedInAsVisible() {return Webtool.isElementVisible(loggedInAs);}
	public boolean isHomePageLogoVisible() {return Webtool.isElementVisible(homePageLogo);}

	
	public boolean isHomePageVisible() {
			return isMainHeaderVisible() && isSliderSectionVisible() 
			&& isLeftSiderBarVisible() && isItemsContainerVisible() && isFooterVisible();
	}
	
	
	public List<By> getBody() {
		List<By> body = new ArrayList<>();
		body.add(mainHeader);
		body.add(sliderSection);
		body.add(leftSidebar);
		body.add(productList);
		body.add(footer);
		return body; 
	}
}
