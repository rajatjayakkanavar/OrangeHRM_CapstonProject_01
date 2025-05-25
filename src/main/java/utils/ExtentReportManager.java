package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/LoginTestReport.html");
            reporter.config().setReportName("Orange HRM Login Test Report");
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
