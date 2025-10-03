package common.utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	private static ExtentReports extent;
	
	public static ExtentReports getReportInstance() {
		
		if(extent == null) {
			
			// Set output path for report file
			String reportPath = ConfigManager.getFullReportPath();
			
			// Set up spark reporter
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("Automation Exercise Report");
			
			// Create and configure ExtentReports instance
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
			
		}
		
		return extent;
	}
}
