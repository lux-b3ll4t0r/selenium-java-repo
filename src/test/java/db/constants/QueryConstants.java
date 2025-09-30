package db.constants;

public class QueryConstants {

	private static final String INSERT_USER = "INSERT INTO users (title, name, email, password, birth_day, birth_month, birth_year, newsletter, optin, first_name, last_name, company, address1, address2, country, state, city, zipcode, mobile_number, creation_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_USER = "DELETE FROM users WHERE email = ? AND password = ?";
	private static final String GET_LAST_USER = "select (name, email, title, birth_day, birth_month, birth_year, first_name, last_name, company, address1, address2, country, state, city, zipcode) from users order by id desc limit 1";
	private static final String GET_USER = "SELECT name, email, password, title, birth_day, birth_month, birth_year, first_name, last_name, company, address1, address2, country, state, city, zipcode, mobile_number FROM users WHERE email = ? AND password = ?";	
	private static final String GET_MOBILE = "SELECT mobile_number FROM users WHERE email = ?";
	
	public static String insertUser() {
		return INSERT_USER;
	}
	
	public static String deleteUser() {
		return DELETE_USER;
	}
	
	public static String getLastUser() {
		return GET_LAST_USER;
	}
	
	public static String getUser() {
		return GET_USER;
	}
	
	public static String getMobile() {
		return GET_MOBILE;
	}
}
