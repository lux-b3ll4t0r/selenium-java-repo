package smoke.products;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.NavBar;
import pages.products.AllProducts;
import utils.BasePage;
import utils.CommonFunctions;
import utils.ConfigManager;
import utils.LogUtil;

public class ProductsListDisplayTest extends BaseTest{
	
	NavBar navBar;
	AllProducts products;
	BasePage basePage;
	CommonFunctions comFunc;
	
	@BeforeMethod(alwaysRun = true)
	public void setupResources() {
		LogUtil.debug("Setting up test resources");
		navBar = new NavBar(driver);
		products = new AllProducts(driver);
		LogUtil.debug("Set up successfully");
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void verifyProductsListIsDisplayed() {
		
		LogUtil.info("[TEST STARTED]: Verifying all products are listed in the Products page");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Products link");
		navBar.clickProductsNav();
		
		wait.until(ExpectedConditions.)
	}
}
