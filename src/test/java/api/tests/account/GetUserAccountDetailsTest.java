package api.tests.account;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.services.AccountApi;
import api.tests.base.APIBaseTest;
import api.utils.JsonUtil;
import api.utils.RequestFactory;
import api.utils.ResponseValidator;
import common.pojos.User;
import common.utils.ConfigManager;
import common.utils.LogUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserAccountDetailsTest extends APIBaseTest{
	 
	@Test (groups = {"smoke"}, priority = 0)
	public void get_user_details_smoke_test() {
		LogUtil.info("Validating account details are returned when entering a valid email");
		
		String email = System.getenv("API_USER");
		
		LogUtil.info("Sending details request with email '" + email + "'.");
		
		ResponseValidator.verifyUserDetailsExist(AccountApi.getUserInfo(email));
		LogUtil.info("User details returned successfully");
	}
	
	@Test (groups = {"functional"}, priority = 1)
	public void verify_user_details_not_null_functional_test() {
		LogUtil.info("Validating account details are not null in response.");
		
		String email = System.getenv("API_USER");
		
		LogUtil.info("Sending details request with email '" + email + "'.");
		Response response = AccountApi.getUserInfo(email);	
		
		User user = JsonUtil.getObject(response, JsonPaths.USER, User.class);
		Map<String, Object> userMap = user.getAsMap();
		
		SoftAssert softAssert = new SoftAssert();
		
		LogUtil.info("Verifying no fields return null.");
		for(Map.Entry<String, Object> entry : userMap.entrySet()) {
			softAssert.assertNotNull(entry);
		}
		 
		softAssert.assertAll();
		LogUtil.info("No user fields returned null.");
	}
	
	@Test (groups = {"smoke"}, priority = 2)
	public void verify_user_details_schema_functional_test() {
		LogUtil.info("Validating account details response schema matches expected.");
		
		String email = System.getenv("API_USER");
		
		LogUtil.info("Sending details request with email '" + email + "'.");
		
		Assert.assertTrue(JsonUtil.bodyMatchesSchema(AccountApi.getUserInfo(email), ConfigManager.getUsetDetailsSchema()), "User details response schema did not match expected.");
		LogUtil.info("User details response schema matches expected.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 3)
	public void verify_invalid_email_negative_test() {
		LogUtil.info("Validating account details are not returned when sending an invalid email.");
		
		String email = "thisisafakeemail12356";
		
		LogUtil.info("Sending details request with email '" + email + "'.");
		ResponseValidator.verifyNoUserDetails(AccountApi.getUserInfo(email));
		
		LogUtil.info("No details returned.");
	}
	
	@Test (groups = {"functional", "negative"}, priority = 4, dataProvider = "invalidMethods")
	public void verify_invalid_request_method_negative_test(String method) {
		LogUtil.info("Verifying invalid HTTP methods are not allowed.");
		
		LogUtil.info("Sending request using: " + method);
		Response response = RestAssured
							.given()
							.spec(RequestFactory.getBaseSpec())
							.queryParam(System.getenv("API_USER"))
							.when()
							.request(method, ApiEndpoints.GET_USER_DETAIL);
											
		ResponseValidator.verifyMethodNotAllowedWithResponseCode(response);
		LogUtil.info(method + ": was not allowed.");
	}
	
	@DataProvider(name = "invalidMethods")
	public Object[][] invalidMethods(){
		return new Object[][] {
			{"POST"},
			{"PUT"},
			{"DELETE"},
		};
	}
}
