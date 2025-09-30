package api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import ui.constants.UrlConstants;

public class RequestFactory {
	
	public static RequestSpecification getBaseSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(UrlConstants.HOMEPAGE)
				.setAccept("application/json")
				.build();
	}
}
