package api.tests.account;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.constants.StatusCodes;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import common.utils.LogUtil;
import io.restassured.response.Response;
import ui.constants.UrlConstants;

public class GetUserAccountDetailsTest extends APIBaseTest{
	 
	@Test (groups = {"smoke"})
	public void getUserAccountDetails() {
		LogUtil.info("Validating account details are returned when sending a request to \"" + UrlConstants.HOMEPAGE + ApiEndpoints.GET_USER_DETAIL + "\".");
		
		String email = System.getenv("API_USER");
		
		Response response = AccountApi.getUserInfo(email);	
		
		LogUtil.info("Get request sent");
		LogUtil.debug("RESPONSE: " + response.getBody().asPrettyString());
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, StatusCodes.OK);			
		
		String responseEmail = JsonUtil.getStringValue(response, JsonPaths.EMAIL);
		LogUtil.debug("Expected: {}, Actual: {}", email, responseEmail);
		Assert.assertTrue(responseEmail.contains(email));
		LogUtil.info("User details returned successfully");
	}
	
}
