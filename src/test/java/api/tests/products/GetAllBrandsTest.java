package api.tests.products;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.constants.StatusCodes;
import api.services.ProductsApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import common.utils.LogUtil;
import io.restassured.response.Response;
import ui.constants.UrlConstants;

public class GetAllBrandsTest extends APIBaseTest{
		
	@Test (groups = {"smoke"})
	public void getAllBrandsTest() {
		LogUtil.info("Verifying all brands are listed when calling \"" + UrlConstants.HOMEPAGE + ApiEndpoints.BRANDS_LIST + "\".");
	
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
