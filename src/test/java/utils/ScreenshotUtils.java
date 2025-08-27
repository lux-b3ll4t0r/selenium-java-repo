package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {
	
	public static String takeScreenshot(WebDriver driver, String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = testName + "_" + timestamp + ".png";
		String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName;

		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(screenshotPath));
		}catch(Exception e) {
			LogUtil.warn("Failed to take a screenshot: " + e.getMessage());
		}
		
		return screenshotPath;
	}
}
