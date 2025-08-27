package tests.smoke.products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.UrlConstants;
import pages.homepage.NavBar;
import pages.products.AllProducts;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class ProductsDisplayTest extends BaseTest{
	
	NavBar navBar;
	AllProducts products;
	Webtool basePage;
	
	@BeforeMethod(alwaysRun = true)
	public void setupResources() {
		LogUtil.trace("Setting up test resources");
		navBar = new NavBar();
		products = new AllProducts();
		LogUtil.trace("Set up successfully");
		
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}
	
	
	
	@Test(groups = {"smoke"}, priority = 0)
	public void verifyProductsListIsDisplayed() {
		
		LogUtil.info("* Verifying all products are listed in the Products page");	
		
		LogUtil.info("Clicking Products link");
		navBar.clickProductsNav();
		
		Assert.assertTrue(products.isProductsListVisible(), "Products not visible");
		LogUtil.info("All Products are visible");
	
		
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void verifySearchedProductsDisplayed() {
		LogUtil.info("* Verifying products are shown when searching for a product");
		
		LogUtil.info("Clicking Products link");
		navBar.clickProductsNav();
		
		LogUtil.info("Searching for product: \"shirt\"");
		products.searchProduct("shirt");
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.PRODUCTS + "?search=shirt");
		Assert.assertTrue(products.isProductsListVisible());
		LogUtil.info("Directed to correct url, and products relating to search are shown successfully");
		
	}
}
