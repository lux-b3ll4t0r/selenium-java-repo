package tests.api.smoke.account;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.APIBaseTest;
import constants.api.ApiEndpoints;
import constants.api.JsonPaths;
import constants.api.StatusCodes;
import constants.ui.UrlConstants;
import io.restassured.response.Response;
import services.account.AccountApi;
import utils.JsonUtil;
import utils.LogUtil;

public class GetUserAccountDetailsTest extends APIBaseTest{
	 
	@Test (groups = {"smoke"})
	public void getUserAccountDetails() {
		LogUtil.info("Validating account details are returned when sending a request to \"" + UrlConstants.BASE + ApiEndpoints.GET_USER_DETAIL + "\".");
		
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
