package pages.signup_login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.BasePage;

public class SignUpAccountInfo extends BasePage{
	
	public SignUpAccountInfo(WebDriver driver) {
		super(driver);
	}
	private By accInfoHeader = By.xpath("//h2/b[contains(text(), 'Enter Account Information')]");
	private By addressInfoHeader = By.xpath("//h2/b[contains(text(), 'Address Information')]");
	private By gender1Button = By.id("id_gender1");
	private By gender2Button = By.id("id_gender2");
	private By nameInput = By.id("name");
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
	
	public void selectTitle(String title) {
		switch(title) {
		case "Mr":
			clickElement(gender1Button);
			break;
		case "Mrs":
			clickElement(gender2Button);
			break;
			default:
				throw new IllegalArgumentException("Title: " + title + " not found.");
		}
	}
	
	public void selectDay(int day) {
		Select selector = new Select(driver.findElement(daySelector));
		
		if(day >= 1 && day <= 31) {
			selector.selectByVisibleText(String.valueOf(day));
		}else {
			throw new IllegalArgumentException("Day must be between 1 and 31");
		}
	}
	
	public void selectMonth(int month) {
		Select selector = new Select(driver.findElement(monthSelector));
		
		if(month >= 1 && month <= 12) {
			selector.selectByIndex(month);
		}else {
			throw new IllegalArgumentException("Month must be between 1 and 12");
		}
	}
	
	public void selectYear(int year) {
		Select selector = new Select(driver.findElement(yearSelector));
		
		if(year >= 1900 && year <= 2025) {
			selector.selectByValue(String.valueOf(year));
		}else {
			throw new IllegalArgumentException("Year must be between 1900 and 2025");
		}
		
	}
	
	public void clickSignUpForNewsLetter() {
		clickElement(newsLetterButton);
	}
	
	public void clickReceiveSpecialOffers() {
		clickElement(specialOffersButton);
	}
	
	public void clickCreateAccount() {
		clickElement(createAccountButton);
	}
	
	public void enterFirstName(String firstName) {
		sendKeysTo(firstNameInput, firstName);
	}
	
	public void enterLastName(String lastName) {
		sendKeysTo(lastNameInput, lastName);
	}
	
	public void enterCompany(String company) {
		sendKeysTo(companyInput, company);
	}
	
	public void enterAddress1(String address1) {
		sendKeysTo(address1Input, address1);
	}
	
	public void enterAddress2(String address2) {
		sendKeysTo(address2Input, address2);
	}
	
	public void selectCountry(String country) {
		Select selector = new Select(driver.findElement(countrySelector));
		selector.selectByValue(country);
	}
	
	public void enterState(String state) {
		sendKeysTo(stateInput, state);
	}
	
	public void enterCity(String city) {
		sendKeysTo(cityInput, city);
	}
	
	public void enterZipCode(int zipCode) {
		sendKeysTo(zipCodeInput, String.valueOf(zipCode));
	}
	
	public void enterMobileNumber(String mobileNumber) {
		sendKeysTo(mobileNumberInput, mobileNumber);
	}
	
	public void enterPassword(String password) {
		sendKeysTo(passwordInput, password);
	}
	
	public boolean isAccountInfoHeaderVisible() {
		return isElementVisible(accInfoHeader);
	}
	/////////////////////////////////////////////
	
//	public String getGender1ButtonValue() {
//		return gender1Button.getAttribute("value");
//	}
//
//	public String getGender2ButtonValue() {
//		return gender2Button.getAttribute("value");
//	}
//
//	public String getNameInputValue() {
//		return nameInput.getAttribute("value");
//	}
//
//	public String getEmailInputValue() {
//		return emailInput.getAttribute("value");
//	}
//
//	public String getPasswordInputValue() {
//		return passwordInput.getAttribute("value");
//	}
//
//	public String getDaySelectorValue() {
//		return daySelector.getAttribute("value");
//	}
//
//	public String getMonthSelectorValue() {
//		return monthSelector.getAttribute("value");
//	}
//
//	public String getYearSelectorValue() {
//		return yearSelector.getAttribute("value");
//	}
//
//	public String getFirstNameInputValue() {
//		return firstNameInput.getAttribute("value");
//	}
//
//	public String getLastNameInputValue() {
//		return lastNameInput.getAttribute("value");
//	}
//
//	public String getCompanyInputValue() {
//		return companyInput.getAttribute("value");
//	}
//
//	public String getAddress1InputValue() {
//		return address1Input.getAttribute("value");
//	}
//
//	public String getAddress2InputValue() {
//		return address2Input.getAttribute("value");
//	}
//
//	public String getCountrySelectorValue() {
//		return countrySelector.getAttribute("value");
//	}
//
//	public String getStateInputValue() {
//		return stateInput.getAttribute("value");
//	}
//
//	public String getCityInputValue() {
//		return cityInput.getAttribute("value");
//	}
//
//	public String getZipCodeInputValue() {
//		return zipCodeInput.getAttribute("value");
//	}
//
//	public String getMobileNumberInputValue() {
//		return mobileNumberInput.getAttribute("value");
//	}

}
