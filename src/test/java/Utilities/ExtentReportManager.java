package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    public static ExtentReports getInstance(){
           if (extent==null){
               extent = new ExtentReports();
               ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReports/ExtentReport.html");
               extent.setSystemInfo("Project Name", "HRMLogoProject");
               extent.setSystemInfo("Organisation", "HRM");
               extent.attachReporter(sparkReporter);

           }
        return extent;
    }
}
