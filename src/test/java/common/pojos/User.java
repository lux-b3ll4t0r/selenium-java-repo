package common.pojos;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import common.utils.LogUtil;

public class User {

	private int id;
	
	private String title, name, email, company, address1, 
	address2, country, state, city, zipcode;	
	
	@JsonProperty("first_name")
	String firstName;
	
	@JsonProperty("last_name")
	String lastName;
	
	@JsonProperty("birth_day")
	String birthDate;
	
	@JsonProperty("birth_month")
	String birthMonth;
	
	@JsonProperty("birth_year")
	String birthYear;
	
	@JsonProperty("mobile_number")
	String mobileNumber;
	
	@JsonIgnore
	private String newsletter, optin, password;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getBirthDate() {return birthDate;}
	public void setBirthDay(String birthDate) {this.birthDate = birthDate;}

	public String getBirthMonth() {return birthMonth;}
	public void setBirthMonth(String birthMonth) {this.birthMonth = birthMonth;}

	public String getBirthYear() {return birthYear;}
	public void setBirthYear(String birthYear) {this.birthYear = birthYear;}

	public String getNewsLetter() {return newsletter;}
	public void setNewsLetter(String newsletter) {this.newsletter = newsletter;}

	public String getOptin() {return optin;}
	public void setOptin(String optin) {this.optin = optin;}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public String getCompany() {return company;}
	public void setCompany(String company) {this.company = company;}

	public String getAddress1() {return address1;}
	public void setAddress1(String address1) {this.address1 = address1;}

	public String getAddress2() {return address2;}
	public void setAddress2(String address2) {this.address2 = address2;}

	public String getCountry() {return country;}
	public void setCountry(String country) {this.country = country;}

	public String getState() {return state;}
	public void setState(String state) {this.state = state;}

	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}

	public String getZipcode() {return zipcode;}
	public void setZipcode(String zipcode) {this.zipcode = zipcode;}

	public String getMobileNumber() {return mobileNumber;}
	public void setMobileNumber(String mobileNumber) {this.mobileNumber = mobileNumber;}

	public String getBirthday() {
		return birthDate + "-" + birthMonth + "-" + birthYear;
	}
	
	public static class Builder { // static class to avoid instantiating builder class
		private final User user; // private final to encapsulate, and prevent further instantiation
		
		public Builder() {   // public constructor to instantiate user
			user = new User();
		}
		
		// all methods return this class to allow method chaining
		public Builder name(String name) {
			this.user.setName(name);
			return this;
		}
		
		public Builder email(String email) {
			this.user.setEmail(email);
			return this;
		}
		
		public Builder title(String title) {
			this.user.setTitle(title);
			return this;
		}
		
		public Builder birth_day(String birth_day) {
			try {
			if(Integer.valueOf(birth_day) > 0 &&
					Integer.valueOf(birth_day) <= 31){
				this.user.setBirthDay(birth_day);
			}else {
				throw new IllegalArgumentException("Birth day must be between 1-31");
			}
			}catch (Exception e) {
				LogUtil.error("Argument might've returned null:\n", e);
			}
			return this;
		}
		
		public Builder birth_month(String birth_month) {
			try {
				Month.valueOf(birth_month.toUpperCase());
				this.user.setBirthMonth(birth_month);
			}catch(Exception e) {
				LogUtil.error("Month: " + birth_month + " not recognized: ", e);
			}
			return this;
		}
		
		public Builder birth_year(String birth_year) {
			try {
			if(Integer.valueOf(birth_year) >= 1900 && 
					Integer.valueOf(birth_year) <= 2025) {
					this.user.setBirthYear(birth_year);
				}
			}
			catch(Exception e) {
				LogUtil.error("Birth Year: " + birth_year + " not recognized: " + e); 
			}
			return this;
		}
		
		public Builder newsletter(String newsletter) {
			
			if(Boolean.valueOf(newsletter) == true || 
					Boolean.valueOf(newsletter) == false) {
				this.user.setNewsLetter(newsletter);
			}else {
				throw new IllegalArgumentException("Newsletter must be true or false");
			}
			return this;
		}
		
		public Builder optin(String optin) {
			if(Boolean.valueOf(optin) == true || 
					Boolean.valueOf(optin) == false) {
				this.user.setOptin(optin);
			}else {
				throw new IllegalArgumentException("Optin must be true or false");
			}
			return this;
		}
		
		public Builder first_name() {
			if(this.user.name != null) {
				String[] names = this.user.name.split(" ");
				this.user.setFirstName(names[0]);
			}else {
				throw new NullPointerException("Name has not been set.");
			}
			return this;
		}
		
		public Builder first_name(String first_name) {
			this.user.setFirstName(first_name);
			return this;
		}
		
		public Builder last_name() {
			if(this.user.name != null) {
				String[] names = this.user.name.split(" ");
				
				if(names.length > 2) {
					this.user.setLastName(names[1] + " " + names[2]);
				}else {
					this.user.setLastName(names[1]);
				}
			}else {
				throw new NullPointerException("Name has not been set.");
			}
			
			return this;
		}

		public Builder last_name(String last_name) {
			this.user.setLastName(last_name);
			return this;
		}
		public Builder company(String company) {
			this.user.setCompany(company);
			return this;
		}

		public Builder address1(String address1) {
			this.user.setAddress1(address1);
			return this;
		}

		public Builder address2(String address2) {
			this.user.setAddress2(address2);
			return this;
		}

		public Builder country(String country) {
			this.user.setCountry(country);
			return this;
		}

		public Builder state(String state) {
			this.user.setState(state);
			return this;
		}

		public Builder city(String city) {
			this.user.setCity(city);
			return this;
		}

		public Builder zipcode(String zipcode) {
			this.user.setZipcode(zipcode);
			return this;
		}

		public Builder mobile_number(String mobile_number) {
			this.user.setMobileNumber(mobile_number);
			return this;
		}

		public Builder password(String password) {
			this.user.setPassword(password);
			return this;
		}

		public User build() { // build method that returns the user once built
			return user;
		}
	}
	
	private String maskEmail(String email) {
	    if (email == null || !email.contains("@")) return "hidden";
	    String[] parts = email.split("@");
	    String prefix = parts[0];
	    return prefix.charAt(0) + "***" + prefix.charAt(prefix.length() - 1) + "@" + parts[1];
	}

	private String maskPassword(String password) {
	    return (password == null) ? "hidden" : "****";
	}

	private String maskPhone(String phone) {
	    if (phone == null || phone.length() < 4) return "hidden";
	    String[] parts = phone.split("-");
	    return "***-" + "***-" + parts[2];

	}

	public Map<String, Object> getAsMap(){
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("name", this.getName());
		userMap.put("email", this.getEmail());
		userMap.put("password", this.getPassword());
		userMap.put("title", this.getTitle());
		userMap.put("birth_date", this.getBirthDate());
		userMap.put("birth_month", this.getBirthMonth());
		userMap.put("birth_year", this.getBirthYear());
		userMap.put("firstname", this.getFirstName());
		userMap.put("lastname", this.getLastName());
		userMap.put("company", this.getCompany());
		userMap.put("address1", this.getAddress1());
		userMap.put("address2", this.getAddress2());
		userMap.put("country", this.getCountry());
		userMap.put("state", this.getState());
		userMap.put("city", this.getCity());
		userMap.put("zipcode", this.getZipcode());
		userMap.put("mobile_number", this.getMobileNumber());

		return userMap;
	}
	
	public Map<String, Object> getAsMapRequiredOnly(){
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("name", this.getName());
		userMap.put("email", this.getEmail());
		userMap.put("password", this.getPassword());
		userMap.put("firstname", this.getFirstName());
		userMap.put("lastname", this.getLastName());
		userMap.put("address1", this.getAddress1());
		userMap.put("country", this.getCountry());
		userMap.put("state", this.getState());
		userMap.put("city", this.getCity());
		userMap.put("zipcode", this.getZipcode());
		userMap.put("mobile_number", this.getMobileNumber());

		return userMap;
	}
	
	public Map<String, Object> getAsUserDetailsCompareMap(){
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("name", this.getName());
		userMap.put("email", this.getEmail());
		userMap.put("title", this.getTitle());
		userMap.put("birth_day", this.getBirthDate());
		userMap.put("birth_month", this.getBirthMonth());
		userMap.put("birth_year", this.getBirthYear());
		userMap.put("first_name", this.getFirstName());
		userMap.put("last_name", this.getLastName());
		userMap.put("company", this.getCompany());
		userMap.put("address1", this.getAddress1());
		userMap.put("address2", this.getAddress2());
		userMap.put("country", this.getCountry());
		userMap.put("state", this.getState());
		userMap.put("city", this.getCity());
		userMap.put("zipcode", this.getZipcode());
		return userMap;
	}
	
	public Map<String, Object> getDeleteRequestMap(){
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("email", this.getEmail());
		userMap.put("password", this.getPassword());
		
		return userMap;
	}
	
	public String toSafeString() {	
		return "[User: Title: " + title +
		" [Full Name: " + name +
		"] [First Name: " + firstName +
		"] [Last Name: " + lastName +
		"] [Email: " + maskEmail(email) +
		"] [Password: " + maskPassword(password) +
		"] [Birth Day: " + birthDate +
		"] [Birth Month: " + birthMonth +
		"] [Birth Year: " + birthYear +
		"] [Company: " + company +
		"] [Newsletter: " + newsletter +
		"] [Optin: " + optin +
		"] [Address 1: " + address1 + 
		"] [Address 2: " + address2 +
		"] [Country: " + country +
		"] [State: " + state +
		"] [City: " + city + 
		"] [Zipcode: " + zipcode + 
		"] [Mobile Number: " + maskPhone(mobileNumber) + "]";

	}
}
