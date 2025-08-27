package pages.contactus;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import utils.Webtool;

public class ContactUs {
	
	private By contactUsHeader = By.xpath("//h2[contains(text(), 'Contact')]");
	private By nameInput = By.name("name");
	private By emailInput = By.name("email");
	private By subjectInput = By.name("subject");
	private By messageInput = By.name("message");
	private By fileUpload = By.name("upload_file");
	private By submitBtn = By.name("submit");
	private By successMsg = By.xpath("//div[@class='status alert alert-success'][contains(text(), 'Success!')]");
	
	
	public boolean isContactUsHeaderVisible() {
		return Webtool.isElementVisible(contactUsHeader);
	}
	
	public void enterName(String name) {
		Webtool.sendKeysTo(nameInput, name);
	}
	
	public boolean isNameInputVisible() {
		return Webtool.isElementVisible(nameInput);
	}
	
	public void enterEmail(String email) {
		Webtool.sendKeysTo(emailInput, email);
	}
	
	public boolean isEmailInputVisible() {
		return Webtool.isElementVisible(emailInput);
	}
	
	public void enterSubject(String subject) {
		Webtool.sendKeysTo(subjectInput, subject);
	}
	
	public boolean isSubjectInputVisible() {
		return Webtool.isElementVisible(subjectInput);
	}
	
	public void enterMessage(String message) {
		Webtool.sendKeysTo(messageInput, message);
	}
	
	public boolean isMessageInputVisible() {
		return Webtool.isElementVisible(messageInput);
	}
	
	public void uploadFile(String filePath) {
		Webtool.sendKeysTo(fileUpload, filePath);
	}
	
	public boolean isUploadFileInputVisible() {
		return Webtool.isElementVisible(fileUpload);
	}
	
	public void clickSubmit() {
		Webtool.clickElement(submitBtn);
	}
	
	public boolean isSubmitBtnVisible() {
		return Webtool.isElementVisible(submitBtn);
	}
	
	public void closeAlert() {
		
		Alert alert = Webtool.getDriver().switchTo().alert();
		alert.accept();
	}
	
	public boolean isSuccessMessageVisible() {
		return Webtool.isElementVisible(successMsg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
