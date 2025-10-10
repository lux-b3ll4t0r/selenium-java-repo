package api.utils;

import org.testng.asserts.SoftAssert;

import api.constants.ApiEndpoints;
import api.constants.JsonPaths;
import api.constants.ResponseCodes;
import api.constants.ResponseMessages;
import api.constants.StatusCodes;
import api.constants.ValidationMessages;
import common.utils.LogUtil;
import io.restassured.response.Response;

public class ResponseValidator {

	public static void verifyUserNotExisting(Response login) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(login.statusCode(), StatusCodes.OK, ApiEndpoints.VERIFY_LOGIN + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(login, JsonPaths.RESPONSE_CODE), ResponseCodes.NOT_FOUND, ApiEndpoints.VERIFY_LOGIN + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getStringValue(login, JsonPaths.MESSAGE), ResponseMessages.USER_NOT_FOUND, ApiEndpoints.VERIFY_LOGIN + ValidationMessages.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user not existing: " + e);
		}
	}
	
	public static void verifyUserExists(Response login) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(login.statusCode(), StatusCodes.OK, ApiEndpoints.VERIFY_LOGIN + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(login, JsonPaths.RESPONSE_CODE), ResponseCodes.OK, ApiEndpoints.VERIFY_LOGIN + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getStringValue(login, JsonPaths.MESSAGE), ResponseMessages.USER_EXISTS, ApiEndpoints.VERIFY_LOGIN + ValidationMessages.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify login: " + e);
		}
	}
	
	public static void verifyUserDetailsExist(Response getUserInfo) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getUserInfo.statusCode(), StatusCodes.OK, ApiEndpoints.GET_USER_DETAIL + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(getUserInfo, JsonPaths.RESPONSE_CODE), ResponseCodes.OK, ApiEndpoints.GET_USER_DETAIL + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user details exist: " + e);
		}
	}
	
	public static void verifyNoUserDetails(Response getUserInfo) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getUserInfo.statusCode(), StatusCodes.OK, ApiEndpoints.GET_USER_DETAIL + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(getUserInfo, JsonPaths.RESPONSE_CODE), ResponseCodes.NOT_FOUND, ApiEndpoints.GET_USER_DETAIL + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getStringValue(getUserInfo, JsonPaths.MESSAGE), ResponseMessages.ACCOUNT_NOT_FOUND_EMAIL, ApiEndpoints.GET_USER_DETAIL + ValidationMessages.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify no user details: " + e);
		}
	}
	
	public static void verifyUserCreated(Response createNewUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(createNewUser.statusCode(), StatusCodes.OK, ApiEndpoints.CREATE_ACCOUNT + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(createNewUser, JsonPaths.RESPONSE_CODE), ResponseCodes.USER_CREATED, ApiEndpoints.CREATE_ACCOUNT + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getStringValue(createNewUser, JsonPaths.MESSAGE), ResponseMessages.USER_CREATED, ApiEndpoints.CREATE_ACCOUNT + ValidationMessages.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user creation: " + e);
		}
	}
	
	public static void verifyUserDeletion(Response deleteUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(deleteUser.statusCode(), StatusCodes.OK, ApiEndpoints.DELETE_ACCOUNT + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(deleteUser, JsonPaths.RESPONSE_CODE), ResponseCodes.OK, ApiEndpoints.DELETE_ACCOUNT + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getStringValue(deleteUser, JsonPaths.MESSAGE), ResponseMessages.ACCOUNT_DELETED, ApiEndpoints.DELETE_ACCOUNT + ValidationMessages.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user deletion: " + e);
		}
	}
	
	public static void verifyUserUpdated(Response updateUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(updateUser.statusCode(), StatusCodes.OK, ApiEndpoints.UPDATE_ACCOUNT + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(updateUser, JsonPaths.RESPONSE_CODE), ResponseCodes.OK, ApiEndpoints.UPDATE_ACCOUNT + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getStringValue(updateUser, JsonPaths.MESSAGE), ResponseMessages.USER_UPDATED, ApiEndpoints.UPDATE_ACCOUNT + ValidationMessages.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user updated: " + e);
		}
	}
	
	/**
	 * Products Validations
	 */
	
	public static void verifyProductsListStatus(Response productsList) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(productsList.statusCode(), StatusCodes.OK, ApiEndpoints.PRODUCTS_LIST + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(productsList, JsonPaths.RESPONSE_CODE), ResponseCodes.OK, ApiEndpoints.PRODUCTS_LIST + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify products list status: " + e);
		}
	}
	
	public static void verifyBrandsListStatus(Response brandsList) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(brandsList.statusCode(), StatusCodes.OK, ApiEndpoints.BRANDS_LIST + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(brandsList, JsonPaths.RESPONSE_CODE), ResponseCodes.OK, ApiEndpoints.BRANDS_LIST + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify brands list status: " + e);
		}
	}
	
	public static void verifySearchListStatus(Response searchList) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(searchList.statusCode(), StatusCodes.OK, ApiEndpoints.SEARCH_PRODUCT + ValidationMessages.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getIntValue(searchList, JsonPaths.RESPONSE_CODE), ResponseCodes.OK, ApiEndpoints.SEARCH_PRODUCT + ValidationMessages.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify search list status: " + e);
		}
	}
}
