package api.tests.account;


import org.testng.Assert;
import org.testng.annotations.Test;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.constants.ResponseMessages;
import api.constants.StatusCodes;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import common.utils.LogUtil;
import io.restassured.response.Response;
import ui.constants.UrlConstants;

public class ValidLoginTest extends APIBaseTest{

	@Test (groups = {"smoke"})
	public void validLoginTest() {
		LogUtil.info("Verifying successful login to \"" + UrlConstants.HOMEPAGE + ApiEndpoints.VERIFY_LOGIN + "\" with valid credentials.");
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
