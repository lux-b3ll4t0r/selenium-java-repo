package api.constants;

public class ResponseErrors {
	
	public static final String STATUS_MISMATCH = " status did not match expected.";
	public static final String RESPONSE_CODE_MISMATCH = " response code did not match expected.";
	public static final String MESSAGE_MISMATCH = " message did not match expected.";
	
	public static String parameterMissingInRequest(String paramName, String requestMethod) {
		return "Bad request, " + paramName + " parameter is missing in " + requestMethod + " request.";
	}
}
