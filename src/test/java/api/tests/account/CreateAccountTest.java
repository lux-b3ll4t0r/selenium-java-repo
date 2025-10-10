package api.tests.account;

import java.sql.Connection;
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
import api.utils.APITools;
import api.utils.JsonUtil;
import api.utils.RequestFactory;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import db.utils.SQLWorkbench;
import io.restassured.response.Response;


public class CreateAccountTest extends APIBaseTest{
	
	@Test (groups = {"smoke"}, priority = 0)
	public void verify_successful_account_creation_smoke_test() {
		LogUtil.info("Verifying successful account creation.");
		
		User user = UserDataGenerator.randomUser();
		Response response = AccountApi.createNewUser(user);
		
		int statusCode = response.statusCode();
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		
		
		SoftAssert softAssert = new SoftAssert();
	
		softAssert.assertEquals(statusCode, StatusCodes.OK, "Account creation status code did not match expected.");
		softAssert.assertEquals(responseCode, ResponseCodes.USER_CREATED, "Account creation response coode did not match expected.");
		softAssert.assertEquals(message, ResponseMessages.USER_CREATED, "Account creation response message, did not match expected.");
		softAssert.assertAll();
		
		LogUtil.info("User: " + user.getEmail() + " created successfully.");
		
		Connection con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.saveUser(con, user);
		SQLWorkbench.closeConnection(con);
		
	}
	
	@Test (groups = {"functional"}, priority = 1)
	public void verify_removing_optional_fields_functional_test() {
		LogUtil.info("Verifying an account can be created with optional fields removed.");
		
		LogUtil.info("Sending request to create new user.");
		User user = UserDataGenerator.requiredApiFields();
		Response response = AccountApi.createNewUser(user);
		
		int statusCode = response.statusCode();
		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		LogUtil.info("Response Code: " + responseCode + " - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(statusCode, StatusCodes.OK, "Account creation status code did not match expected.");
		softAssert.assertEquals(responseCode, ResponseCodes.USER_CREATED, "Account creation response code did not match expected.");
		softAssert.assertEquals(message, ResponseMessages.USER_CREATED, "Account creation response message, did not match expected.");
		softAssert.assertAll();
		
		LogUtil.info("User: " + user.getEmail() + " created successfully.");
		
		Connection con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.saveUser(con, user);
		SQLWorkbench.closeConnection(con);
	}
	
	@Test (groups = {"functional", "negative"}, priority = 2, dataProvider = "missingFields")
	public void verify_removing_required_fields_functional_test(String key) {
		LogUtil.info("Verifying a create account request is rejected when required fields are not present.");
		
		User user = UserDataGenerator.requiredApiFields();
		Map<String, Object> userMap = user.getAsMapRequiredOnly();
		userMap.remove(key);
		
		LogUtil.info("Sending request without field: \"" + key + "\"");
		Response response = AccountApi.createNewUser(userMap);

		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		String expectedMessage = "Bad request, " + key + " parameter is missing in POST request.";
		LogUtil.info("Response Code: " + responseCode + " - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.BAD_REQUEST, "Account creation response code did not match expected.");
		softAssert.assertEquals(message, expectedMessage, "Account creation response message, did not match expected.");
		softAssert.assertAll();
		
		LogUtil.info("Account creation request rejected successfully.");
	}
	
	@DataProvider (name = "missingFields")
	public Object[][] missingFields(){
		
		return new Object[][] {
			{"name"},
			{"email"},
			{"password"},
			{"firstname"},
			{"lastname"},
			{"address1"},
			{"country"},
			{"zipcode"},
			{"state"},
			{"city"},
			{"mobile_number"},
			
		};
	}
	
	@Test (groups = {"functional", "negative"}, priority = 3)
	public void verify_removing_all_fields_functional_test() {
		LogUtil.info("Verifying a create account request is rejected when no fields are present.");
		
		LogUtil.info("Sending request to create new user.");
		Response response = APITools.post(RequestFactory.getBaseSpec(), ApiEndpoints.CREATE_ACCOUNT);

		int responseCode = JsonUtil.getIntValue(response, JsonPaths.RESPONSE_CODE);
		String message = JsonUtil.getStringValue(response, JsonPaths.MESSAGE);
		String expectedMessage = "Bad request, name parameter is missing in POST request.";
		LogUtil.info("Response Code: " + responseCode + " - " + "Message: " + message);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseCode, ResponseCodes.BAD_REQUEST, "Account creation response code did not match expected.");
		softAssert.assertEquals(message, expectedMessage, "Account creation response message, did not match expected.");
		softAssert.assertAll();
		
		LogUtil.info("Account creation request rejected successfully.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 4, dataProvider = "invalidMethods")
	public void verify_invalid_request_methods_functional_test(String method, int statusCode) {
		LogUtil.info("Verifying invalid HTTP methods are rejected.");
		
		LogUtil.info("Sending request using: " + method);
		Response response = APITools.sendRequest(RequestFactory.getBaseSpec(), method, ApiEndpoints.CREATE_ACCOUNT);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatusCode(), statusCode);
		softAssert.assertAll();
		LogUtil.info("Method rejected successfully.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"GET", StatusCodes.METHOD_NOT_ALLOWED},
			{"PUT", StatusCodes.METHOD_NOT_ALLOWED},
			{"DELETE", StatusCodes.METHOD_NOT_ALLOWED},
		};
	}
}
