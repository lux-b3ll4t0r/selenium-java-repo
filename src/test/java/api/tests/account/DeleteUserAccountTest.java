package api.tests.account;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.constants.ApiEndpoints;
import api.constants.ResponseMessages;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.RequestFactory;
import api.utils.ResponseValidator;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUserAccountTest extends APIBaseTest{
	
	private List<User> createdUsers = new ArrayList<>();
	
	@Test (groups = {"smoke"}, priority = 0)
	public void verify_deleting_account_smoke_test() {
		LogUtil.info("Verifying account is deleted successfully after sending a delete request.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		
		LogUtil.info("Sending delete request.");
		Response response = AccountApi.deleteUser(tempUser);
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserDeleted(response);
		LogUtil.info("Account deleted successfully");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 1, dataProvider = "missingFields")
	public void verify_removing_required_fields_negative_test(String key) {
		LogUtil.info("Verifying delete account request is rejected when required fields are not present.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		createdUsers.add(tempUser);
		
		LogUtil.info("Removing field: " + key);
		Map<String, Object> userMap = tempUser.getDeleteRequestMap();
		userMap.remove(key);
	
		LogUtil.info("Sending delete request.");
		Response response = AccountApi.deleteUser(userMap);
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserDeleteRejected(response, key);
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
		LogUtil.info("Verifying account is not found when deleting and credentials are invalid.");

		Map<String, Object> userMap = new HashMap<>();
		userMap.put("email", "thisisafakeemail123@fakeemail.com");
		userMap.put("password", "thisisafakepassword1254");
	
		LogUtil.info("Sending delete request with invalid credentials.");
		Response response = AccountApi.deleteUser(userMap);
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserDeleteAccountNotFound(response);
		LogUtil.info("Account was not found.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 3, dataProvider = "invalidCredentials")
	public void verify_partial_invalid_credentials_negative_test(String key, String value) {
		LogUtil.info("Verifying account is not found when deleting with partial invalid credentials.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		createdUsers.add(tempUser);
		
		Map<String, Object> userMap = tempUser.getDeleteRequestMap();
		userMap.put(key, value);
	
		LogUtil.info("Sending delete request with invalid credential: " + key);
		Response response = AccountApi.deleteUser(userMap);
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserDeleteAccountNotFound(response);
		LogUtil.info("Account was not found.");
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
	public void verify_invalid_request_methods_functional_test(String method) {
		LogUtil.info("Verifying invalid HTTP methods are not allowed.");
		
		User tempUser = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Creating new user to delete: " + tempUser.getEmail());
		AccountApi.createNewUser(tempUser);
		createdUsers.add(tempUser);
		
		Map<String, Object> userMap = tempUser.getDeleteRequestMap();
		
		LogUtil.info("Sending request using: " + method);
		Response response = RestAssured
				.given()
				.spec(RequestFactory.getBaseSpec())
				.contentType("application/x-www-form-urlencoded")
				.formParams(userMap)
				.when()
				.request(method, ApiEndpoints.DELETE_ACCOUNT);
		
		ResponseValidator.verifyMethodNotAllowed(response);
		LogUtil.info(method + ": was not allowed.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"GET"},
			{"POST"},
			{"PUT"},
		};
	}
	
	@AfterClass
	public void cleanUpUsers() {
		LogUtil.info("Cleaning up users.");
		AccountApi.cleanUpUsers(createdUsers);
	}
}
