package api.utils;

import java.util.List;
import java.util.Map;

import api.pojos.APIProduct;
import api.services.ProductsApi;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class APITools {	
	
	public static void main(String[] args) {
		
		Response response = ProductsApi.getAllProducts();
		List<APIProduct> list = JsonUtil.getListAsObject(response, "products", APIProduct.class);
		System.out.println(list);
	}
	
	public static Response sendRequest(RequestSpecification requestSpec, String method, String endpoint) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.when()
				.request(method, endpoint);
	}
	
	public static Response get(RequestSpecification requestSpec, String endpoint) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.when()
				.get(endpoint);
	}
	
	public static Response getQuery(RequestSpecification requestSpec, String endpoint, String key, String value) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.queryParam(key, value)
				.when()
				.get(endpoint);
	}
	
	public static Response getQuery(RequestSpecification requestSpec, String endpoint, Map<String, Object> params) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.queryParams(params)
				.when()
				.get(endpoint);
	}
	
	public static Response post(RequestSpecification requestSpec, String endpoint, Object body) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.contentType("application/json")
				.body(body)
				.when()
				.post(endpoint);
	
	}
	
	public static Response post(RequestSpecification requestSpec, String endpoint) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.contentType("application/json")
				.when()
				.post(endpoint);
	}
		
	public static Response postForm(RequestSpecification requestSpec, String endpoint, Map<String, Object> form) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.contentType("application/x-www-form-urlencoded")
				.formParams(form)
				.when()
				.post(endpoint);

	}
	
	public static Response put(RequestSpecification requestSpec, String endpoint, Object body) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.contentType("application/json")
				.body(body)
				.when()
				.put(endpoint);
	}
	
	public static Response putForm(RequestSpecification requestSpec, String endpoint, Map<String, Object> form) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.contentType("application/x-www-form-urlencoded")
				.formParams(form)
				.when()
				.put(endpoint);
	}
	
	public static Response delete(RequestSpecification requestSpec, String endpoint) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.when()
				.delete(endpoint);
	}
	
	public static Response deleteForm(RequestSpecification requestSpec, String endpoint, Map<String, Object> form) {
		return RestAssured
				.given()
				.spec(requestSpec)
				.contentType("application/x-www-form-urlencoded")
				.formParams(form)
				.when()
				.delete(endpoint);
	}
	
//	public Response createNewRandomUser() {
//		
//		User user = UserDataGenerator.randomUser();
//		Map<String, Object> userData = UserDataGenerator.getApiMap(user);
//		Workbook workbook = null;
//		
//		Response response = post(ApiEndpoints.createAccount, userData);
//		
//		if(response.getStatusCode() == StatusCodes.OK && JsonUtil.getStringValue(response, "responseCode").equals(StatusCodes.USER_CREATED)) {
//			try {
//				workbook = ExcelUtils.setUpWorkbook();
//				Sheet sheet = workbook.getSheet(ConfigManager.getSheet());
//				ExcelUtils.updateByColumnHeader(sheet, "email", user.getEmail());
//				ExcelUtils.updateByColumnHeader(sheet, "password", user.getPassword());
//				ExcelUtils.updateByColumnHeader(sheet, "active", "true");
//	
//			}catch (Exception e) {
//				
//			}finally {
//				
//				if(workbook != null) {
//					ExcelUtils.writeToWorkbook(workbook);
//					ExcelUtils.close(workbook);
//					workbook = null;
//				}
//			}
//		}
//		return response;
//	}
}
