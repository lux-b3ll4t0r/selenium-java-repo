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
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import io.restassured.response.Response;
import ui.constants.UrlConstants;

public class DeleteUserAccountTest extends APIBaseTest{
	
	@Test (groups = {"smoke"})
	public void deleteUserAccount() {
		LogUtil.info("Validating is delete successfully after sending delete request to \"" + UrlConstants.HOMEPAGE + ApiEndpoints.DELETE_ACCOUNT + "\".");
		
		User tempUser = UserDataGenerator.randomUser();
		Response tempUserResponse = AccountApi.createNewUser(tempUser);
		Assert.assertEquals(tempUserResponse.getStatusCode(), StatusCodes.OK);
		
		Response response = AccountApi.deleteUser(tempUser);
		LogUtil.info("Delete request sent");
		LogUtil.debug("RESPONSE: " + response.getBody().asPrettyString());
		
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		String responseCode = JsonUtil.getStringValue(response, JsonPaths.RESPONSE_CODE);
		int statusCode = response.getStatusCode();
		
		LogUtil.debug("Expected: {}, Actual: {}", StatusCodes.OK, statusCode);
		Assert.assertEquals(statusCode, StatusCodes.OK);
		LogUtil.debug("Expected: {}, Actual: {}", StatusCodes.OK, responseCode);
		Assert.assertEquals(responseCode, "200");
		
		LogUtil.debug("Expected: {}, Actual: {}", ResponseMessages.ACCOUNT_DELETED, message);
		Assert.assertEquals(ResponseMessages.ACCOUNT_DELETED, message);
		LogUtil.info("Account deleted successfully");
	}
	
	
}
