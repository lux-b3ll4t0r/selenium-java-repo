package ui.pages.contactus;

import org.openqa.selenium.By;
import com.github.javafaker.Faker;
import common.utils.ConfigManager;
import ui.utils.Webtool;

public class ContactUs {
	
	private By contactUsHeader = By.xpath("//h2[contains(text(), 'Contact')]");
	private By emailInput = By.name("email");
	private By fileUpload = By.name("upload_file");
	private By messageInput = By.name("message");
	private By nameInput = By.name("name");
	private By subjectInput = By.name("subject");
	private By submitBtn = By.name("submit");
	private By successMsg = By.xpath("//div[@class='status alert alert-success'][contains(text(), 'Success!')]");
	
	public void clickSubmit() {Webtool.clickElement(submitBtn);}
	public void enterEmail(String email) {Webtool.sendKeysTo(emailInput, email);}
	public void enterMessage(String message) {Webtool.sendKeysTo(messageInput, message);}
	public void enterName(String name) {Webtool.sendKeysTo(nameInput, name);}
	public void enterSubject(String subject) {Webtool.sendKeysTo(subjectInput, subject);}
	public void uploadFile(String filePath) {Webtool.sendKeysTo(fileUpload, filePath);}
	public boolean isContactUsHeaderVisible() {return Webtool.isElementVisible(contactUsHeader);}
	public boolean isEmailInputVisible() {return Webtool.isElementVisible(emailInput);}
	public boolean isMessageInputVisible() {return Webtool.isElementVisible(messageInput);}
	public boolean isNameInputVisible() {return Webtool.isElementVisible(nameInput);}
	public boolean isSubjectInputVisible() {return Webtool.isElementVisible(subjectInput);}
	public boolean isSubmitBtnVisible() {return Webtool.isElementVisible(submitBtn);}
	public boolean isSuccessMessageVisible() {return Webtool.isElementVisible(successMsg);}
	public boolean isUploadFileInputVisible() {return Webtool.isElementVisible(fileUpload);}
	
	
	public void submitContactUsForm() {
		enterName(new Faker().name().fullName());
		enterEmail(new Faker().internet().emailAddress());
		enterSubject(new Faker().book().title());
		enterMessage(new Faker().lorem().paragraph());
		uploadFile(System.getProperty("user.dir") + "/" + ConfigManager.getPicsPath() + "icon.png");
		clickSubmit();
	}
	
	public boolean isEmailBrowserErrorMsgVisible() {
		try {
			String msg = Webtool.getBrowserErrorMessage(emailInput);
			return !msg.isEmpty();
		}catch(NullPointerException e) {
			return false;
		}
	}

	
}
