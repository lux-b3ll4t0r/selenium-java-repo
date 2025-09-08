package utils;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import com.github.javafaker.Faker;

public class UserDataGenerator {
	
	public static void main(String[] args) {
		
//		User user = UserDataGenerator.randomUser();
//		//Map<String, Object> test = UserDataGenerator.getAsMap(user);
//		
//		for (Entry<String, Object> entry : test.entrySet()) {
//		    String key = entry.getKey();
//		    String value = String.valueOf(entry.getValue());
//		    System.out.println(key + " = " + value);
//		}
	}
	
	private static final Faker faker = new Faker();
	
	
	public static User randomUser() {
	
		return new User.Builder()
				.name(getName())
				.email(getEmail())
				.password(getPassword())
				.title(getTitle())
				.birth_day(getBirthDay())
				.birth_month(getBirthMonth())
				.birth_year(getBirthYear())
				.newsletter(getNewsletter())
				.optin(getOptin())
				.first_name()
				.last_name()
				.company(getCompany())
				.address1(getAddress1())
				.address2(getAddress2())
				.country(getCountry())
				.state(getState())
				.city(getCity())
				.zipcode(getZipCode())
				.mobile_number(getMobileNumber())
				.build();	
		
	}
	
//	public static Map<String, Object> getAsMap(User user){
//		
//		Map<String, Object> userMap = new HashMap<>();
//		
//		userMap.put("name", user.getName());
//		userMap.put("email", user.getEmail());
//		userMap.put("password", user.getPassword());
//		userMap.put("title", user.getTitle());
//		userMap.put("birth_day", user.getBirthDay());
//		userMap.put("birth_month", user.getBirthMonth());
//		userMap.put("birth_year", user.getBirthYear());
//		userMap.put("newsletter", user.getNewsLetter());
//		userMap.put("optin", user.getOptin());
//		userMap.put("firstname", user.getFirstName());
//		userMap.put("lastname", user.getLastName());
//		userMap.put("company", user.getCompany());
//		userMap.put("address1", user.getAddress1());
//		userMap.put("address2", user.getAddress2());
//		userMap.put("country", user.getCountry());
//		userMap.put("state", user.getState());
//		userMap.put("city", user.getCity());
//		userMap.put("zipcode", user.getZipcode());
//		userMap.put("mobile_number", user.getMobileNumber());
//		
//		return userMap;
//	}
	
public static Map<String, Object> getApiMap(User user){
		
		Map<String, Object> userMap = new HashMap<>();
		
		userMap.put("name", user.getName());
		userMap.put("email", user.getEmail());
		userMap.put("password", user.getPassword());
		userMap.put("title", user.getTitle());
		userMap.put("birth_day", user.getBirthDay());
		userMap.put("birth_month", user.getBirthMonth());
		userMap.put("birth_year", user.getBirthYear());
		userMap.put("firstname", user.getFirstName());
		userMap.put("lastname", user.getLastName());
		userMap.put("company", user.getCompany());
		userMap.put("address1", user.getAddress1());
		userMap.put("address2", user.getAddress2());
		userMap.put("country", user.getCountry());
		userMap.put("state", user.getState());
		userMap.put("city", user.getCity());
		userMap.put("zipcode", user.getZipcode());
		userMap.put("mobile_number", user.getMobileNumber());
		
		return userMap;
	}
	
	
	public static String getName() {
		return faker.name().firstName() + " " + faker.name().lastName();
	}

	
	public static String getEmail() {
		return faker.internet().emailAddress();
	}
	
	public static String getPassword() {
		return faker.internet().password();
	}
	
	public static String getTitle() {
		return MathUtils.getRandomIntWithRange(0, 1) == 0 ? "Mr" : "Mrs";
	}
	
	public static String getBirthDay() {
		return String.valueOf(MathUtils.getRandomIntWithRange(1, 31));
	}
	
	public static String getBirthMonth() {
		
		String temp = Month.of(MathUtils.getRandomIntWithRange(1, 12)).name();
		String month = temp.substring(0,1) + temp.substring(1).toLowerCase();
		return month;
	}
		
	public static String getBirthYear() {
		return String.valueOf(MathUtils.getRandomIntWithRange(1900, 2025));
	}
	
	public static String getNewsletter() {
		return MathUtils.getRandomIntWithRange(0, 1) == 0 ? "false": "true";
	}
	
	public static String getOptin() {
		return MathUtils.getRandomIntWithRange(0, 1) == 0 ? "false": "true";
	}
	
	public static String getCompany() {
		return faker.company().name();
	}
	
	public static String getAddress1() {
		return faker.address().streetAddress();
	}
	
	public static String getAddress2() {
		return faker.address().secondaryAddress();
	}
	
	public static String getCountry() {
		String[] countries = {"India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore"};
		
		return countries[MathUtils.getRandomIntWithRange(0, 6)];
	}
	
	public static String getState() {
		return faker.address().state();
	}
	
	public static String getCity() {
		return faker.address().city();
	}
	
	public static String getZipCode() {
		return faker.numerify("#####");
	}
	
	public static String getMobileNumber() {
		return faker.numerify("#-###-###-####");
	}

}
