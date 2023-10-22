package CustomListeners;

import TestCases.BaseTest;
import Utilities.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ExtentListener implements ITestListener {

    ExtentReports exRep = ExtentReportManager.getInstance();
    private ThreadLocal<ExtentTest> extentTest=ThreadLocal.withInitial(() -> null);

    @Override
    public void onTestStart(ITestResult result) {
        extentTest.set(exRep.createTest(result.getName()));
        extentTest.get().log(Status.INFO,result.getName() + "started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.INFO,result.getName() + "succeeded");

    }

    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getInstance();
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/screenshot.png";
        try {
            // Copy the screenshot to the destination path
            FileUtils.copyFile(screenshot, new File(destination));
            System.out.println("Screenshot saved to: " + destination);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        exRep.flush();
    }
}
