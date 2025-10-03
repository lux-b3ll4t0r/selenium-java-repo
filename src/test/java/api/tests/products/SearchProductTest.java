package api.tests.products;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.constants.ResponseCodes;
import api.constants.ResponseMessages;
import api.constants.StatusCodes;
import api.services.ProductsApi;
import api.tests.base.APIBaseTest;
import api.utils.APITools;
import api.utils.JsonUtil;
import api.utils.RequestFactory;
import common.pojos.APIProduct;
import common.utils.ConfigManager;
import common.utils.LogUtil;
import io.restassured.response.Response;


public class SearchProductTest extends APIBaseTest{
	
	@Test (groups = {"smoke"}, priority = 0)
	public void search_product_status_smoke_test() {
		LogUtil.info("Verifying product search returns successful status and response code.");
		
		LogUtil.info("Sending request to search for product.");
		Response response = ProductsApi.searchProduct("shirt");
		
		int statusCode = response.getStatusCode();
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		LogUtil.info("Status Code: " + statusCode + " - " + "Response Code: " + responseCode);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(statusCode, StatusCodes.OK);
		softAssert.assertEquals(responseCode, ResponseCodes.OK);
		softAssert.assertAll();

		LogUtil.info("Status and response code returned successful.");
	}
	
	@Test (groups = {"smoke"}, priority = 1)
	public void verify_search_list_smoke_test() {
		LogUtil.info("Verifying search list returns the list of products.");
		
		LogUtil.info("Sending request to search for product.");
		Response response = ProductsApi.searchProduct("shirt");
		List<String> products = JsonUtil.getList(response, JsonPaths.PRODUCTS);
		
		Assert.assertTrue(products.size() > 0);
		LogUtil.info("Searched products list returned successfully.");
	
	}
	
	@Test (groups = {"functional"}, priority = 2)
	public void verify_search_list_schema_functional_test() {
		LogUtil.info("Verifying search list response schema matches expected Json schema.");
		
		LogUtil.info("Sendring request to search for product.");
		Response response = ProductsApi.searchProduct("shirt");
		
		Assert.assertTrue(JsonUtil.bodyMatchesSchema(response, ConfigManager.getProductsSchema()));
		LogUtil.info("Searched product schema matches expected Json schema.");
	}
	
	@Test(groups = {"functional"}, priority = 3)
	public void verify_search_list_details_functional_test() {
		LogUtil.info("Verifying all product details are returned in the search list.");
		
		LogUtil.info("Searching for product");
		Response response = ProductsApi.searchProduct("shirt");
		List<APIProduct> products = JsonUtil.getListAsObject(response, JsonPaths.PRODUCTS, APIProduct.class);
		SoftAssert softAssert = new SoftAssert();
		
		for(APIProduct product : products) {
			softAssert.assertNotNull(product.getId(), "Searched product ID was null. Returned: " + product);
			softAssert.assertNotNull(product.getName(), "Searched product name was null. Returned: " + product);
			softAssert.assertNotNull(product.getPrice(), "Searched product price was null. Returned: " + product);
			softAssert.assertNotNull(product.getBrand(), "Searched product brand was null. Returned: " + product);
			softAssert.assertNotNull(product.getCategory().getCategory(), "Searched product cateogyr was null. Returned: " + product);
			softAssert.assertNotNull(product.getCategory().getUsertype().getUsertype(), "Searched product user type was null. Returned: " + product);
		}
		
		softAssert.assertAll();
		LogUtil.info("All searched product details returned successfully.");
	}
	
	@Test(groups = {"functional"}, priority = 4, dataProvider = "searchInputs")
	public void verify_search_list_category_functional_test(String search) {
		LogUtil.info("Verifying category of searched products contain search input.");
		
		LogUtil.info("Sending request to search for product: " + search);
		Response response = ProductsApi.searchProduct(search);
		List<APIProduct> products = JsonUtil.getListAsObject(response, JsonPaths.PRODUCTS, APIProduct.class);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(products.size() > 0, "No results were returned from search: \"" + search + "\"");
		
		for(APIProduct product : products) {
			String category = product.getCategory().getCategory().toLowerCase();
			String name = product.getName().toLowerCase();
			softAssert.assertTrue(category.contains(search) || name.contains(search), "Product: " + product + " did not contain category: " + category);
		}
		
		softAssert.assertAll();
		LogUtil.info("All product categories contain search input.");
	}
	
	@DataProvider(name = "searchInputs")
	public Object[][] searchInputs(){
		return new Object[][] {
			{"dress"},
			{"shirt"},
			{"jeans"},
			{"top"},
			{"saree"}
		};
	}
	
	@Test(groups = {"functional", "negative"}, priority = 4, dataProvider = "negativeInputs")
	public void verify_search_list_negative_test(String search) {
		LogUtil.info("Verifying invalid inputs return no products.");
		
		LogUtil.info("Sending request to search for product: " + search);
		Response response = ProductsApi.searchProduct(search.toLowerCase());
		List<String> products = JsonUtil.getList(response, JsonPaths.PRODUCTS);
	
		Assert.assertTrue(products.size() == 0, "Search returned a list from a negative input. Products: " + products);

		LogUtil.info("No products were returned for the negative input.");
	}
	
	@DataProvider(name = "negativeInputs")
	public Object[][] negativeInputs(){
		return new Object[][] {
			{"1"},
			{"@"},
			{"24 jeans"},
			{"select"},
			{"computer"},
		};
	}
	
	@Test (groups = {"functional", "negative"}, priority = 5)
	public void verify_no_search_parameter_negative_test() {
		LogUtil.info("Verifying response rejects request when the required search parameter is missing.");
		
		Response response = APITools.post(RequestFactory.getBaseSpec(), ApiEndpoints.SEARCH_PRODUCT);
		
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + responseCode + " - Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.BAD_REQUEST, "Response code return incorrect code.");
		softAssert.assertEquals(message, ResponseMessages.SEARCH_PARAM_MISSING, "Response message return incorrect message.");
		softAssert.assertAll();
		LogUtil.info("Request reject successfully.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 5, dataProvider = "invalidMethods")
	public void verify_invalid_request_method_negative_test(String method, int responseCode, String responseMessage) {
		LogUtil.info("Verifying invalid HTTP methods are rejected.");
		
		LogUtil.info("Sending request using: " + method);
		Response response = APITools.sendRequest(RequestFactory.getBaseSpec(), method, ApiEndpoints.SEARCH_PRODUCT);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE), responseCode);
		softAssert.assertEquals(JsonUtil.getStringValue(response, JsonPaths.MESSAGE), responseMessage);
		softAssert.assertAll();
		LogUtil.info("Method rejected successfully.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"GET", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
			{"PUT", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
			{"DELETE", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
		};
	}
}
