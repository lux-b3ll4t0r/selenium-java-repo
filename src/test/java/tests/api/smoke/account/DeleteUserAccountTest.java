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
import utils.User;
import utils.UserDataGenerator;

public class DeleteUserAccountTest extends APIBaseTest{
	
	@Test (groups = {"smoke"})
	public void deleteUserAccount() {
		LogUtil.info("Validating is delete successfully after sending delete request to \"" + UrlConstants.BASE + ApiEndpoints.DELETE_ACCOUNT + "\".");
		
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
