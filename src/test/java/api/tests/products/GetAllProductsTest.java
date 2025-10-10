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
import api.pojos.APICategory;
import api.pojos.APIProduct;
import api.pojos.APIUserType;
import api.services.ProductsApi;
import api.tests.base.APIBaseTest;
import api.utils.APITools;
import api.utils.JsonUtil;
import api.utils.RequestFactory;
import common.utils.ConfigManager;
import common.utils.LogUtil;
import io.restassured.response.Response;

public class GetAllProductsTest extends APIBaseTest{	
	
	@Test(groups = {"smoke"}, priority = 0)
	public void verify_products_status_smoke_test() {
		LogUtil.info("Verifying products list returns a successful status and response code.");
		
		LogUtil.info("Sending request to get all products.");
		Response response = ProductsApi.getAllProducts();
		
		int statusCode = response.getStatusCode();
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		LogUtil.info("Status Code: " + statusCode + " - Response Code: " + responseCode);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(statusCode, StatusCodes.OK, "Status code doesn't match.");
		softAssert.assertEquals(responseCode, ResponseCodes.OK, "Response code doesn't match.");
		softAssert.assertAll();
		
		LogUtil.info("Status code and response code returned successful.");
	}
	
	@Test(groups = {"smoke"}, priority = 1)
	public void verify_products_list_smoke_test() {
		LogUtil.info("Verifying the products list returns the list of products.");
		
		LogUtil.info("Sending request to get all products.");
		Response response = ProductsApi.getAllProducts();
		
		List<String> products = JsonUtil.getList(response, JsonPaths.PRODUCTS);
		Assert.assertTrue(products.size() > 0);
		LogUtil.info("Products list returned successfully.");
	}
	
	
	@Test(groups = {"functional"}, priority = 2)
	public void verify_products_schema_functional_test() {
		LogUtil.info("Verifying products list response schema matches expected Json schema.");
		
		LogUtil.info("Sending request to get all products.");
		Response response = ProductsApi.getAllProducts();
	
		Assert.assertTrue(JsonUtil.bodyMatchesSchema(response, ConfigManager.getProductsSchema()));
		LogUtil.info("Products response schema matches expected Json schema.");
	}
	
	@Test (groups = {"functional"}, priority = 3)
	public void verify_product_details_functional_test() {
		LogUtil.info("Verifying product details are returned for all products.");
		
		LogUtil.info("Sending request to get all products.");
		Response response = ProductsApi.getAllProducts();
		
		List<APIProduct> productsList = JsonUtil.getListAsObject(response, JsonPaths.PRODUCTS, APIProduct.class);
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Searching through all products.");
		for(APIProduct product : productsList) {
			softAssert.assertNotNull(product.getId(), "Product ID was null. Returned: " + product.toString());
			softAssert.assertNotNull(product.getName(), "Product name was null. Returned: " + product.toString());
			softAssert.assertNotNull(product.getPrice(), "Product price was null. Returned: " + product.toString());
			softAssert.assertNotNull(product.getBrand(), "Product brand was null. Returned: " + product.toString());
			softAssert.assertNotNull(product.getCategory().getCategory(), "Category was null. Returned: " + product.toString());
			softAssert.assertNotNull(product.getCategory().getUsertype().getUsertype(), "User type was null. Returned: " + product.toString());
		}
		
		softAssert.assertAll();
		LogUtil.info("All products were returned successfully.");		
	}
	
	@Test (groups = {"functionality"}, priority = 4)
	public void verify_product_usertype_functional_test() {
		LogUtil.info("Verifying product user type is a valid user type.");
		
		LogUtil.info("Sending request to get all products.");
		Response response = ProductsApi.getAllProducts();
		
		List<String> validTypes = APIUserType.VALID_TYPES;
		LogUtil.info("Valid user types: " + validTypes);
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Searching through all products.");
		for(APIProduct product : JsonUtil.getListAsObject(response, JsonPaths.PRODUCTS, APIProduct.class)) {
			softAssert.assertTrue(validTypes.contains(product.getCategory().getUsertype().getUsertype()));
		}
		
		softAssert.assertAll();
		LogUtil.info("All products contain valid user types.");
	}
	
	@Test (groups = {"functional"}, priority = 5)
	public void verify_product_category_functional_test() {
		LogUtil.info("Verifying product category is a valid category.");
		
		LogUtil.info("Sending request to get all products.");
		Response response = ProductsApi.getAllProducts();
		
		List<String> validTypes = APICategory.VALID_TYPES;
		LogUtil.info("Valid categories: " + validTypes);
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Searching through all products.");
		for(APIProduct product : JsonUtil.getListAsObject(response, JsonPaths.PRODUCTS, APIProduct.class)) {
			softAssert.assertTrue(validTypes.contains(product.getCategory().getCategory()));
		}
		
		softAssert.assertAll();
		LogUtil.info("All products contain valid category types.");
	}
	
	@Test (groups = {"functional", "negative"}, dataProvider = "invalidMethods", priority = 6)
	public void verify_invalid_request_methods_negative_test(String method, int responseCode, String responseMessage) {
		LogUtil.info("Verifying invalid HTTP methods are rejected.");
		
		LogUtil.info("Sending request using: " + method);
		Response response = APITools.sendRequest(RequestFactory.getBaseSpec(), method, ApiEndpoints.PRODUCTS_LIST);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE), responseCode);
		softAssert.assertEquals(JsonUtil.getStringValue(response, JsonPaths.MESSAGE), responseMessage);
		softAssert.assertAll();
		LogUtil.info("Method rejected successfully.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"POST", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
			{"PUT", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
			{"DELETE", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
		};
	}
	
}
