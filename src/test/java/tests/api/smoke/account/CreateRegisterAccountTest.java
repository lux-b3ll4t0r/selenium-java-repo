package tests.api.smoke.account;

import java.sql.Connection;

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
import utils.SQLWorkbench;
import utils.User;
import utils.UserDataGenerator;

public class CreateRegisterAccountTest extends APIBaseTest{
	
	@Test (groups = {"smoke"})
	public void createRegisterAccount() {
		LogUtil.info("Verifying successful account creation/registration to \"" + UrlConstants.BASE + ApiEndpoints.CREATE_ACCOUNT+ "\".");
		
		User user = UserDataGenerator.randomUser();
		Response response = AccountApi.createNewUser(user);
		
		// Extracting response message and api response code
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		String responseCode = JsonUtil.getStringValue(response, JsonPaths.RESPONSE_CODE);
		int statusCode = response.statusCode();
		
		// Validating both HTTP code and Application level status code
		LogUtil.debug("Expected: {}, Actual: {}", StatusCodes.OK, statusCode);
		Assert.assertEquals(statusCode, StatusCodes.OK);
		LogUtil.debug("Expected: {}, Actual: {}", StatusCodes.USER_CREATED, responseCode);
		Assert.assertEquals(responseCode, StatusCodes.USER_CREATED);
		
		LogUtil.debug("Expected: {}, Actual: {}", ResponseMessages.USER_CREATED, message);
		Assert.assertTrue(message.contains(ResponseMessages.USER_CREATED));
		
		Connection con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.saveUser(con, user);
		SQLWorkbench.closeConnection(con);
		
		LogUtil.info("User created successfully");

	}


}
