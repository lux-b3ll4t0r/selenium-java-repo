package tests.smoke.products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.NavBar;
import pages.products.AllProducts;
import utils.BasePage;
import utils.ConfigManager;
import utils.LogUtil;

public class ProductsListDisplayTest extends BaseTest{
	
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
	public void verifyAllProductsHeaderIsDisplayed() {
		
		LogUtil.info("[TEST STARTED]: Verifying All Products header is visible in the Products page");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Products link");
		navBar.clickProductsNav();
		
		Assert.assertTrue(products.isAllProductsHeaderVisible(), "All Products header not visible.");
		LogUtil.info("All Products header is visible");
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void verifyProductsListIsDisplayed() {
		
		LogUtil.info("[TEST STARTED]: Verifying all products are listed in the Products page");
		
		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Clicking Products link");
		navBar.clickProductsNav();
		
		Assert.assertTrue(products.isAllProductsHeaderVisible(), "All Products header not visible.");
		Assert.assertTrue(products.isProductsListDisplayed(), "Products list returned 0");
		LogUtil.info("All Products are visible");
		
	}
}
