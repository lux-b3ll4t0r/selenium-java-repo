package utils;

import constants.ui.UrlConstants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestFactory {
	
	public static RequestSpecification getBaseSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(UrlConstants.BASE)
				.setAccept("application/json")
				.build();
	}
}
