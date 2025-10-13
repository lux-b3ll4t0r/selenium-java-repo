package ui.pages.signup_login;


import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.Select;

import common.pojos.User;
import common.utils.LogUtil;
import ui.utils.DriverFactory;
import ui.utils.Webtool;

public class SignUpAccountInfo {
	

	private By accInfoHeader = By.xpath("//h2/b[contains(text(), 'Enter Account Information')]");
	private By gender1Button = By.id("id_gender1");
	private By gender2Button = By.id("id_gender2");
	private By fullName = By.id("name");
	private By emailInput = By.id("email");
	private By passwordInput = By.id("password");
	private By daySelector = By.id("days");
	private By monthSelector = By.id("months");
	private By yearSelector = By.id("years");
	private By newsLetterButton = By.id("newsletter");
	private By specialOffersButton = By.id("optin");
	private By firstNameInput = By.id("first_name");
	private By lastNameInput = By.id("last_name");
	private By companyInput = By.id("company");
	private By address1Input = By.id("address1");
	private By address2Input = By.id("address2");
	private By countrySelector = By.id("country");
	private By stateInput = By.id("state");
	private By cityInput = By.id("city");
	private By zipCodeInput = By.id("zipcode");
	private By mobileNumberInput = By.id("mobile_number");
	private By createAccountButton = By.xpath("//button[@data-qa='create-account']");
	
	public void enterAddress1(String address1) {Webtool.sendKeysTo(address1Input, address1);}	
	public void enterAddress2(String address2) {Webtool.sendKeysTo(address2Input, address2);}	
	public void clickReceiveSpecialOffers() {Webtool.clickElement(specialOffersButton);}
	public void clickSignUpForNewsLetter() {Webtool.clickElement(newsLetterButton);}
	public void enterCity(String city) {Webtool.sendKeysTo(cityInput, city);}	
	public void enterCompany(String company) {Webtool.sendKeysTo(companyInput, company);}
	public void enterFirstName(String firstName) {Webtool.sendKeysTo(firstNameInput, firstName);}
	public void enterLastName(String lastName) {Webtool.sendKeysTo(lastNameInput, lastName);}
	public void enterMobileNumber(String mobileNumber) {Webtool.sendKeysTo(mobileNumberInput, mobileNumber);}
	public void enterPassword(String password) {Webtool.sendKeysTo(passwordInput, password);}
	public void enterState(String state) {Webtool.sendKeysTo(stateInput, state);}	
	public void enterZipCode(String zipcode) {Webtool.sendKeysTo(zipCodeInput, zipcode);}		
	public String getFullNameInput() {return Webtool.getElementAttribute(fullName, "value");}
	public String getEmailInput() {return Webtool.getElementAttribute(emailInput, "value");}
	public boolean isAccountInfoHeaderVisible() {return Webtool.isElementVisible(accInfoHeader);}
	
	
	public void clickCreateAccount() {
		try {
		Webtool.clickElement(createAccountButton);
		}catch(ElementClickInterceptedException e) {
			LogUtil.warn("Click was intercepted by another element. Attempting to scroll to element and click.");
			Webtool.scrollToElementLocated(createAccountButton);
			Webtool.clickElement(createAccountButton);
		}
	}
	
//	public void reportValidity() {
//		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
//		js.executeScript("arguments[0].reportValidity();", Webtool.waitForVisibitlityOfElementLocated(accInfoForm));
//	}
//	
	
	public boolean isPasswordBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(passwordInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public boolean isFirstNameBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(firstNameInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public boolean isLastNameBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(lastNameInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public boolean isAddressBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(lastNameInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public boolean isStateBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(lastNameInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public boolean isCityBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(lastNameInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public boolean isZipBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(lastNameInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public boolean isMobileNumberBrowserErrorVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(lastNameInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}
	
	public void enterAndSubmitAccInfo(User user) {
		selectTitle(user.getTitle());
		enterPassword(user.getPassword()); 
		selectDay(user.getBirthDate());				 
		selectMonth(user.getBirthMonth());
		selectYear(user.getBirthYear());
		clickSignUpForNewsLetter();
		clickReceiveSpecialOffers();
		enterFirstName(user.getFirstName());
		enterLastName(user.getLastName());
		enterCompany(user.getCompany());
		enterAddress1(user.getAddress1());
		enterAddress2(user.getAddress2());
		selectCountry(user.getCountry());
		enterState(user.getState());
		enterCity(user.getCity());
		enterZipCode(user.getZipcode());
		enterMobileNumber(user.getMobileNumber());
		clickCreateAccount();
	}
	
	public void selectCountry(String country) {
		Select selector = new Select(DriverFactory.getDriver().findElement(countrySelector));
		selector.selectByValue(country);
	}
	
	public void selectDay(String day) {
		Select selector = new Select(DriverFactory.getDriver().findElement(daySelector));
		try {
		if(Integer.valueOf(day) >= 1 && Integer.valueOf(day) <= 31) {
			selector.selectByVisibleText(day);
		}
		}catch(Exception e) {
			LogUtil.error("Day not in range (1-31) or text not visible", e);
		}
	}
	
	public void selectMonth(String month) {
		Select selector = new Select(DriverFactory.getDriver().findElement(monthSelector));
	
		try {
			Month.valueOf(month.toUpperCase());
			selector.selectByVisibleText(month);
		}catch(Exception e) {
			LogUtil.error("Month: " + month + " not recognized or text not visible", e);
		}
		
	}
	
	public void selectTitle(String title) {
		switch(title) {
		case "Mr":
			Webtool.clickElement(gender1Button);
			break;
		case "Mrs":
			Webtool.clickElement(gender2Button);
			break;
			default:
				throw new IllegalArgumentException("Title: " + title + " not found.");
		}
	}
	
	public void selectYear(String year) {
		Select selector = new Select(DriverFactory.getDriver().findElement(yearSelector));
		
		try {
		if(Integer.valueOf(year) >= 1900 && Integer.valueOf(year) <= 2025) {
			selector.selectByValue(String.valueOf(year));
		}
		}catch(Exception e) {
			LogUtil.error("Year not in range (1900-2021) or value doesn't exist", e);
		}
	}
}
