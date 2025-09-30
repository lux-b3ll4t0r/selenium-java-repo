package common.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;

import common.utils.ExtentReportManager;
import common.utils.LogUtil;


public class BaseTest {

	protected static ExtentReports extent;
	
	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		extent = ExtentReportManager.getReportInstance();
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

}

