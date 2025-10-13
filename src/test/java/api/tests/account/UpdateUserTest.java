package api.tests.account;

import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.constants.ApiEndpoints;
import api.constants.ResponseMessages;
import api.constants.StatusCodes;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.RequestFactory;
import api.utils.ResponseValidator;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateUserTest extends APIBaseTest{
	
	private User updateUser;

	@BeforeClass(alwaysRun = true)
	public void setupUpdateUser() {
		LogUtil.info("Creating new user to update.");
		updateUser = UserDataGenerator.randomUser();
		Response createUser = AccountApi.createNewUser(updateUser);
		ResponseValidator.verifyUserCreated(createUser);
	}
	
	@Test (groups = {"smoke"}, priority = 0)
	public void update_user_smoke_test() {
		LogUtil.info("Verifying user account is updated successfully.");
	
		LogUtil.info("Updating name field for user: '" + updateUser.getEmail() + "'.");
		Map<String, Object> userMap = updateUser.getAsMap();
		userMap.put("name", new Faker().name().fullName());
	
		Response response = AccountApi.updateUser(userMap);	
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserUpdated(response);
		LogUtil.info("User updated successfully.");
	}
	
	
	@Test (groups = {"functional"}, priority = 1, dataProvider = "updatedFields")
	public void update_user_by_single_field_functional_test(String key, String value) {
		LogUtil.info("Verifying user account is updated successfully when updating a single field.");
	
		Map<String, Object> userMap = updateUser.getAsMap();
		userMap.put(key, value);
		
		LogUtil.info("Updating field: '" + key + "' with value: '" + value + "' for user: '" + updateUser.getEmail() + "'.");
		
		Response response = AccountApi.updateUser(userMap);	
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserUpdated(response);
		LogUtil.info("User updated successfully.");
	}
	
	@DataProvider(name = "updatedFields")
	public Object[][] updatedFields(){
		return new Object[][] {
			{"name", UserDataGenerator.name()},
			{"title", UserDataGenerator.title()},
			{"birth_day", UserDataGenerator.birthDay()},
			{"birth_month", UserDataGenerator.birthMonth()},
			{"birth_year", UserDataGenerator.birthYear()},
			{"firstname", UserDataGenerator.firstName()},
			{"lastname", UserDataGenerator.lastName()},
			{"company", UserDataGenerator.company()},
			{"address1", UserDataGenerator.address1()},
			{"address2", UserDataGenerator.address2()},
			{"country", UserDataGenerator.country()},
			{"state", UserDataGenerator.state()},
			{"city", UserDataGenerator.city()},
			{"zipcode", UserDataGenerator.zipCode()},
		};
	}
	
	@Test (groups = {"functional"}, priority = 2)
	public void update_user_by_multiple_fields_functional_test() {
		LogUtil.info("Verifying user account is updated successfully when updating multiple fields.");
	
		LogUtil.info("Updating multiple fields for user: '" + updateUser.getEmail() + "'.");
		
		Map<String, Object> userMap = updateUser.getAsMap();
		userMap.put("name", new Faker().name().fullName());
		userMap.put("address1", new Faker().address().streetAddress());
		userMap.put("address2", new Faker().address().secondaryAddress());
		userMap.put("state", new Faker().address().state());
	
		Response response = AccountApi.updateUser(userMap);	
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserUpdated(response);
		LogUtil.info("User updated successfully.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 3, dataProvider = "emailPassFields")
	public void update_user_without_required_fields_negative_test(String field) {
		LogUtil.info("Verifying user account request is reject when required fields aren't present.");
		
		LogUtil.info("Updating field '" + field + "' from user: '" + updateUser.getEmail() + "'.");
		
		Map<String, Object> userMap = updateUser.getAsMap();
		userMap.remove(field);
		
		Response response = AccountApi.updateUser(userMap);	
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserUpdateRejected(response, field);
		LogUtil.info("Update user request rejected successfully.");
	}
	
	@DataProvider(name = "emailPassFields")
	public Object[][] emailPassFields(){
		return new Object[][] {
			{"email"},
			{"password"},
		};
	}
	
	@Test (groups = {"functional", "negative"}, priority = 4, dataProvider = "invalidCredentials")
	public void update_user_with_invalid_credentials_negative_test(String field, String value, String verification) {
		LogUtil.info("Verifying user account is not found when credentials are invalid.");
	
		LogUtil.info("Updating field '" + field + "' to verify '" + verification + "'");
		
		Map<String, Object> userMap = updateUser.getAsMap();
		userMap.replace(field, value);
		
		Response response = AccountApi.updateUser(userMap);	
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserUpdateAccountNotFound(response);
		LogUtil.info("Account was not found.");
	}
	
	@DataProvider(name = "invalidCredentials")
	public Object[][] invalidCredentials(){
		return new Object[][] {
			{"email", "thisisaninvalidemail@gmail.com", "Invalid Email"},
			{"email", updateUser.getEmail().substring(0, updateUser.getEmail().indexOf("@")), "Partial Valid Email"},
			{"email", "", "Empty Email"},
			{"password", "thisisaninvalidemail", "Invalid Password"},
			{"password", "", "Empty Password"},
		};
	}
	
	@Test (groups = {"functional", "negative"}, priority = 5, dataProvider = "invalidMethods")
	public void verify_invalid_request_method_negative_test(String method) {
		LogUtil.info("Verifying invalid HTTP methods are not allowed.");
		
		Map<String, Object> userMap = updateUser.getAsMap();
		 
		LogUtil.info("Sending request using: " + method);
		Response response = RestAssured
							.given()
							.spec(RequestFactory.getBaseSpec())
							.formParams(userMap)
							.when()
							.request(method, ApiEndpoints.UPDATE_ACCOUNT);
											
		ResponseValidator.verifyMethodNotAllowed(response);
		LogUtil.info(method + ": was not allowed.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"GET"},
			{"POST"},
			{"DELETE"},
		};
	}
	
	@AfterClass
	public void deleteUser() {
		LogUtil.info("Deleting user.");
		if(updateUser != null) {
			if(AccountApi.deleteUser(updateUser).statusCode() != StatusCodes.OK) {
				LogUtil.warn("User '" + updateUser.getEmail() + "' was not deleted successfully.");
			}
		}
	}
}
