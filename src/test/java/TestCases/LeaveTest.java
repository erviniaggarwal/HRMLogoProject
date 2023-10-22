package TestCases;

import PageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LeaveTest extends BaseTest{
    UserManagementPage usrPg;
    LeavePage leavePg;
    LoginTests loginTests;


    @Override @BeforeClass
    @Parameters({ "browser" })
    @Test
    public void setUp(String browser) {
        usrPg = new UserManagementPage(driver);
        logger.debug("User must be logged in to see/apply leave status");
        loginTests = new LoginTests();
        //if(!loginTests.isUserLoggedin()) {
        //    loginTests.setUp(browser);
        //    loginTests.validData();
        //}
    }


    @Test(priority=2)
    public void testSearchLeaveList(){
        String parentWindowHandle = driver.getWindowHandle();
        usrPg.click_leave_btn();

        // Switch to the new window (assuming only two windows are open)
        logger.info("Switch to Leave list Page");
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        leavePg = new LeavePage(driver);
        //leavePg.clearFromDate(driver);
        leavePg.enterFromDate("2022-07-04");
        //leavePg.clearToDate(driver);
        leavePg.enterToDate("2024-10-26");
        leavePg.selectLeaveType(driver,4);
        leavePg.click_srchBtn();

        try {
            Thread.sleep(10000);
        }catch(Exception e){
            e.getMessage();
        }

        logger.info("Validate the above entered values from table record fetched");
        SoftAssert sa = new SoftAssert();
        System.out.println(leavePg.getFromDate_ToDate());

        sa.assertTrue(leavePg.getFromDate_ToDate().equals("2022-07-28 to 2022-07-29"));
        System.out.println(leavePg.getEmpName());
        sa.assertTrue(leavePg.getEmpName().equals("Anthony Nolan"));
        System.out.println(leavePg.getleaveType());
        sa.assertTrue(leavePg.getleaveType().equals("CAN - Personal"));
        //sa.assertTrue(leavePg.getleaveBalance().equals("99.00"));
        //sa.assertTrue(leavePg.getNoOfDays().equals("1.00"));
        //sa.assertTrue(leavePg.getstatus().equals("Pending approval"));
        sa.assertAll();




    }

}
