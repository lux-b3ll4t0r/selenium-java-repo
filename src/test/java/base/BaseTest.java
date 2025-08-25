package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.DriverFactory;
import utils.ExtentReportManager;
import utils.LogUtil;

public class BaseTest {
	
	private static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
	private static ExtentReports extent;
	protected WebDriver driver;
	protected WebDriverWait wait;
	private DriverFactory driverFactory; // driver factory is private to allow only BaseTest to handle the lifecycle of the driver
	
	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		extent = ExtentReportManager.getReportInstance();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void baseSetUp(Method method) {
		
		// Create a test node in the report
		ExtentTest test = extent.createTest(method.getName());
		
		// Set to ThreadLocal for parallel 
		extentTestThread.set(test);
		
		// Set test in LogUtil so logs go to ExtentReports
		LogUtil.setExtentTest(test);
		
		LogUtil.info("==================== [TEST START]: " + method.getName() + " ====================");
		LogUtil.debug("Initializing Driver");
		driverFactory = new DriverFactory();
		driverFactory.initDriver();
		driver = driverFactory.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		LogUtil.debug("Driver Initialized");

	}
	
	@AfterMethod(alwaysRun = true)
	public void baseTearDown(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		
		LogUtil.debug("Quiting Driver");
		driverFactory.quitDriver();
		LogUtil.debug("Driver Quit");
		
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
	
	@AfterSuite(alwaysRun = true)
	public void tearDownSuite() {
		
		if(extent != null) {
			extent.flush();
		}
	}
	
	public WebDriver getDriver() {
		return driverFactory.getDriver();
	}

}

