package api.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.restassured.response.Response;

public class JsonUtil {
	
	public static String getStringValue(Response response, String key) {
		return response.then().extract().jsonPath().getString(key);
	}
	
	public static List<String> getList(Response response, String key){
		return response.then().extract().jsonPath().getList(key);
	}
	
    public static void prettyPrintJsonResponse(Response response) {
        String rawJson = response.getBody().asString();

        ObjectMapper mapper = new ObjectMapper();
        Object json;

        try {
            // Parse raw JSON string into Object (can be Map or List depending on JSON structure)
            json = mapper.readValue(rawJson, Object.class);

            // Use ObjectWriter with prettyPrint enabled
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

            // Convert JSON Object back to formatted JSON String
            String prettyJson = writer.writeValueAsString(json);

            System.out.println(prettyJson);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to parse JSON");
        }
    }
}
