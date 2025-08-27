package base;

import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.BasePage;
import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.LogUtil;

public class BaseTest {
	
	private static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
	private static ExtentReports extent;

	
	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		extent = ExtentReportManager.getReportInstance();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		LogUtil.trace("Initializing Driver");
		DriverFactory.setupDriver();
		LogUtil.trace("Driver Initialized");
		BasePage.safeSetup();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void baseSetUp(Method method) {
		
		// Create a test node in the report
		ExtentTest test = extent.createTest(method.getName());
		
		// Set to ThreadLocal for parallel 
		extentTestThread.set(test);
		
		// Set test in LogUtil so logs go to ExtentReports
		LogUtil.setExtentTest(test);
		
		BasePage.clearCookies();
		((JavascriptExecutor) BasePage.getDriver()).executeScript("window.localStorage.clear();");
		((JavascriptExecutor) BasePage.getDriver()).executeScript("window.sessionStorage.clear();");
		
		LogUtil.info("==================== [TEST START]: " + method.getName() + " ====================");
	}
	
	@AfterMethod(alwaysRun = true)
	public void baseTearDown(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		
		if(result.getStatus() == ITestResult.FAILURE) {
			Throwable throwable = result.getThrowable();
			LogUtil.fail("[TEST FAILED]: " + testName, throwable);
		}else if(result.getStatus() == ITestResult.SKIP) {
			LogUtil.info("[WARN]: " + testName);
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			LogUtil.pass("[TEST PASS]: " + testName);
		}
		
		LogUtil.info("==================== [TEST END] ====================");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClassTearDown() {
		LogUtil.debug("Quiting Driver");
		DriverFactory.quitDriver();
		LogUtil.debug("Driver Quit");
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() {
		
		if(extent != null) {
			extent.flush();
		}
	}
	
	public WebDriver getDriver() {
		return DriverFactory.getDriver();
	}

}

