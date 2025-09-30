package ui.pages.payment;

import org.openqa.selenium.By;

import ui.utils.Webtool;

public class Payment {
	
	private By cardName = By.name("name_on_card");
	private By cardNumber = By.name("card_number");
	private By cvc = By.name("cvc");
	private By expireMonth = By.name("expiry_month");
	private By expireYear = By.name("expiry_year");
	private By confirmBtn = By.id("submit");
	
	public void setName(String name) {Webtool.sendKeysTo(cardName, name);}
	public void setCardNumber(int number) {Webtool.sendKeysTo(cardNumber, String.valueOf(number));}
	public void setCvc(int cvc) {Webtool.sendKeysTo(this.cvc, String.valueOf(cvc));}
	public void setExpireMonth(int month) {Webtool.sendKeysTo(expireMonth, String.valueOf(month));}
	public void setExpireYear(int year) {Webtool.sendKeysTo(expireYear, String.valueOf(year));}
	public void confirmOrder() {Webtool.clickElement(confirmBtn);}
	
}
