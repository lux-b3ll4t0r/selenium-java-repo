package api.tests.account;

import java.sql.Connection;

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
import db.utils.SQLWorkbench;
import io.restassured.response.Response;
import ui.constants.UrlConstants;

public class CreateRegisterAccountTest extends APIBaseTest{
	
	@Test (groups = {"smoke"})
	public void createRegisterAccount() {
		LogUtil.info("Verifying successful account creation/registration to \"" + UrlConstants.HOMEPAGE + ApiEndpoints.CREATE_ACCOUNT+ "\".");
		
		User user = UserDataGenerator.randomUser();
		Response response = AccountApi.createNewUser(user);
		
		// Extracting response message and api response code
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		String responseCode = JsonUtil.getStringValue(response, JsonPaths.RESPONSE_CODE);
		int statusCode = response.statusCode();
		
		// Validating both HTTP code and Application level status code
	
		Assert.assertEquals(statusCode, StatusCodes.OK);

		Assert.assertEquals(responseCode, StatusCodes.USER_CREATED);
		

		Assert.assertTrue(message.contains(ResponseMessages.USER_CREATED));
		
		Connection con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.saveUser(con, user);
		SQLWorkbench.closeConnection(con);
		
		LogUtil.info("User created successfully");

	}


}
