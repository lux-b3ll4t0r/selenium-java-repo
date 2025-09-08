package tests.api.smoke.account;


import org.testng.Assert;
import org.testng.annotations.Test;

import base.APIBaseTest;
import constants.api.ApiEndpoints;
import constants.api.JsonPaths;
import constants.api.ResponseMessages;
import constants.api.StatusCodes;
import constants.ui.UrlConstants;
import io.restassured.response.Response;
import services.account.AccountApi;
import utils.JsonUtil;
import utils.LogUtil;

public class ValidLoginTest extends APIBaseTest{

	@Test (groups = {"smoke"})
	public void validLoginTest() {
		LogUtil.info("Verifying successful login to \"" + UrlConstants.BASE + ApiEndpoints.VERIFY_LOGIN + "\" with valid credentials.");
		Response response = AccountApi.login(System.getenv("API_USER"), System.getenv("API_PASS"));
		
		LogUtil.info("Post request sent");
		LogUtil.debug("RESPONSE: " + response.getBody().asPrettyString());	

		int statusCode = response.getStatusCode();
		LogUtil.debug("Expected: {}, Actual: {}", StatusCodes.OK, statusCode);
		Assert.assertEquals(statusCode, StatusCodes.OK);
		
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.debug("Expected: {}, Actual: {}", ResponseMessages.USER_EXISTS, message);
		Assert.assertTrue(message.contains(ResponseMessages.USER_EXISTS));
		LogUtil.info("User found successfully");
	}
	

}
