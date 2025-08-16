package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	private static ExtentReports extent;
	
	public static ExtentReports getReportInstance() {
		
		if(extent == null) {
			
			// Create timestamp with simple date format to append to report path
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			
			// Set output path for report file appending timestamp to avoid overwriting file
			String reportPath = System.getProperty("user.dir") + "/test-output/extent-reports/ExtentReport_" + timestamp + ".html";
			
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
