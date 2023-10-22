package TestCases;

import PageObjects.LoginPage;
import PageObjects.ResetPasswordPage;
import PageObjects.SaveUserPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import PageObjects.UserManagementPage;

public class UserManagementTest extends BaseTest{
    UserManagementPage usrPg;
    SaveUserPage saveusrPg;
    LoginTests loginTests;

    String username = "Admin";
    @Override @BeforeClass
    @Parameters({ "browser" })
    @Test
    public void setUp(String browser) {
        usrPg = new UserManagementPage(driver);
        saveusrPg= new SaveUserPage(driver);
        loginTests = new LoginTests();
        //if(!loginTests.isUserLoggedin()) {
        //    loginTests.setUp(browser);
        //    loginTests.validData();
        //}
        usrPg.clickAdmin_btn();

    }

    @Test
    public boolean testSearchBtnFunctionality(String username){
        boolean b = false;
        logger.info("Send Username");
        if (username != null) {
            usrPg.sendUsername(username);
        }else{
            usrPg.sendUsername(this.username);
        }
        logger.info("Selecting User role Admin");
        usrPg.selectDrpDown(driver);
        logger.info("Semding partial text and selecting");
        usrPg.sendEmpName(driver);
        logger.info("Select status to enabled");
        usrPg.selectStatus(driver);
        logger.info("Click search button");
        usrPg.clickSearch_btn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(9000));
        WebElement alert;
        try {
            alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(., '(1) Record Found')]")));
        } catch(Exception e){
            b = false;
            return b;
        }
        Assert.assertNotNull(alert);
        b = true;
        return b;


    }


    @Test
    public void testResetBtnFunctionality(){
        logger.info("Click Reset");
        usrPg.clickResetBtn();
        logger.info("Validate all fields got empty");
        usrPg.validateAllFieldsareEmpty();
    }

    @Test
    public void testAddBtnFunctionality(){
        logger.info("Click Add Button");
        String parentHandle = driver.getWindowHandle();
        usrPg.clickAddBtn();
        String parentWindowHandle = driver.getWindowHandle();

        // Switch to the new window (assuming only two windows are open)
        logger.info("Switch to Add UserPage");
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        saveusrPg.selectDrpDown(driver);
        saveusrPg.sendEmpName(driver);
        saveusrPg.selectStatus(driver);

        saveusrPg.sendUsername("vinita42");
        try {
            Thread.sleep(10000); // Pause for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveusrPg.enterPassword("admin123");
        saveusrPg.enterConfirmPassword("admin123",driver);
        try {
            Thread.sleep(10000); // Pause for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveusrPg.clickSaveBtn(driver);
        logger.info("User added above should appear in search");
        Assert.assertTrue(testSearchBtnFunctionality("vinita42"));
    }

    @AfterClass
    public void delete_AddedUser() {
        logger.info("Select the record that needs to be deleted found from above search record");
        usrPg.click_selChkBox();
        logger.info("click the delete button on the selected checkbox");
        usrPg.click_del_button();
        usrPg.click_final_delete_btn();
        logger.info("Validate that record got deleted by performing search again- it shouldn't come in search again");
        Assert.assertFalse(testSearchBtnFunctionality("vinita42"));
    }
}
