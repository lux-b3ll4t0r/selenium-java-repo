package api.tests.base;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentTest;

import common.base.BaseTest;
import common.utils.LogUtil;

public class APIBaseTest extends BaseTest{
	
	protected ThreadLocal<ExtentTest> classTest = new ThreadLocal<>();
	
	@BeforeClass(alwaysRun = true)
	public void setupUIBaseClass(ITestContext context) {
		classTest.set(BaseTest.getTestTagNode(context.getCurrentXmlTest().getName()).createNode(this.getClass().getSimpleName()));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupApiBaseMethods(Method method) {
		
		// Create a test node in the report
		ExtentTest test = classTest.get().createNode(method.getName());
		
		// Set test in LogUtil so logs go to ExtentReports
		LogUtil.setExtentTest(test);	
		
		LogUtil.info("==================== [TEST START] ====================");
		LogUtil.info("[TEST]: " + method.getName());
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardownApiBaseMethods(ITestResult result) {
		LogUtil.logTestResult(result);
		LogUtil.info("==================== [TEST END] ====================");
	}
	

	

}

