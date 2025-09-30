
package api.services;

import java.util.HashMap;
import java.util.Map;

import api.constants.ApiEndpoints;
import api.utils.APITools;
import api.utils.RequestFactory;
import io.restassured.response.Response;

public class ProductsApi {
	
	public static Response getAllProducts() {
		return APITools.get(RequestFactory.getBaseSpec(), ApiEndpoints.PRODUCTS_LIST);
	}
	
	public static Response searchProduct(String product) {
		Map<String, Object> searchForm = new HashMap<>();
		searchForm.put("search_product", product);
		
		return APITools.postForm(RequestFactory.getBaseSpec(), ApiEndpoints.SEARCH_PRODUCT, searchForm);
	}
	
	public static Response getAllBrands() {
		return APITools.get(RequestFactory.getBaseSpec(), ApiEndpoints.BRANDS_LIST);
	}
}
