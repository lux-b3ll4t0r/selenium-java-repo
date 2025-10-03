package common.utils;

import java.time.Month;

import com.github.javafaker.Faker;

import common.pojos.User;

public class UserDataGenerator {
	
	private static final Faker faker = new Faker();
	
	
	public static User randomUser() {
	
		return new User.Builder()
				.name(name())
				.email(email())
				.password(password())
				.title(title())
				.birth_day(birthDay())
				.birth_month(birthMonth())
				.birth_year(birthYear())
				.newsletter(newsletter())
				.optin(optin())
				.first_name()
				.last_name()
				.company(company())
				.address1(address1())
				.address2(address2())
				.country(country())
				.state(state())
				.city(city())
				.zipcode(zipCode())
				.mobile_number(mobileNumber())
				.build();	
		
	}
	
	
	public static String name() {
		return faker.name().firstName() + " " + faker.name().lastName();
	}

	
	public static String email() {
		return faker.internet().emailAddress();
	}
	
	public static String password() {
		return faker.internet().password();
	}
	
	public static String title() {
		return MathUtils.getRandomIntWithRange(0, 1) == 0 ? "Mr" : "Mrs";
	}
	
	public static String birthDay() {
		return String.valueOf(MathUtils.getRandomIntWithRange(1, 31));
	}
	
	public static String birthMonth() {
		
		String temp = Month.of(MathUtils.getRandomIntWithRange(1, 12)).name();
		String month = temp.substring(0,1) + temp.substring(1).toLowerCase();
		return month;
	}
		
	public static String birthYear() {
		return String.valueOf(MathUtils.getRandomIntWithRange(1900, 2025));
	}
	
	public static String newsletter() {
		return MathUtils.getRandomIntWithRange(0, 1) == 0 ? "false": "true";
	}
	
	public static String optin() {
		return MathUtils.getRandomIntWithRange(0, 1) == 0 ? "false": "true";
	}
	
	public static String company() {
		return faker.company().name();
	}
	
	public static String address1() {
		return faker.address().streetAddress();
	}
	
	public static String address2() {
		return faker.address().secondaryAddress();
	}
	
	public static String country() {
		String[] countries = {"India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore"};
		
		return countries[MathUtils.getRandomIntWithRange(0, 6)];
	}
	
	public static String state() {
		return faker.address().state();
	}
	
	public static String city() {
		return faker.address().city();
	}
	
	public static String zipCode() {
		return faker.numerify("#####");
	}
	
	public static String mobileNumber() {
		return faker.numerify("#-###-###-####");
	}

}
