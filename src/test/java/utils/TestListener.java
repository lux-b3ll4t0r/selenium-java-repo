package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener{

	
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = DriverFactory.getDriver();
		
		String base64Path = ScreenshotUtils.getBase64Screenshot(driver);
		LogUtil.attachScreenshot(result, base64Path);
		
	}
}
