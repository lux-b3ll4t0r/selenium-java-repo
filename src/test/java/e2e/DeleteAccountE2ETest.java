package e2e;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.constants.ApiEndpoints;
import api.services.AccountApi;
import api.utils.ResponseValidator;
import common.pojos.User;
import common.utils.LogUtil;
import common.utils.UserDataGenerator;
import ui.constants.UrlConstants;
import ui.pages.delete.DeleteAccount;
import ui.pages.homepage.Homepage;
import ui.pages.signup_login.SignUpLogin;
import ui.utils.Webtool;

public class DeleteAccountE2ETest extends E2EBaseTest{

	private Homepage homepage;
	private SignUpLogin signUpLogin;
	private DeleteAccount deleteAccount;
	
	private List<User> createdUsers = new ArrayList<>();
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		signUpLogin = new SignUpLogin();
		homepage = new Homepage();	
		deleteAccount = new DeleteAccount();
	}
	
	
	@Test (groups = {"E2E"}, priority = 0)
	public void delete_account_e2e_test() {
		LogUtil.info("Verifying deleting account through UI persists in API.");
		
		LogUtil.info("Creating temporary user to delete.");
		User tempUser = UserDataGenerator.randomUser();
		ResponseValidator.verifyUserCreated(AccountApi.createNewUser(tempUser));
			
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
		
		LogUtil.info("Navigating to login.");
		homepage.navigateToLogin();
		
		LogUtil.info("Logging in.");
		signUpLogin.login(tempUser.getEmail(), tempUser.getPassword());
		
		LogUtil.info("Deleting user through UI.");
		homepage.deleteUser();
		Assert.assertTrue(deleteAccount.isDeleteMessageVisible());
		
		LogUtil.info("Deleted message was displayed in UI, verifying user doesn't exist in API: " + ApiEndpoints.VERIFY_LOGIN);
		ResponseValidator.verifyUserNotExisting(AccountApi.login(tempUser.getEmail(), tempUser.getPassword()));
	
		LogUtil.info("Account was deleted successfully through UI and persisted to API.");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void cleanUpUsers() {
		LogUtil.info("Cleaning up users.");
		AccountApi.cleanUpUsers(createdUsers);
	}
}
