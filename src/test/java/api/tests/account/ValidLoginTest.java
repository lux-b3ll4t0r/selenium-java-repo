package api.tests.account;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.constants.ResponseCodes;
import api.constants.ResponseMessages;
import api.constants.StatusCodes;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.APITools;
import api.utils.JsonUtil;
import api.utils.RequestFactory;
import common.utils.ConfigManager;
import common.utils.LogUtil;
import io.restassured.response.Response;

public class ValidLoginTest extends APIBaseTest{

	@Test (groups = {"smoke"}, priority = 0)
	public void verify_valid_login_smoke_test() {
		LogUtil.info("Verifying successful user login with valid credentials.");
		
		LogUtil.info("Sending login request.");
		Response response = AccountApi.login(System.getenv("API_USER"), System.getenv("API_PASS"));
		
		int statusCode = response.getStatusCode();
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Status Code: " + statusCode + " - " + "Response Code: " + responseCode + " - Message: " + message);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(statusCode, StatusCodes.OK);
		softAssert.assertEquals(responseCode, ResponseCodes.OK);
		softAssert.assertEquals(message, ResponseMessages.USER_EXISTS);
		softAssert.assertAll();
		LogUtil.info("User found successfully");
	}
	
	
	/*
	 * Although the response only returns 2 properties (responseCode, and message)
	 * the schema is checked to validate those are the ONLY properties returned to the user.
	 */
	
	@Test (groups = {"functional"}, priority = 1)
	public void verify_login_schema_functional_test() {
		LogUtil.info("Verifying login response schema matches expected Json schema.");
		
		LogUtil.info("Sending login request.");
		Response response = AccountApi.login(System.getenv("API_USER"), System.getenv("API_PASS"));
		
		Assert.assertTrue(JsonUtil.bodyMatchesSchema(response, ConfigManager.getLoginSchema()));
		LogUtil.info("Login schema matches expected Json schema.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 2)
	public void verify_login_invalid_fields_negative_test() {
		LogUtil.info("Verifying login request is rejected when submitted with invalid credentials.");
		
		LogUtil.info("Sending login request.");
		Response response = AccountApi.login(new Faker().internet().emailAddress(), new Faker().internet().password());
		
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + responseCode + " - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.NOT_FOUND);
		softAssert.assertEquals(message, ResponseMessages.USER_NOT_FOUND);
		softAssert.assertAll();
		LogUtil.info("Login request rejected successfully.");
		
	}
	
	@Test (groups = {"functional", "negative"}, priority = 3, dataProvider = "loginDetails")
	public void verify_login_partial_fields_negative_test(String userKey, String passKey) {
		LogUtil.info("Verifying login request is rejected when submitted with partially valid credentials.");
		
		String user = passKey.isEmpty() ? System.getenv(userKey) : userKey;
		String pass = userKey.isEmpty() ? System.getenv(passKey) : passKey;
		
		LogUtil.info("Sending login request.");	
		Response response = AccountApi.login(user, pass);
		
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + responseCode + " - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.NOT_FOUND);
		softAssert.assertEquals(message, ResponseMessages.USER_NOT_FOUND);
		softAssert.assertAll();
		LogUtil.info("Login request rejected successfully.");
		
	}
	
	@DataProvider(name = "loginDetails")
	public Object[][] loginDetails(){
		return new Object[][] {
			{"API_USER", ""},
			{"", "API_PASS"}
			};	
	}
	
	@Test (groups = {"functional", "negative"}, priority = 4, dataProvider = "loginDetails")
	public void verify_login_missing_fields_negative_test(String userKey, String passKey) {
		LogUtil.info("Verifying login request is rejected when required fields are missing.");
		
		LogUtil.info("Sending login request with missing fields.");
		Response response = APITools.post(RequestFactory.getBaseSpec(), ApiEndpoints.VERIFY_LOGIN);
		
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + responseCode + " - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.BAD_REQUEST);
		softAssert.assertEquals(message, ResponseMessages.EMAIL_PASS_MISSING);
		softAssert.assertAll();
		LogUtil.info("Login request rejected successfully.");
		
	}
	
	@Test (groups = {"functional", "negative"}, priority = 5, dataProvider = "invalidMethods")
	public void verify_invalid_request_method_negative_test(String method, int responseCode, String responseMessage) {
		LogUtil.info("Verifying invalid HTTP methods are rejected.");
		
		LogUtil.info("Sending request using: " + method);
		Response response = APITools.sendRequest(RequestFactory.getBaseSpec(), method, ApiEndpoints.VERIFY_LOGIN);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE), responseCode);
		softAssert.assertEquals(JsonUtil.getStringValue(response, JsonPaths.MESSAGE), responseMessage);
		softAssert.assertAll();
		LogUtil.info("Method rejected successfully.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"GET", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
			{"PUT", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
			{"DELETE", ResponseCodes.METHOD_NOT_SUPPORTED, ResponseMessages.METHOD_NOT_SUPPORTED},
		};
	}
}
