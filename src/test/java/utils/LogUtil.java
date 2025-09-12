package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class LogUtil {
	
	private static final Logger logger = LogManager.getLogger(LogUtil.class);
	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	public static void setExtentTest(ExtentTest test) {
		extentTest.set(test);
	}
	
	
	public static void logTestResult(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		
		if(result.getStatus() == ITestResult.FAILURE) {
			Throwable throwable = result.getThrowable();
			LogUtil.fail("[TEST FAILED]: " + testName + " ", throwable);
		}else if(result.getStatus() == ITestResult.SKIP) {
			LogUtil.info("[WARN]: " + testName);
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			LogUtil.pass("[TEST PASS]: " + testName);
		}
	}
	
	public static void removeExtentTest() {
		extentTest.remove();
	}
	
	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}
	
	public static void info(String message) {
		logger.info(message);
		ExtentTest test = extentTest.get();
		
		if(test != null) test.info(message);
	}
	
	public static void debug(String message) {
		logger.debug(message);
	
	}
	
	public static void debug(String message, Object placeHolder1, Object placeHolder2) {
		logger.debug(message, placeHolder1, placeHolder2);
	}
	
	public static void pass(String message) {
		logger.info(message);
		ExtentTest test = extentTest.get();
		
		if(test != null) test.pass(message);
	}
	
	public static void fail(String message, Throwable t) {
		logger.error(message + ": [STACK TRACE]: " + t.getMessage());
		ExtentTest test = extentTest.get();
		
		if(test != null) test.fail(message + t.getMessage());
	}
	
	public static void warn(String message, Throwable t) {
		logger.warn(message + ": [STACK TRACE]: " + t.getMessage());
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	
	public static void trace(String message) {
		logger.trace(message);
	}
	
	public static void error(String message, Throwable t) {
		logger.error(message, t);
		ExtentTest test = extentTest.get();
		
		if(test != null) test.fail(message + ": " + t.getMessage());
	}
	
	public static void error(String message) {
		logger.error(message);
		ExtentTest test = extentTest.get();
		
		if(test != null) test.fail(message);
	}
	
	public static void attachScreenshot(ITestResult result, String base64) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			ExtentTest test = extentTest.get();
			
			if(test != null) test.fail("Failed, see screenshot" , MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
		}
	}
}
