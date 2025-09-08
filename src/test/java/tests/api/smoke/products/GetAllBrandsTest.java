package tests.api.smoke.products;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.APIBaseTest;
import constants.api.ApiEndpoints;
import constants.api.JsonPaths;
import constants.api.StatusCodes;
import constants.ui.UrlConstants;
import io.restassured.response.Response;
import services.products.ProductsApi;
import utils.JsonUtil;
import utils.LogUtil;

public class GetAllBrandsTest extends APIBaseTest{
		
	@Test (groups = {"smoke"})
	public void getAllBrandsTest() {
		LogUtil.info("Verifying all brands are listed when calling \"" + UrlConstants.BASE + ApiEndpoints.BRANDS_LIST + "\".");
	
		Response response = ProductsApi.getAllBrands();
		LogUtil.info("Get request sent");
		LogUtil.debug("RESPONSE: " + response.getBody().asPrettyString());
		
		int statusCode = response.getStatusCode();
		LogUtil.debug("Expected: {}, Actual: {} ", StatusCodes.OK, statusCode);
		Assert.assertEquals(statusCode, StatusCodes.OK);
		
		List<String> brandsList = JsonUtil.getList(response, JsonPaths.BRANDS);
		LogUtil.debug("Expected: {Brand count > 0}, Actual: {" + brandsList.size() +"}");
		Assert.assertTrue(brandsList.size() > 0, "No brands returned.");
		
		String firstBrand = JsonUtil.getStringValue(response, JsonPaths.BRANDS + "[0].brand");
		Assert.assertFalse(firstBrand.isEmpty(), "First brand name not returned.");
		LogUtil.info("First brand returned: " + firstBrand);
		
		LogUtil.info("Brands returned successfully");
	}

}
