package api.services;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import api.constants.ApiEndpoints;
import api.utils.APITools;
import api.utils.RequestFactory;
import common.pojos.User;
import common.pojos.UserDataGenerator;
import db.utils.SQLWorkbench;
import io.restassured.response.Response;

public class AccountApi{	
	
	public static Response createNewUser(User user) {
		Map<String, Object> userData = UserDataGenerator.getApiMap(user);
		return APITools.postForm(RequestFactory.getBaseSpec(), ApiEndpoints.CREATE_ACCOUNT, userData);
	}
	
	public static Response login(String email, String password) {
		Map<String, Object> loginInfo = new HashMap<>();
		loginInfo.put("email", email);
		loginInfo.put("password", password);
		
		return APITools.postForm(RequestFactory.getBaseSpec(), ApiEndpoints.VERIFY_LOGIN, loginInfo);
	}
	
	public static Response deleteUser(User user) {
		Map<String, Object> deleteForm = new HashMap<>();
		deleteForm.put("email", user.getEmail());
		deleteForm.put("password", user.getPassword());
		
		return APITools.deleteForm(RequestFactory.getBaseSpec(), ApiEndpoints.DELETE_ACCOUNT, deleteForm);
	}
	
	public static Response updateUser(User user) {
		Map<String, Object> userMap = UserDataGenerator.getApiMap(user);	
		return APITools.putForm(RequestFactory.getBaseSpec(), ApiEndpoints.UPDATE_ACCOUNT, userMap);
	}
	
	public static Response getUserInfo(String email) {
		return APITools.getQuery(RequestFactory.getBaseSpec(), ApiEndpoints.GET_USER_DETAIL, "email", email);
	}
	
	public static User getUser(String email, String password) {
		Connection con = SQLWorkbench.connectToLocalDb();
		Map<String, Object> userData = SQLWorkbench.getUser(con, email, password);
		SQLWorkbench.closeConnection(con);
		
		if(!userData.isEmpty()) {
		
		return new User.Builder()
				.name(String.valueOf(userData.get("name")))
				.email(String.valueOf(userData.get("email")))
				.password(String.valueOf(userData.get("password")))
				.title(String.valueOf(userData.get("title")))
				.birth_day(String.valueOf(userData.get("birth_day")))
				.birth_month(String.valueOf(userData.get("birth_month")))
				.birth_year(String.valueOf(userData.get("birth_year")))
				.first_name(String.valueOf(userData.get("first_name")))
				.last_name(String.valueOf(userData.get("last_name")))
				.company(String.valueOf(userData.get("company")))
				.address1(String.valueOf(userData.get("address1")))
				.address2(String.valueOf(userData.get("address2")))
				.country(String.valueOf(userData.get("country")))
				.state(String.valueOf(userData.get("state")))
				.city(String.valueOf(userData.get("city")))
				.zipcode(String.valueOf(userData.get("zipcode")))
				.mobile_number(String.valueOf(userData.get("mobile_number")))
				.build();
		
		}else {
			throw new IllegalArgumentException("Username or password are incorrect, no user returned.");
		}
	}
}
