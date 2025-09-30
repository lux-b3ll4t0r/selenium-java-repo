package ui.tests.contactus;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.utils.LogUtil;
import ui.constants.UrlConstants;
import ui.pages.contactus.ContactUs;
import ui.pages.homepage.Homepage;
import ui.tests.base.UIBaseTest;
import ui.utils.Webtool;

@Listeners(common.listeners.TestListener.class)
public class ContactUsTest extends UIBaseTest {

	private Homepage homepage;
	private ContactUs contactUs;
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.debug("Setting up class resources.");
		homepage = new Homepage();
		contactUs = new ContactUs();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethods() {
		LogUtil.info("Navigating to: " + UrlConstants.HOMEPAGE);
		Webtool.get(UrlConstants.HOMEPAGE);
	}

	@Test(groups = { "smoke" }, priority = 0)
	public void contact_us_smoke_test() {
		LogUtil.info("* Verifying contact form submits successfully.");
		
		String contactUsUrl = UrlConstants.CONTACT_US;
		
		LogUtil.info("Navigating to: " + contactUsUrl);
		homepage.navigateToContactUs();
		
		Assert.assertEquals(Webtool.getCurrentUrl(), contactUsUrl, "Current url doesn't match the Contact Us url.");
		
		LogUtil.info("Submitting contact form.");
		
		contactUs.submitContactUsForm();
		
		LogUtil.debug("Closing alert.");
		Webtool.acceptAlertSafe();
		
		Assert.assertTrue(contactUs.isSuccessMessageVisible(), "Success message not visible.");
		LogUtil.info("Successfully submitted contact form.");
	}
	
	@Test(groups = { "functional" }, dataProvider = "emails")
	public void contact_incomplete_form_functional_test(String email) {
		LogUtil.info("* Verifying contact form is rejected when email is not valid.");
		
		LogUtil.info("Navigating to Contact Us.");
		homepage.navigateToContactUs();
		
		contactUs.enterEmail(email);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(contactUs.isEmailBrowserErrorMsgVisible(), "Browser error was not displayed.");
		softAssert.assertAll();
		LogUtil.info("Form was rejected successfully.");
	}
	
	@DataProvider(name = "emails")
	public Object[][] emails(){
		return new Object[][] {
			{""},
			{"noatsign"},
			{"nodotcom@"},
			{"nodomain@.com"},
			
		};
	}
}
