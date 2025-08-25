package tests.smoke.products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.NavBar;
import pages.products.AllProducts;
import utils.BasePage;
import utils.ConfigManager;
import utils.LogUtil;

@Listeners(utils.TestListener.class)
public class ProductsDisplayTest extends BaseTest{
	
	NavBar navBar;
	AllProducts products;
	BasePage basePage;
	
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
		
		Assert.assertTrue(products.isProductsListVisible(), "Products not visible");
		LogUtil.info("All Products are visible");
		
		LogUtil.info("[TEST COMPLETED]: Verifying all products are listed in the Products page");
		
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void verifySearchedProductsDisplayed() {
		LogUtil.info("[TEST STARTED]: Verifying products are shown when searching for a product");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Products link");
		navBar.clickProductsNav();
		
		LogUtil.info("Searching for product: \"shirt\"");
		products.searchProduct("shirt");
		
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getProductsUrl() + "?search=shirt");
		Assert.assertTrue(products.isProductsListVisible());
		LogUtil.info("Directed to correct url, and products relating to search are shown successfully");
		
	}
}
