package ui.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		String nameOfOS = System.getProperty("os.name");
		String versionOfOS = System.getProperty("os.version");
		String architectureOfOS = System.getProperty("os.arch");

		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pirimid");
		extent.setSystemInfo("OS",nameOfOS);
		extent.setSystemInfo("Version",versionOfOS);
		extent.setSystemInfo("Arch", architectureOfOS);

		return extent;
		
	}
}
