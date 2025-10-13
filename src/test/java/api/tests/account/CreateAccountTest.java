package api.tests.account;

import java.sql.Connection;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.constants.ApiEndpoints;
import api.constants.ResponseMessages;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.APITools;
import api.utils.RequestFactory;
import api.utils.ResponseValidator;
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
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserCreated(response);
		LogUtil.info("User created successfully.");
		
		Connection con = SQLWorkbench.connectToLocalDb();
		SQLWorkbench.saveUser(con, user);
		SQLWorkbench.closeConnection(con);
		
	}
	
	@Test (groups = {"functional"}, priority = 1)
	public void verify_removing_optional_fields_functional_test() {
		LogUtil.info("Verifying an account can be created with optional fields removed.");
		
		User user = UserDataGenerator.requiredApiFields();
		
		LogUtil.info("Sending request to create new user without optional fields.");
		Response response = AccountApi.createNewUser(user);
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserCreated(response);
		LogUtil.info("User created successfully.");
		
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
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserCreateRejected(response, key);
		
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
		LogUtil.info(ResponseMessages.apiStatusAndMessage(response));
		
		ResponseValidator.verifyUserCreateRejected(response, "name");
	
		LogUtil.info("Account creation request rejected successfully.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 4, dataProvider = "invalidMethods")
	public void verify_invalid_request_methods_functional_test(String method) {
		LogUtil.info("Verifying invalid HTTP methods are not allowed.");
		
		LogUtil.info("Sending request using: " + method);
		Response response = APITools.sendRequest(RequestFactory.getBaseSpec(), method, ApiEndpoints.CREATE_ACCOUNT);
		
		ResponseValidator.verifyMethodNotAllowed(response);
		LogUtil.info(method + " was not allowed.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"GET"},
			{"PUT"},
			{"DELETE"},
		};
	}
}
