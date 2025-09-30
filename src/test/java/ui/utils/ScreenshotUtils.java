package ui.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import common.utils.LogUtil;

public class ScreenshotUtils {
	
	public static String takeScreenshot(WebDriver driver, String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = testName + "_" + timestamp + ".png";
		String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName;

		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(screenshotPath));
		}catch(Exception e) {
			LogUtil.error("Failed to take a screenshot: ", e);
		}
		
		return screenshotPath;
	}
	
	public static String getBase64Screenshot(WebDriver driver) {
		
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
			return base64Screenshot;
			
		}catch(Exception e) {
			LogUtil.error("Failed to take screenshot: ", e);
		}
		
		return null;
	}
}
