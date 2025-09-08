package base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentTest;
import utils.LogUtil;

public class APIBaseTest extends BaseTest{
	
	
	@BeforeMethod(alwaysRun = true)
	public void setupApiBaseMethods(Method method) {
		
		// Create a test node in the report
		ExtentTest test = extent.createTest(method.getName());
		
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

