package common.base;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import common.utils.ExtentReportManager;
import common.utils.LogUtil;


public class BaseTest {

	protected static ExtentReports extent;
	protected static Map<String, ExtentTest> testTagNodes = new ConcurrentHashMap<>();
	
	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		extent = ExtentReportManager.getReportInstance();
	}

	@BeforeTest(alwaysRun = true)
	public void beforeXmlTest(ITestContext context) {
		String testName = context.getCurrentXmlTest().getName();
		testTagNodes.put(testName, extent.createTest(testName));
	}
	
	@AfterSuite(alwaysRun = true)
	public void teardownBaseSuite() {
		LogUtil.trace("Tearing down suite.");
		try {
			extent.flush();
			LogUtil.removeExtentTest();
		}catch(Exception e) {
			LogUtil.warn("Failed to flush extent report: " + e.getMessage());
		}
		
	}
	
	public static ExtentTest getTestTagNode(String testTagName) {
		return testTagNodes.get(testTagName);
	}
}

