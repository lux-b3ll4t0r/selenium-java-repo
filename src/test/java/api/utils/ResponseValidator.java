package api.utils;

import org.testng.asserts.SoftAssert;

import api.constants.ApiEndpoints;
import api.constants.ResponseCodes;
import api.constants.ResponseMessages;
import api.constants.StatusCodes;
import api.constants.ResponseErrors;
import common.utils.LogUtil;
import io.restassured.response.Response;

public class ResponseValidator {

	public static void verifyUserNotExisting(Response login) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(login.statusCode(), StatusCodes.OK, ApiEndpoints.VERIFY_LOGIN + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(login), ResponseCodes.NOT_FOUND, ApiEndpoints.VERIFY_LOGIN + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(login), ResponseMessages.USER_NOT_FOUND, ApiEndpoints.VERIFY_LOGIN + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user not existing: " + e);
		}
	}
	
	public static void verifyUserExists(Response login) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(login.statusCode(), StatusCodes.OK, ApiEndpoints.VERIFY_LOGIN + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(login), ResponseCodes.OK, ApiEndpoints.VERIFY_LOGIN + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(login), ResponseMessages.USER_EXISTS, ApiEndpoints.VERIFY_LOGIN + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify login: " + e);
		}
	}
	
	public static void verifyUserDetailsExist(Response getUserInfo) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getUserInfo.statusCode(), StatusCodes.OK, ApiEndpoints.GET_USER_DETAIL + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(getUserInfo), ResponseCodes.OK, ApiEndpoints.GET_USER_DETAIL + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user details exist: " + e);
		}
	}
	
	public static void verifyNoUserDetails(Response getUserInfo) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(getUserInfo.statusCode(), StatusCodes.OK, ApiEndpoints.GET_USER_DETAIL + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(getUserInfo), ResponseCodes.NOT_FOUND, ApiEndpoints.GET_USER_DETAIL + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(getUserInfo), ResponseMessages.ACCOUNT_NOT_FOUND_EMAIL, ApiEndpoints.GET_USER_DETAIL + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify no user details: " + e);
		}
	}
	
	public static void verifyUserCreated(Response createNewUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(createNewUser.statusCode(), StatusCodes.OK, ApiEndpoints.CREATE_ACCOUNT + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(createNewUser), ResponseCodes.USER_CREATED, ApiEndpoints.CREATE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(createNewUser), ResponseMessages.USER_CREATED, ApiEndpoints.CREATE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user creation: " + e);
		}
	}
	
	public static void verifyUserCreateRejected(Response createNewUser, String param) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(JsonUtil.getResponseCode(createNewUser), ResponseCodes.BAD_REQUEST, ApiEndpoints.CREATE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(createNewUser), ResponseErrors.parameterMissingInRequest(param, "POST"), ApiEndpoints.CREATE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify create user rejection: " + e);
		}
	}
	
	public static void verifyUserDeleted(Response deleteUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(deleteUser.statusCode(), StatusCodes.OK, ApiEndpoints.DELETE_ACCOUNT + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(deleteUser), ResponseCodes.OK, ApiEndpoints.DELETE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(deleteUser), ResponseMessages.ACCOUNT_DELETED, ApiEndpoints.DELETE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user deleted: " + e);
		}
	}
	
	public static void verifyUserDeleteRejected(Response deleteUser, String param) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(JsonUtil.getResponseCode(deleteUser), ResponseCodes.BAD_REQUEST, ApiEndpoints.DELETE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(deleteUser), ResponseErrors.parameterMissingInRequest(param, "DELETE"), ApiEndpoints.DELETE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify delete user rejection: " + e);
		}
	}
	
	public static void verifyUserDeleteAccountNotFound(Response deleteUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(JsonUtil.getResponseCode(deleteUser), ResponseCodes.NOT_FOUND, ApiEndpoints.DELETE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(deleteUser), ResponseMessages.ACCOUNT_NOT_FOUND, ApiEndpoints.DELETE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify account not found: " + e);
		}
	}
	
	public static void verifyUserUpdated(Response updateUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(updateUser.statusCode(), StatusCodes.OK, ApiEndpoints.UPDATE_ACCOUNT + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(updateUser), ResponseCodes.OK, ApiEndpoints.UPDATE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(updateUser), ResponseMessages.USER_UPDATED, ApiEndpoints.UPDATE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify user updated: " + e);
		}
	}
	
	public static void verifyUserUpdateRejected(Response updateUser, String param) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(JsonUtil.getResponseCode(updateUser), ResponseCodes.BAD_REQUEST, ApiEndpoints.UPDATE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(updateUser), ResponseErrors.parameterMissingInRequest(param, "PUT"), ApiEndpoints.UPDATE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify update user rejection: " + e);
		}
	}
	
	public static void verifyUserUpdateAccountNotFound(Response updateUser) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(JsonUtil.getResponseCode(updateUser), ResponseCodes.NOT_FOUND, ApiEndpoints.UPDATE_ACCOUNT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseMessage(updateUser), ResponseMessages.ACCOUNT_NOT_FOUND, ApiEndpoints.UPDATE_ACCOUNT + ResponseErrors.MESSAGE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify account not found: " + e);
		}
	}
	
	/**
	 * Products Validations
	 */
	
	public static void verifyProductsListStatus(Response productsList) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(productsList.statusCode(), StatusCodes.OK, ApiEndpoints.PRODUCTS_LIST + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(productsList), ResponseCodes.OK, ApiEndpoints.PRODUCTS_LIST + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify products list status: " + e);
		}
	}
	
	public static void verifyBrandsListStatus(Response brandsList) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(brandsList.statusCode(), StatusCodes.OK, ApiEndpoints.BRANDS_LIST + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(brandsList), ResponseCodes.OK, ApiEndpoints.BRANDS_LIST + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify brands list status: " + e);
		}
	}
	
	public static void verifySearchListStatus(Response searchList) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(searchList.statusCode(), StatusCodes.OK, ApiEndpoints.SEARCH_PRODUCT + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertEquals(JsonUtil.getResponseCode(searchList), ResponseCodes.OK, ApiEndpoints.SEARCH_PRODUCT + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify search list status: " + e);
		}
	}
	
	public static void verifyMethodNotAllowed(Response response) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(response.statusCode(), StatusCodes.METHOD_NOT_ALLOWED, "Response" + ResponseErrors.STATUS_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify method not allowed: " + e);
		}
	}
	
	public static void verifyMethodNotAllowedWithResponseCode(Response response) {
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(JsonUtil.getResponseCode(response), ResponseCodes.METHOD_NOT_SUPPORTED, "Response" + ResponseErrors.RESPONSE_CODE_MISMATCH);
			softAssert.assertAll();
		}catch(Exception e) {
			LogUtil.warn("Failed to verify method not allowed: " + e);
		}
	}
}
