package pages.contactus;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import utils.BasePage;

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
		return BasePage.isElementVisible(contactUsHeader);
	}
	
	public void enterName(String name) {
		BasePage.sendKeysTo(nameInput, name);
	}
	
	public boolean isNameInputVisible() {
		return BasePage.isElementVisible(nameInput);
	}
	
	public void enterEmail(String email) {
		BasePage.sendKeysTo(emailInput, email);
	}
	
	public boolean isEmailInputVisible() {
		return BasePage.isElementVisible(emailInput);
	}
	
	public void enterSubject(String subject) {
		BasePage.sendKeysTo(subjectInput, subject);
	}
	
	public boolean isSubjectInputVisible() {
		return BasePage.isElementVisible(subjectInput);
	}
	
	public void enterMessage(String message) {
		BasePage.sendKeysTo(messageInput, message);
	}
	
	public boolean isMessageInputVisible() {
		return BasePage.isElementVisible(messageInput);
	}
	
	public void uploadFile(String filePath) {
		BasePage.sendKeysTo(fileUpload, filePath);
	}
	
	public boolean isUploadFileInputVisible() {
		return BasePage.isElementVisible(fileUpload);
	}
	
	public void clickSubmit() {
		BasePage.clickElement(submitBtn);
	}
	
	public boolean isSubmitBtnVisible() {
		return BasePage.isElementVisible(submitBtn);
	}
	
	public void closeAlert() {
		
		Alert alert = BasePage.getDriver().switchTo().alert();
		alert.accept();
	}
	
	public boolean isSuccessMessageVisible() {
		return BasePage.isElementVisible(successMsg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
