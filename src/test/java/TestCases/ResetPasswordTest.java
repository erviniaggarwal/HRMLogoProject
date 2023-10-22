package TestCases;

import PageObjects.LoginPage;
import PageObjects.ResetPasswordPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ResetPasswordTest extends BaseTest{
    LoginPage loginPg;

    @BeforeClass
    public void resetPasswordSetup() {
        loginPg = new LoginPage(driver);
    }


    @Test(priority=2)
    public void testForgotPwd(){
        logger.info("click forgot password link");
        loginPg.clickForgotPwd();
        String parentWindowHandle = driver.getWindowHandle();

        // Switch to the new window (assuming only two windows are open)
        logger.info("Switch to Reset password Page");
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        ResetPasswordPage resetPg = new ResetPasswordPage(driver);
        resetPg.sendUsername("Admin");
        resetPg.clickResetPwdButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(@class,'orangehrm-forgot-password-title')]")));
        String alertText = alert.getText();
        Assert.assertEquals("Reset Password link sent successfully", alertText);
        logger.info("Test Passed");


    }

}
