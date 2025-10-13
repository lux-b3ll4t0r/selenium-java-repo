package ui.tests.products;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.common.ProductDetails;
import ui.pages.common.ViewProduct;
import ui.pages.homepage.Homepage;
import ui.pages.products.Products;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

public class ProductsTest extends UIBaseTest{
	
	private Homepage homepage;
	private Products products;
	private ViewProduct viewProduct;
	private ProductDetails productDetails;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		homepage = new Homepage();
		products = new Products();
		viewProduct = new ViewProduct();
		productDetails = new ProductDetails();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}
	
	@Test(groups = {"smoke"}, priority = 0)
	public void products_list_smoke_test() {
		LogUtil.info("Verifying all products are listed in the Products page.");	
		
		LogUtil.info("Navigating to Products.");
		homepage.navigateToProducts();
		
		Assert.assertTrue(productDetails.isProductsListVisible(), "Products not visible.");
		LogUtil.info("All Products are visible.");
	}
	
	
	@Test(groups = {"smoke"}, priority = 1)
	public void search_smoke_test() {
		LogUtil.info("Verifying searching for a product returns matching results.");
		
		LogUtil.info("Navigating to Products.");
		homepage.navigateToProducts();
		
		LogUtil.info("Searching for product: \"shirt\".");
		products.searchProduct("shirt");
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.PRODUCTS + "?search=shirt");
		Assert.assertTrue(productDetails.isProductsListVisible(), "The list of products was not visible.");
		LogUtil.info("Directed to correct url, and products relating to search are shown successfully.");
	}
	
	@Test(groups = {"functional"}, priority = 2, dataProvider = "search")
	public void search_functional_test(String search) {
		LogUtil.info("Verifying products details match the searched product.");
		
		LogUtil.info("Navigating to Products.");
		homepage.navigateToProducts();
		
		LogUtil.info("Searching for product: " + search);
		products.searchProduct(search);
		
		productDetails.clickViewProduct();
		
		String productName = viewProduct.getName().toLowerCase();
		String productCategory = viewProduct.getCategory().toLowerCase();
		
		Assert.assertTrue(productName.contains(search) || productCategory.contains(search), "Product details does not contain search query: " + search);
		LogUtil.info("Product details match searched product.");
	}
	
	@DataProvider(name = "search")
	public Object[][] search(){
		return new Object[][] {
			{"shirt"},
			{"dress"},
			{"top"},
			{"jeans"}
		};
	}

	@Test(groups = {"functional", "negative"}, priority = 2, dataProvider = "negativeSearch")
	public void search_negative_test(String search) {
		LogUtil.info("Verifying invalid search values return no products.");
		
		LogUtil.info("Navigating to Products.");
		homepage.navigateToProducts();
		
		LogUtil.info("Searching for product: " + search);
		products.searchProduct(search);
		
		Assert.assertFalse(productDetails.isProductsListVisible());
		LogUtil.info("Invalid search values returned no results.");
	}
	
	@DataProvider(name = "negativeSearch")
	public Object[][] negativeSearch(){
		return new Object[][] {
			{"1"},
			{"@"},
			{"s h i r t"},
			{"SELECT * FROM products;"}
		};
	}
}
