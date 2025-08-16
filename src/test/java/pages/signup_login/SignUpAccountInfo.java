package pages.signup_login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.BasePage;

public class SignUpAccountInfo extends BasePage{
	
	public SignUpAccountInfo(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h2/b[contains(text(), 'Enter Account Information')]")
	private WebElement accInfoHeader;
	
	@FindBy(xpath = "//h2/b[contains(text(), 'Address Information')]")
	private WebElement addressInfoHeader;

	@FindBy(id = "id_gender1")
	private WebElement gender1Button;
	
	@FindBy(id = "id_gender2")
	private WebElement gender2Button;
	
	@FindBy(id = "name")
	private WebElement nameInput;
	
	@FindBy(id = "email")
	private WebElement emailInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput;
	
	@FindBy(id = "days")
	private WebElement daySelector;
	
	@FindBy(id = "months")
	private WebElement monthSelector;
	
	@FindBy(id = "years")
	private WebElement yearSelector;
	
	@FindBy(id = "newsletter")
	private WebElement newsLetterButton;
	
	@FindBy(id = "optin")
	private WebElement specialOffersButton;
	
	@FindBy(id = "first_name")
	private WebElement firstNameInput;
	
	@FindBy(id = "last_name")
	private WebElement lastNameInput;
	
	@FindBy(id = "company")
	private WebElement companyInput;
	
	@FindBy(id = "address1")
	private WebElement address1Input;
	
	@FindBy(id = "address2")
	private WebElement address2Input;
	
	@FindBy(id = "country")
	private WebElement countrySelector;
	
	@FindBy(id = "state")
	private WebElement stateInput;
	
	@FindBy(id = "city")
	private WebElement cityInput;
	
	@FindBy(id = "zipcode")
	private WebElement zipCodeInput;
	
	@FindBy(id = "mobile_number")
	private WebElement mobileNumberInput;
	
	@FindBy(xpath = "//button[@data-qa='create-account']")
	private WebElement createAccountButton;
	
	public void selectTitle(String title) {
		switch(title) {
		case "Mr":
			gender1Button.click();
			break;
		case "Mrs":
			gender2Button.click();
			break;
			default:
				throw new IllegalArgumentException("Title: " + title + " not found.");
		}
	}
	
	public void selectDay(int day) {
		Select selector = new Select(daySelector);
		
		if(day >= 1 && day <= 31) {
			selector.selectByValue(String.valueOf(day));
		}else {
			throw new IllegalArgumentException("Day must be between 1 and 31");
		}
	}
	
	public void selectMonth(int month) {
		Select selector = new Select(monthSelector);
		
		if(month >= 1 && month <= 12) {
			selector.selectByIndex(month);
		}else {
			throw new IllegalArgumentException("Month must be between 1 and 12");
		}
	}
	
	public void selectYear(int year) {
		Select selector = new Select(yearSelector);
		
		if(year >= 1900 && year <= 2025) {
			selector.selectByValue(String.valueOf(year));
		}else {
			throw new IllegalArgumentException("Year must be between 1900 and 2025");
		}
		
	}
	
	public void clickSignUpForNewsLetter() {
		newsLetterButton.click();
	}
	
	public void clickReceiveSpecialOffers() {
		specialOffersButton.click();
	}
	
	public void enterFirstName(String firstName) {
		firstNameInput.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameInput.sendKeys(lastName);
	}
	
	public void enterCompany(String company) {
		companyInput.sendKeys(company);
	}
	
	public void enterAddress1(String address1) {
		address1Input.sendKeys(address1);
	}
	
	public void enterAddress2(String address2) {
		address2Input.sendKeys(address2);
	}
	
	public void selectCountry(String country) {
		Select selector = new Select(countrySelector);
		selector.selectByValue(country);
	}
	
	public void enterState(String state) {
		stateInput.sendKeys(state);
	}
	
	public void enterCity(String city) {
		cityInput.sendKeys(city);
	}
	
	public void enterZipCode(int zipCode) {
		zipCodeInput.sendKeys(String.valueOf(zipCode));
	}
	
	public void enterMobileNumber(String mobileNumber) {
		mobileNumberInput.sendKeys(mobileNumber);
	}
	
	
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public WebElement getAccInfoHeader() {
		return accInfoHeader;
	}
	
	public WebElement getAddressInfoHeader() {
		return addressInfoHeader;
	}

	public WebElement getGender1Button() {
		return gender1Button;
	}

	public WebElement getGender2Button() {
		return gender2Button;
	}

	public WebElement getNameInput() {
		return nameInput;
	}

	public WebElement getEmailInput() {
		return emailInput;
	}

	public WebElement getPasswordInput() {
		return passwordInput;
	}

	public WebElement getDaySelector() {
		return daySelector;
	}

	public WebElement getMonthSelector() {
		return monthSelector;
	}

	public WebElement getYearSelector() {
		return yearSelector;
	}

	public WebElement getNewsLetterButton() {
		return newsLetterButton;
	}

	public WebElement getSpecialOffersButton() {
		return specialOffersButton;
	}

	public WebElement getFirstNameInput() {
		return firstNameInput;
	}

	public WebElement getLastNameInput() {
		return lastNameInput;
	}

	public WebElement getCompanyInput() {
		return companyInput;
	}

	public WebElement getAddress1Input() {
		return address1Input;
	}

	public WebElement getAddress2Input() {
		return address2Input;
	}

	public WebElement getCountrySelector() {
		return countrySelector;
	}

	public WebElement getStateInput() {
		return stateInput;
	}

	public WebElement getCityInput() {
		return cityInput;
	}

	public WebElement getZipCodeInput() {
		return zipCodeInput;
	}

	public WebElement getMobileNumberInput() {
		return mobileNumberInput;
	}
	
	/////////////////////////////////////////////
	
	public String getGender1ButtonValue() {
		return gender1Button.getAttribute("value");
	}

	public String getGender2ButtonValue() {
		return gender2Button.getAttribute("value");
	}

	public String getNameInputValue() {
		return nameInput.getAttribute("value");
	}

	public String getEmailInputValue() {
		return emailInput.getAttribute("value");
	}

	public String getPasswordInputValue() {
		return passwordInput.getAttribute("value");
	}

	public String getDaySelectorValue() {
		return daySelector.getAttribute("value");
	}

	public String getMonthSelectorValue() {
		return monthSelector.getAttribute("value");
	}

	public String getYearSelectorValue() {
		return yearSelector.getAttribute("value");
	}

	public String getFirstNameInputValue() {
		return firstNameInput.getAttribute("value");
	}

	public String getLastNameInputValue() {
		return lastNameInput.getAttribute("value");
	}

	public String getCompanyInputValue() {
		return companyInput.getAttribute("value");
	}

	public String getAddress1InputValue() {
		return address1Input.getAttribute("value");
	}

	public String getAddress2InputValue() {
		return address2Input.getAttribute("value");
	}

	public String getCountrySelectorValue() {
		return countrySelector.getAttribute("value");
	}

	public String getStateInputValue() {
		return stateInput.getAttribute("value");
	}

	public String getCityInputValue() {
		return cityInput.getAttribute("value");
	}

	public String getZipCodeInputValue() {
		return zipCodeInput.getAttribute("value");
	}

	public String getMobileNumberInputValue() {
		return mobileNumberInput.getAttribute("value");
	}
	public WebElement getCreateAccountButton() {
		return createAccountButton;
	}
}
