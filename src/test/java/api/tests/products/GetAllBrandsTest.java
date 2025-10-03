package api.tests.products;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.constants.JsonPaths;
import api.constants.ResponseCodes;
import api.constants.StatusCodes;
import api.services.ProductsApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import common.pojos.APIBrand;
import common.utils.ConfigManager;
import common.utils.LogUtil;
import io.restassured.response.Response;

public class GetAllBrandsTest extends APIBaseTest{
		
	@Test (groups = {"smoke"}, priority = 0)
	public void verify_brands_status_smoke_test() {
		LogUtil.info("Verifying brands list returns successful status and response code.");
	
		LogUtil.info("Sending request to get all brands.");
		Response response = ProductsApi.getAllBrands();
		
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
	public void verify_brands_list_smoke_test() {
		LogUtil.info("Verifying the brands list returns the list of brands");
		
		LogUtil.info("Sending request to get all brands.");
		Response response = ProductsApi.getAllBrands();
		
		List<String> brands = JsonUtil.getList(response, JsonPaths.BRANDS);
		Assert.assertTrue(brands.size() > 0);
	}
	
	@Test (groups = {"functional"}, priority = 2)
	public void verify_brands_schema_functional_test() {
		LogUtil.info("Verifying the brands list response schema matches expected Json schema.");
		
		LogUtil.info("Sending request to get all brands.");
		Response response = ProductsApi.getAllBrands();
		
		Assert.assertTrue(JsonUtil.bodyMatchesSchema(response, ConfigManager.getBrandsSchema()));
		LogUtil.info("Brands response schema matches expected Json schema.");
	}
	
	@Test (groups = {"functional"}, priority = 2)
	public void verify_brand_details_functional_test() {
		LogUtil.info("Verifying brand details are returned for all brands. ");
		
		LogUtil.info("Sending request to get all brands.");
		Response response = ProductsApi.getAllBrands();
		
		List<APIBrand> brands = JsonUtil.getListAsObject(response, JsonPaths.BRANDS, APIBrand.class);
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Searching through all brands.");
		for(APIBrand brand : brands) {
			softAssert.assertNotNull(brand.getId(), "Brand ID was null. Returned: " + brand.toString());
			softAssert.assertNotNull(brand.getBrand(), "Brand name was null. Returned: " + brand.toString());
		}
		softAssert.assertAll();
		LogUtil.info("All brands were returned successfully.");
	}
	
	@Test (groups = {"functional"}, priority = 3)
	public void verify_brand_name_functional_test() {
		LogUtil.info("Verifying brand name is a valid category.");
		
		LogUtil.info("Sending request to get all brands.");
		Response response = ProductsApi.getAllProducts();
		
		List<String> validTypes = APIBrand.VALID_TYPES;
		LogUtil.info("Valid categories: " + validTypes);
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Searching through all brands.");
		for(APIBrand brand : JsonUtil.getListAsObject(response, JsonPaths.BRANDS, APIBrand.class)) {
			softAssert.assertTrue(validTypes.contains(brand.getBrand()));
		}
		
		softAssert.assertAll();
		LogUtil.info("All brands contain valid brand names.");
	}
}
