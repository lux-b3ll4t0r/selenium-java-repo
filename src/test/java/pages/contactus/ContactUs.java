package pages.contactus;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.BasePage;

public class ContactUs extends BasePage{
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
	
	
	public ContactUs(WebDriver driver) {
		super(driver);
	}
	
	private By contactUsHeader = By.xpath("//h2[contains(text(), 'Contact')]");
	private By nameInput = By.name("name");
	private By emailInput = By.name("email");
	private By subjectInput = By.name("subject");
	private By messageInput = By.name("message");
	private By fileUpload = By.name("upload_file");
	private By submitBtn = By.name("submit");
	private By successMsg = By.xpath("//div[@class='status alert alert-success'][contains(text(), 'Success!')]");
	
	
	public boolean isContactUsHeaderVisible() {
		return isElementVisible(contactUsHeader);
	}
	
	public void enterName(String name) {
		sendKeysTo(nameInput, name);
	}
	
	public boolean isNameInputVisible() {
		return isElementVisible(nameInput);
	}
	
	public void enterEmail(String email) {
		sendKeysTo(emailInput, email);
	}
	
	public boolean isEmailInputVisible() {
		return isElementVisible(emailInput);
	}
	
	public void enterSubject(String subject) {
		sendKeysTo(subjectInput, subject);
	}
	
	public boolean isSubjectInputVisible() {
		return isElementVisible(subjectInput);
	}
	
	public void enterMessage(String message) {
		sendKeysTo(messageInput, message);
	}
	
	public boolean isMessageInputVisible() {
		return isElementVisible(messageInput);
	}
	
	public void uploadFile(String filePath) {
		sendKeysTo(fileUpload, filePath);
	}
	
	public boolean isUploadFileInputVisible() {
		return isElementVisible(fileUpload);
	}
	
	public void clickSubmit() {
		clickElement(submitBtn);
	}
	
	public boolean isSubmitBtnVisible() {
		return isElementVisible(submitBtn);
	}
	
	public void closeAlert() {
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public boolean isSuccessMessageVisible() {
		return isElementVisible(successMsg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
