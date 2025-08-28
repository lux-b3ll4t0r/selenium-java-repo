package tests.smoke.contactus;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import base.BaseTest;
import constants.UrlConstants;
import pages.contactus.ContactUs;
import pages.homepage.NavBar;
import utils.ConfigManager;
import utils.LogUtil;
import utils.Webtool;

@Listeners(utils.TestListener.class)
public class ContactUsTest extends BaseTest {

	private NavBar navBar;
	private ContactUs contactUs;
	
	@BeforeMethod(alwaysRun = true)
	public void setupResources() {
		LogUtil.debug("Setting up test resources");
		navBar = new NavBar();
		contactUs = new ContactUs();
		LogUtil.debug("Set up successfully");
		
		LogUtil.info("Navigating to: " + UrlConstants.BASE);
		Webtool.get(UrlConstants.BASE);
	}

	@Test(groups = { "smoke" }, priority = 0)
	public void contactus_submit_test() {
		LogUtil.info("* Verifying contact form submits successfully.");
		
		LogUtil.info("Navigating to: " + UrlConstants.CONTACT_US);
		navBar.clickContactUsNav();
		
		Assert.assertEquals(Webtool.getCurrentUrl(), UrlConstants.CONTACT_US);
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
		
		LogUtil.info("Submitting form.");
		contactUs.clickSubmit();
		
		LogUtil.debug("Closing alert.");
		Webtool.acceptAlertSafe();
		
		
		Assert.assertTrue(contactUs.isSuccessMessageVisible(), "Success message not visible");
		LogUtil.info("Success message shown.");
		
	}

}
