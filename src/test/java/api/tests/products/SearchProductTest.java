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

public class SearchProductTest extends APIBaseTest{
	
	@Test (groups = {"smoke"})
	public void searchProductTest() {
		LogUtil.info("Verifying products are returned with searching to \"" + UrlConstants.HOMEPAGE + ApiEndpoints.SEARCH_PRODUCT + "\".");
		
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
