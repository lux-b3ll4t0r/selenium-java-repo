package api.tests.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import api.utils.ResponseValidator;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import io.restassured.response.Response;

public class AccountIntegrationTest extends APIBaseTest{
	
	private List<User> createdUsers = new ArrayList<>();
	
	@Test (groups = {"integration"}, priority = 0)
	public void verify_account_persists_after_creation_integration_test() {
		LogUtil.info("Verifying account persistence after creating a new account.");
		
		User user = UserDataGenerator.randomUser();
		String email = user.getEmail();
		
		LogUtil.info("Verifying the current user is not in the system: '" + email + "'." );
		ResponseValidator.verifyUserNotExisting(AccountApi.login(email, user.getPassword()));
		
		LogUtil.info("User wasn't found, sending request to create user.");
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(user));
		createdUsers.add(user);
		
		LogUtil.info("User was created successfully, sending request to verify persistence.");
		ResponseValidator.verifyUserExists(AccountApi.login(email, user.getPassword()));
		
		LogUtil.info("User account was found successfully after account creation.");		
	}
	
	@Test (groups = {"integration"}, priority = 1)
	public void verify_create_account_details_persist_integration_test() {
		LogUtil.info("Verifying create account details persist and are returned when requesting user details.");
		
		User initialUser = UserDataGenerator.randomUser();
		String email = initialUser.getEmail();
		
		LogUtil.info("Verifying the current user is not in the system: '" + email + "'." );
		ResponseValidator.verifyUserNotExisting(AccountApi.login(email, initialUser.getPassword()));
		
		LogUtil.info("User wasn't found, sending request to create user.");
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(initialUser));
		createdUsers.add(initialUser);
		
		LogUtil.info("User created successfully, getting details to compare.");
		Response getUser = AccountApi.getUserInfo(email);
		ResponseValidator.verifyUserDetailsExist(getUser);
		
		User compareUser = JsonUtil.getObject(getUser, "user", User.class);
		Map<String, Object> initialDetails = initialUser.getAsUserDetailsCompareMap();
		Map<String, Object> compareDetails = compareUser.getAsUserDetailsCompareMap();
		
		Assert.assertEquals(initialDetails, compareDetails, "Returned user details did not match initial details.");
		LogUtil.info("Returned user details correctly map to newly created user details.");

	}
	
	@Test (groups = {"integration"}, priority = 2)
	public void verify_account_update_persists_integration_test() {
		LogUtil.info("Verifying account update persists and is reflected when retrieving user details.");
		
		User initialUser = UserDataGenerator.randomUser();
		String email = initialUser.getEmail();
		
		LogUtil.info("Verifying the current user is not in the system: '" + email + "'." );
		ResponseValidator.verifyUserNotExisting(AccountApi.login(email, initialUser.getPassword()));
		
		LogUtil.info("User wasn't found, sending request to create user.");
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(initialUser));
		createdUsers.add(initialUser);	 
		
		LogUtil.info("User created successfully, updating user with new details.");
		User updateUser = UserDataGenerator.randomUser();
		updateUser.setEmail(initialUser.getEmail());
		updateUser.setPassword(initialUser.getPassword());
		ResponseValidator.verifyUserUpdated(AccountApi.updateUser(updateUser));
		
		LogUtil.info("Account updated successfully, verifying user details do not match initial details after updating.");
		Response detailsResponse = AccountApi.getUserInfo(updateUser.getEmail());
		User responseUser = JsonUtil.getObject(detailsResponse, "user", User.class);
		
		Map<String, Object> initialDetails = initialUser.getAsUserDetailsCompareMap();
		Map<String, Object> updateDetails = updateUser.getAsUserDetailsCompareMap();
		Map<String, Object> responseDetails = responseUser.getAsUserDetailsCompareMap();

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertNotEquals(initialDetails, responseDetails, "Response details matched initial details meaning the user wasn't updated.");
		softAssert.assertEquals(updateDetails, responseDetails, "Response details did not match updated details.");
		softAssert.assertAll();
		LogUtil.info("Returned user details reflect updated details.");
	}	
	
	@Test (groups = {"integration"}, priority = 3)
	public void verify_user_not_existing_after_deletion_integration_test() {
		LogUtil.info("Verifying user doesn't exist after an account is deleted.");
		
		User user = UserDataGenerator.randomUser();
		String email = user.getEmail();
		
		LogUtil.info("Verifying the current user is not in the system: '" + email + "'." );
		ResponseValidator.verifyUserNotExisting(AccountApi.login(email, user.getPassword()));
		
		LogUtil.info("User wasn't found, sending request to create user.");
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(user));
		createdUsers.add(user);
		
		LogUtil.info("User created successfully, verifying user exists before deleting.");
		ResponseValidator.verifyUserExists(AccountApi.login(email, user.getPassword()));
		
		LogUtil.info("User login verification successful, deleting user.");
		ResponseValidator.verifyUserDeleted(AccountApi.deleteUser(user));
		
		LogUtil.info("User was deleted successfully, verifying account is not found.");
		ResponseValidator.verifyUserNotExisting(AccountApi.login(email, user.getPassword()));
		
		LogUtil.info("Login request was rejected and account was not found after deletion.");
	}
	
	@Test (groups = {"integration"}, priority = 4)
	public void verify_no_account_details_after_deletion_integration_test() {
		LogUtil.info("Verifying no user details are returned after a user has been deleted.");
		
		User user = UserDataGenerator.randomUser();
		String email = user.getEmail();
		
		LogUtil.info("Verifying the current user is not in the system: '" + email + "'." );
		ResponseValidator.verifyUserNotExisting(AccountApi.login(email, user.getPassword()));
		
		LogUtil.info("User wasn't found, sending request to create user.");
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(user));
		createdUsers.add(user);
		
		LogUtil.info("User created successfully, verifying user details exist before deleting.");
		ResponseValidator.verifyUserDetailsExist(AccountApi.getUserInfo(email));
		
		LogUtil.info("User details exist, deleting user.");
		ResponseValidator.verifyUserDeleted(AccountApi.deleteUser(user));
		
		LogUtil.info("User deleted successfully, verifying account is not found when requesting details.");
		ResponseValidator.verifyNoUserDetails(AccountApi.getUserInfo(email));
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanUpUsers() {
		LogUtil.info("Cleaning up users.");
		AccountApi.cleanUpUsers(createdUsers);
	}
}
