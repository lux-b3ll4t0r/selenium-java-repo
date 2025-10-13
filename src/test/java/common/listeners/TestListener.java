package common.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.IConfigurationListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import common.utils.LogUtil;
import common.utils.ScreenshotUtils;
import ui.utils.DriverFactory;

public class TestListener implements ITestListener, IConfigurationListener{

	
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = DriverFactory.getDriver();
		
		String base64Path = ScreenshotUtils.getBase64Screenshot(driver);
		LogUtil.attachScreenshot(result, base64Path);
		
	}
	
	@Override
	public void onConfigurationFailure(ITestResult result) {
		WebDriver driver = DriverFactory.getDriver();
		
		String base64Path = ScreenshotUtils.getBase64Screenshot(driver);
		LogUtil.attachScreenshot(result, base64Path);
		
	}
}
