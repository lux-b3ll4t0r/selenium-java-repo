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

public class SearchProductTest extends APIBaseTest{
	
	@Test (groups = {"smoke"})
	public void searchProductTest() {
		LogUtil.info("Verifying products are returned with searching to \"" + UrlConstants.BASE + ApiEndpoints.SEARCH_PRODUCT + "\".");
		
		Response response = ProductsApi.searchProduct("shirt");
		LogUtil.info("Post request sent");
		LogUtil.debug("RESPONSE: " + response.getBody().asPrettyString());
		
		int statusCode = response.getStatusCode();
		LogUtil.debug("Expected: {}, Actual: {}", StatusCodes.OK, statusCode);
		Assert.assertEquals(statusCode, StatusCodes.OK);
		
		List<String> productsList = JsonUtil.getList(response, JsonPaths.PRODUCTS);
		LogUtil.debug("Expected: {Products count > 0}, Actual {" + productsList.size() + "}");
		Assert.assertTrue(productsList.size() > 0);
		LogUtil.info("Searched product returned successfully");
	}
}
