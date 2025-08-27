package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{

	
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = DriverFactory.getDriver();
		
		String testName = result.getName();
		ScreenshotUtils.takeScreenshot(driver, testName);
		
		
	}
}
