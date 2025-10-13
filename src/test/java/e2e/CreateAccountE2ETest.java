package e2e;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.constants.ApiEndpoints;
import api.services.AccountApi;
import api.utils.JsonUtil;
import api.utils.ResponseValidator;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import ui.constants.UrlConstants;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.AccountCreated;
import ui.pages.signup_login.SignUpAccountInfo;
import ui.pages.signup_login.SignUpLogin;
import ui.utils.Webtool;

public class CreateAccountE2ETest extends E2EBaseTest{
	
	private SignUpLogin signUpLogin;
	private SignUpAccountInfo signUpAccInfo;
	private AccountCreated accountCreated;
	private Homepage homepage;
	private List<User> createdUsers = new ArrayList<>();
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		signUpLogin = new SignUpLogin();
		signUpAccInfo = new SignUpAccountInfo();
		accountCreated = new AccountCreated();
		homepage = new Homepage();	
	}
	
	
	@Test (groups = {"E2E"}, priority = 0)
	public void create_account_e2e_test() {
		LogUtil.info("Verifying account created through the UI is received by the API.");
		
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
		
		LogUtil.info("Navigating to: " + UrlConstants.SIGNUP);
		homepage.navigateToSignup();
		
		LogUtil.info("Signing up new user.");
		User uiUser = UserDataGenerator.randomUser();
		signUpLogin.signUpNewUserWithRetry(uiUser.getName(), uiUser.getEmail());
		
		LogUtil.info("Entering and submitting new user account information.");
		signUpAccInfo.enterAndSubmitAccInfo(uiUser);
		
		Assert.assertTrue(accountCreated.isAccountCreatedMessageVisible(), "Creation success message was not visible.");
		LogUtil.info("Account created successfully.");
		createdUsers.add(uiUser);
		
		LogUtil.info("Verifying user exists in API: " + ApiEndpoints.VERIFY_LOGIN);
		ResponseValidator.verifyUserExists(AccountApi.login(uiUser.getEmail(), uiUser.getPassword()));
		
		LogUtil.info("Verifying user details exist in API: " + ApiEndpoints.GET_USER_DETAIL);
		ResponseValidator.verifyUserDetailsExist(AccountApi.getUserInfo(uiUser.getEmail()));
		
		LogUtil.info("Comparing UI user's details from user details returned from: " + ApiEndpoints.GET_USER_DETAIL);
		User apiUser = JsonUtil.getObject(AccountApi.getUserInfo(uiUser.getEmail()), "user", User.class);
		Map<String, Object> uiUserMap = uiUser.getAsUserDetailsCompareMap();
		// This conversion is done due to API mismatch between UI
		String monthName = String.valueOf(uiUserMap.get("birth_month")).toUpperCase();
		String monthNumber = String.valueOf(Month.valueOf(monthName).getValue());
		uiUserMap.put("birth_month", monthNumber);
	
		Map<String, Object> apiUserMap = apiUser.getAsUserDetailsCompareMap();
		Assert.assertEquals(uiUserMap, apiUserMap, "UI details do not match API details.");
		
		LogUtil.info("Account created in UI was successfully persisted to API.");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanUpUsers() {
		LogUtil.info("Cleaning up users.");
		AccountApi.cleanUpUsers(createdUsers);
	}
}
