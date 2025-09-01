package base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.LogUtil;
import utils.Webtool;

public class BaseTest {

	private static ExtentReports extent;
	
	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		extent = ExtentReportManager.getReportInstance();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Setting up resources");
		DriverFactory.setupDriver();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupMethod(Method method) {
		
		// Create a test node in the report
		ExtentTest test = extent.createTest(method.getName());
		
		// Set test in LogUtil so logs go to ExtentReports
		LogUtil.setExtentTest(test);	
		
		Webtool.clearCookies();
		
		// Uncomment this line to create a new driver instance after each test
		// Must also uncomment the quit method in AfterMethod, or handle driver quit manually
			//LogUtil.trace("Initializing Driver");
			//DriverFactory.setupDriver();
			//LogUtil.trace("Driver Initialized");
		
		LogUtil.info("==================== [TEST START] ====================");
		LogUtil.info("[TEST]: " + method.getName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void baseTearDown(ITestResult result) {
		Webtool.clearStorage();	
		LogUtil.logTestResult(result);
		
		// Only uncomment this if driver is being setup in BeforeMethod
			//LogUtil.debug("Quiting Driver");
			//DriverFactory.quitDriver();
			//LogUtil.debug("Driver Quit");
		
		LogUtil.info("==================== [TEST END] ====================");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClassTearDown() {
		DriverFactory.quitDriver();
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() {
		LogUtil.trace("Tearing down all resources.");
		try {
			extent.flush();
			LogUtil.removeExtentTest();
		}catch(Exception e) {
			LogUtil.warn("Failed to flush extent report: " + e.getMessage());
		}
		
	}

}

