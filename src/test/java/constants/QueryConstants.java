package constants;

public class QueryConstants {

	private static final String insertUserData = "INSERT INTO users (title, name, email, password, days, months, years, newsletter, optin, first_name, last_name, company, address1, address2, state, city, zipcode, mobile_number) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String getInsertUserData() {
		return insertUserData;
	}
}
