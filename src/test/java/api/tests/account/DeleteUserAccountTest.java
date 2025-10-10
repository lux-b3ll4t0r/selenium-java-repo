package api.tests.account;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.constants.ResponseCodes;
import api.constants.ResponseMessages;
import api.constants.StatusCodes;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import api.utils.RequestFactory;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUserAccountTest extends APIBaseTest{
	
	@Test (groups = {"smoke"}, priority = 0)
	public void verify_deleting_account_smoke_test() {
		LogUtil.info("Verifying account is deleted successfully after sending a delete request.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		
		LogUtil.info("Sending delete request.");
		Response response = AccountApi.deleteUser(tempUser);
		
		int statusCode = response.getStatusCode();
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + " - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(statusCode, StatusCodes.OK);
		softAssert.assertEquals(responseCode, ResponseCodes.OK);
		softAssert.assertEquals(message, ResponseMessages.ACCOUNT_DELETED);
		softAssert.assertAll();

		LogUtil.info("Account deleted successfully");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 1, dataProvider = "missingFields")
	public void verify_removing_required_fields_negative_test(String key) {
		LogUtil.info("Verifying delete account request is rejected when required fields are not present.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		
		LogUtil.info("Removing field: " + key);
		Map<String, Object> userMap = tempUser.getDeleteRequestMap();
		userMap.remove(key);
	
		LogUtil.info("Sending delete request.");
		Response response = AccountApi.deleteUser(userMap);
		
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		String expectedMessage = "Bad request, " + key + " parameter is missing in DELETE request."; 
		LogUtil.info("Response Code: " + responseCode +" - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.BAD_REQUEST);
		softAssert.assertEquals(message, expectedMessage);
		softAssert.assertAll();

		LogUtil.info("Delete request rejected successfully.");
	}
	
	@DataProvider (name = "missingFields")
	public Object[][] missingFields(){
		
		return new Object[][] {
			{"email"},
			{"password"},	
		};
	}
	
	@Test (groups = {"functional", "negative"}, priority = 2)
	public void verify_invalid_credentials_negative_test() {
		LogUtil.info("Verifying delete account request is rejected when credentials are invalid.");

		Map<String, Object> userMap = new HashMap<>();
		userMap.put("email", "thisisafakeemail123@fakeemail.com");
		userMap.put("password", "thisisafakepassword1254");
	
		LogUtil.info("Sending delete request with invalid credentials.");
		Response response = AccountApi.deleteUser(userMap);
		
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + responseCode +" - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.NOT_FOUND);
		softAssert.assertEquals(message, ResponseMessages.ACCOUNT_NOT_FOUND);
		softAssert.assertAll();

		LogUtil.info("Delete request rejected successfully.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 3, dataProvider = "invalidCredentials")
	public void verify_partial_invalid_credentials_negative_test(String key, String value) {
		LogUtil.info("Verifying delete account request is rejected when partial credentials are invalid.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		

		Map<String, Object> userMap = tempUser.getDeleteRequestMap();
		userMap.put(key, value);
	
		LogUtil.info("Sending delete request with invalid credential: " + key);
		Response response = AccountApi.deleteUser(userMap);
		
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + responseCode +" - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.NOT_FOUND);
		softAssert.assertEquals(message, ResponseMessages.ACCOUNT_NOT_FOUND);
		softAssert.assertAll();

		LogUtil.info("Delete request rejected successfully.");
	}
	
	@DataProvider (name = "invalidCredentials")
	public Object[][] invalidCredentials(){
		
		return new Object[][] {
			{"email", "invalidemail@gmail.com"},
			{"email", ""},
			{"password", "invalidpassword123"},	
			{"password", ""},
		};
	}
	
	@Test (groups = {"functional", "negative"}, priority = 4, dataProvider = "invalidMethods")
	public void verify_invalid_request_methods_functional_test(String method, int statusCode) {
		LogUtil.info("Verifying invalid HTTP methods are rejected.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		
		Map<String, Object> userMap = tempUser.getDeleteRequestMap();
		
		LogUtil.info("Sending request using: " + method);
		Response response = RestAssured
				.given()
				.spec(RequestFactory.getBaseSpec())
				.contentType("application/x-www-form-urlencoded")
				.formParams(userMap)
				.when()
				.request(method, ApiEndpoints.DELETE_ACCOUNT);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatusCode(), statusCode);
		softAssert.assertAll();
		LogUtil.info("Method rejected successfully.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"GET", StatusCodes.METHOD_NOT_ALLOWED},
			{"POST", StatusCodes.METHOD_NOT_ALLOWED},
			{"PUT", StatusCodes.METHOD_NOT_ALLOWED},
		};
	}
}
