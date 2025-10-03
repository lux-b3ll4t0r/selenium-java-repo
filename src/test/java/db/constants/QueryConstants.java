package db.constants;

public class QueryConstants {

	public static final String INSERT_USER = "INSERT INTO users (title, name, email, password, birth_date, birth_month, birth_year, newsletter, optin, firstname, lastname, company, address1, address2, country, state, city, zipcode, mobile_number, creation_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String DELETE_USER = "DELETE FROM users WHERE email = ? AND password = ?";
	public static final String GET_LAST_USER = "select (name, email, title, birth_date, birth_month, birth_year, firstname, lastname, company, address1, address2, country, state, city, zipcode) from users order by id desc limit 1";
	public static final String GET_USER = "SELECT name, email, password, title, birth_date, birth_month, birth_year, firstname, lastname, company, address1, address2, country, state, city, zipcode, mobile_number FROM users WHERE email = ? AND password = ?";	
	public static final String GET_MOBILE = "SELECT mobile_number FROM users WHERE email = ?";
	
	
}
