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
import common.pojos.UserDataGenerator;
import common.utils.LogUtil;
import db.utils.SQLWorkbench;
import io.restassured.response.Response;
import ui.constants.UrlConstants;

public class UpdateUserTest extends APIBaseTest{
	
	@Test (groups = {"smoke"})
	public void updateUserTest() {
		LogUtil.info("Verifying successful account update to \"" + UrlConstants.HOMEPAGE + ApiEndpoints.UPDATE_ACCOUNT + "\".");
	
		User user = AccountApi.getUser(System.getenv("API_USER"), System.getenv("API_PASS"));
		
		LogUtil.info("Updating user: " + user.toSafeString());

		String updateName = UserDataGenerator.getName();
		String updateAddress1 = UserDataGenerator.getAddress1();
		user.setName(updateName);
		user.setAddress1(updateAddress1); 
		
		Response response = AccountApi.updateUser(user);	
		int statusCode = response.statusCode();
		String responseCode = JsonUtil.getStringValue(response, "message");
		
		Assert.assertEquals(statusCode, StatusCodes.OK, "Status code did not match expected, was: " + statusCode);
		Assert.assertEquals(responseCode, ResponseMessages.USER_UPDATED, "Message did not match expected, was: " + responseCode);
		
		LogUtil.info("Updated user info: " + user.toSafeString());
		
		Response userInfo = AccountApi.getUserInfo(user.getEmail());
		String updatedName = JsonUtil.getStringValue(userInfo, JsonPaths.NAME);
		String updatedAddress1 = JsonUtil.getStringValue(userInfo, JsonPaths.ADDRESS1);
		Assert.assertEquals(updatedName, updateName, "Response name did not match expected update name, was: " + updatedName);
		Assert.assertEquals(updatedAddress1, updateAddress1, "Response address1 did not match expected update, was: " + updatedAddress1);
		
		Connection con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.updateUser(con, user);
		SQLWorkbench.closeConnection(con);
		
		LogUtil.info("User updated successfully.");
	}
	
}
