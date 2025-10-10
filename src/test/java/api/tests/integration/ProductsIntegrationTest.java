package api.tests.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.pojos.APIBrand;
import api.pojos.APIProduct;
import api.services.ProductsApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import api.utils.ResponseValidator;
import common.utils.LogUtil;
import io.restassured.response.Response;

public class ProductsIntegrationTest extends APIBaseTest{

	
	@Test (groups = {"integration"}, priority = 0)
	public void verify_product_list_and_brand_list_integration_test() {
		LogUtil.info("Verifying product id and brand name are corresponding in both product list and brand list.");
		
		LogUtil.info("Sending request to: " + ApiEndpoints.PRODUCTS_LIST);
		Response productList = ProductsApi.getAllProducts();
		ResponseValidator.verifyProductsListStatus(productList);
		
		LogUtil.info("Sending request to: " + ApiEndpoints.BRANDS_LIST);
		Response brandList = ProductsApi.getAllBrands();
		ResponseValidator.verifyBrandsListStatus(brandList);
		
		List<APIProduct> allProducts = JsonUtil.getListAsObject(productList, JsonPaths.PRODUCTS, APIProduct.class);
		List<APIBrand> allBrands = JsonUtil.getListAsObject(brandList, JsonPaths.BRANDS, APIBrand.class);
		Map<Integer, String> brandsMap = new HashMap<>();
		
		LogUtil.info("Getting all IDs and brands from brands list.");
		for(APIBrand brand : allBrands) {
			brandsMap.put(brand.getId(), brand.getBrand());
		}
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Getting and comparing all brands from products list.");
		for(APIProduct product : allProducts) {
			int productId = product.getId();
			String productBrand = product.getBrand();
		
			softAssert.assertTrue(brandsMap.containsKey(productId), "Product ID: '" + productId + "' was missing from brands list.");
			softAssert.assertEquals(brandsMap.get(productId), productBrand, "Product ID: '" + productId + "' brand did not match in brands list.");
		}
		
		softAssert.assertAll();
		LogUtil.info("All IDs and Brands correctly map between products list and brands list.");
	}
	
	@Test (groups = {"integration"}, priority = 1)
	public void verify_product_list_and_search_list_integration_test() {
		LogUtil.info("Verifying searched products correctly map to products in products list.");
		
		LogUtil.info("Sending request to :" + ApiEndpoints.PRODUCTS_LIST);
		Response productList = ProductsApi.getAllProducts();
		ResponseValidator.verifyProductsListStatus(productList);
		
		LogUtil.info("Sending request to search for product 'shirt' to: " + ApiEndpoints.SEARCH_PRODUCT);
		Response searchProduct = ProductsApi.searchProduct("shirt");
		ResponseValidator.verifySearchListStatus(searchProduct);
		
		List<APIProduct> allProducts = JsonUtil.getListAsObject(productList, JsonPaths.PRODUCTS, APIProduct.class);
		List<APIProduct> searchedProducts = JsonUtil.getListAsObject(searchProduct, JsonPaths.PRODUCTS, APIProduct.class);
		
		LogUtil.info("Getting all products returned from search.");
		Map<Integer, APIProduct> allProductsMap = allProducts.stream()
			    .collect(Collectors.toMap(APIProduct::getId, p -> p));
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Comparing searched products with products list.");
		for(APIProduct search : searchedProducts) {
				
			int id = search.getId();
			APIProduct product = allProductsMap.get(id);			
			
			softAssert.assertNotNull(product, "Searched product did not exist in products list.");
			softAssert.assertTrue(search.equals(product), "Searched product did not match product in products list.");
		}
		
		softAssert.assertAll();
		LogUtil.info("All products returned from search list correctly map to products in products list.");
	}
}
