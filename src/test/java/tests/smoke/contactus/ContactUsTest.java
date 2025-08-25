package tests.smoke.contactus;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import base.BaseTest;
import pages.contactus.ContactUs;
import pages.homepage.NavBar;
import utils.BasePage;
import utils.ConfigManager;
import utils.LogUtil;

@Listeners(utils.TestListener.class)
public class ContactUsTest extends BaseTest {

	private NavBar navBar;
	private ContactUs contactUs;
	private BasePage basePage;
	
	@BeforeMethod(alwaysRun = true)
	public void setupResources() {
		LogUtil.debug("Setting up test resources");
		navBar = new NavBar(driver);
		contactUs = new ContactUs(driver);
		basePage = new BasePage(driver);
		LogUtil.debug("Set up successfully");
	}

	@Test(groups = { "smoke" }, priority = 0)
	public void contactUsSubmitTest() {
		
		LogUtil.info("[TEST STARTED]: Verifying contact form submits successfully.");

		driver.get(ConfigManager.getBaseUrl());
		LogUtil.debug("Base URL: " + ConfigManager.getBaseUrl());
		
		LogUtil.info("Navigating to Contact Us page");
		navBar.clickContactUsNav();
		
		Assert.assertEquals(driver.getCurrentUrl(), ConfigManager.getContactUsUrl());
		LogUtil.debug("Directed to Contact Us url successfully.");
		
		LogUtil.info("Filling out Contact Us form.");
		
		Faker faker = new Faker();
	
		String name = faker.name().fullName();
		contactUs.enterName(name);
		LogUtil.debug("Name: " + name);
		
		String email = faker.internet().emailAddress();
		contactUs.enterEmail(email);
		LogUtil.debug("Email: " + email);
		
		String subject = faker.book().title();
		contactUs.enterSubject(subject);
		LogUtil.debug("Subject: " + subject);
		
		String message = faker.lorem().paragraph();
		contactUs.enterMessage(message);
		LogUtil.debug("Message: " + message);
		
		contactUs.uploadFile(System.getProperty("user.dir") + "/" + ConfigManager.getPicsPath() + "icon.png");
		
		LogUtil.info("Submitting form");
		contactUs.clickSubmit();
		
		LogUtil.info("Accepting alert");
		basePage.acceptAlert();
		
		
		Assert.assertTrue(contactUs.isSuccessMessageVisible(), "Success message not visible");
		LogUtil.info("Form submitted successfully.");
		
		LogUtil.info("[TEST COMPLETED]");
		
	}

}
