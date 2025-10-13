package ui.pages.payment;

import org.openqa.selenium.By;

import com.github.javafaker.Faker;

import ui.utils.Webtool;

public class Payment {
	
	private By cardName = By.name("name_on_card");
	private By cardNumber = By.name("card_number");
	private By cvc = By.name("cvc");
	private By expireMonth = By.name("expiry_month");
	private By expireYear = By.name("expiry_year");
	private By confirmBtn = By.id("submit");
	private By orderPlaced = By.xpath("//b[text() ='Order Placed!']");
	
	public void setName(String name) {Webtool.sendKeysTo(cardName, name);}
	public void setCardNumber(String number) {Webtool.sendKeysTo(cardNumber, number);}
	public void setCvc(String cvc) {Webtool.sendKeysTo(this.cvc, cvc);}
	public void setExpireMonth(String month) {Webtool.sendKeysTo(expireMonth, month);}
	public void setExpireYear(String year) {Webtool.sendKeysTo(expireYear, year);}
	public void confirmOrder() {Webtool.clickElement(confirmBtn);}
	public boolean isOrderPlacedVisible() {return Webtool.isElementVisible(orderPlaced);}
	
	public void submitPaymentInfo() {
		Faker faker = new Faker();
		
		setName(faker.name().fullName());
		setCardNumber(faker.finance().creditCard());
		setCvc(faker.numerify("###"));
		setExpireMonth(faker.numerify("##"));
		setExpireYear(faker.numerify("####"));
		confirmOrder();
	}
	
}
