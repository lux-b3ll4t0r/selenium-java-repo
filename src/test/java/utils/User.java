package utils;

import com.github.javafaker.Faker;

public class User {
		
	private String title, firstName, lastName, email, password, company, 
	address1, address2, country, state, city, mobileNumber;
	private String[] countries = {"India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore"};
	private int day, month, year, zipCode;
	boolean newsLetter, optin;
	
	public void generateRandomUser() {
		
		Faker faker = new Faker();
		int randInt = MathUtils.getRandomIntWithRange(0, 1);
		
		title = randInt == 0 ? "Mr" : "Mrs";
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		email = faker.internet().emailAddress();
		password = faker.internet().password();
		day = MathUtils.getRandomIntWithRange(1, 31);
		month = MathUtils.getRandomIntWithRange(1, 12);
		year = MathUtils.getRandomIntWithRange(1900, 2025);
		company = faker.company().name();
		address1 = faker.address().streetAddress();
		address2 = faker.address().secondaryAddress();
		randInt = MathUtils.getRandomIntWithRange(0, 6);
		country = countries[randInt];
		state = faker.address().state();
		city = faker.address().city();
		zipCode = Integer.valueOf(faker.numerify("#####"));
		mobileNumber = faker.numerify("###-###-####");
	}
	
	public String getBirthday() {
		return day + "-" + month + "-" + year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}

	public boolean getNewsLetter() {
		return newsLetter;
	}

	public boolean getOptin() {
		return optin;
	}

	public String getCompany() {
		return company;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}
	
	
	
}
