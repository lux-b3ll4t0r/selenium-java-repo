package ui.tests.base;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import com.aventstack.extentreports.ExtentTest;

import common.base.BaseTest;
import common.utils.LogUtil;
import ui.utils.DriverFactory;
import ui.utils.Webtool;

public class UIBaseTest extends BaseTest{

	protected ThreadLocal<ExtentTest> classTest = new ThreadLocal<>();	
	
	@BeforeClass(alwaysRun = true)
	public void setupUIBaseClass(ITestContext context) {
		LogUtil.trace("Setting up resources");
		DriverFactory.setupDriver();
		classTest.set(BaseTest.getTestTagNode(context.getCurrentXmlTest().getName()).createNode(this.getClass().getSimpleName()));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupUIBaseMethods(Method method) {
		ExtentTest methodNode = classTest.get().createNode(method.getName());
		
		// Set test in LogUtil so logs go to ExtentReports
		LogUtil.setExtentTest(methodNode);	
		
		// Uncomment this line to create a new driver instance after each test
		// Must also uncomment the quit method in AfterMethod, or handle driver quit manually
//			LogUtil.trace("Initializing Driver");
//			DriverFactory.setupDriver();
//			LogUtil.trace("Driver Initialized");
		
		Webtool.clearCookies();
		LogUtil.info("==================== [TEST START] ====================");
		LogUtil.info("[TEST]: " + method.getName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardownUIBaseMethods(ITestResult result) {
		Webtool.clearStorage();	
		LogUtil.logTestResult(result);

		// Only uncomment this if driver is being setup in BeforeMethod
			//DriverFactory.quitDriver();
		
		LogUtil.info("==================== [TEST END] ====================");
	}
	
	@AfterClass(alwaysRun = true)
	public void teardownUIBaseClasses() {
		LogUtil.trace("Tearing down class");
		DriverFactory.quitDriver();
	}
	

}

