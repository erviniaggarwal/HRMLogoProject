package TestCases;

import PageObjects.BasePage;
import PageObjects.LoginPage;
import PageObjects.ResetPasswordPage;
import Utilities.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

@Listeners(CustomListeners.ExtentListener.class)
public class LoginTests extends BaseTest {
    protected static LoginPage loginPg;

    @BeforeClass
    public void loginSetup() {
        loginPg = new LoginPage(driver);
     }
    @DataProvider
    public Object[][] authentication() throws Exception {
        String filePath = System.getProperty("user.dir") + "//src/test//java//Resources//LoginData.xlsx";
        Object[][] tabarray = ExcelUtils.getTableArray(filePath, "Data");
        return tabarray;
    }



    @Test(dataProvider = "authentication")
    public void invalidData(String username, String password) {
        logger.info("Sending Username");
        loginPg.sendUsername(username);
        logger.info("Sending Password");
        loginPg.sendPassword(password);
        logger.info("Clicking login button");
        loginPg.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));
        String alertText = alert.getText();
        Assert.assertEquals("Invalid credentials", alertText);
        logger.info("Test Passed");
    }

    @Test(priority=1)
    public void testAllLinksOnLoginPage() {

        String url = null;
        logger.info("Get All links on login Page");
        List<WebElement> links = loginPg.getAlllinksOnLoginPage();

        logger.info("Get href attributes for links");
        for (WebElement a : links) {
            url = (a.getAttribute("href"));
            logger.info(url);
            try {
                logger.info(testAllLinksAreWorking(new URL(url)));
                Assert.assertTrue(testAllLinksAreWorking(new URL(url)).equals("OK") || testAllLinksAreWorking(new URL(url)).equals("Request denied") || testAllLinksAreWorking(new URL(url)).equals("Moved Permanently"));

            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }



    @Test(priority=3)
    public void validData(){
        logger.info("Sending Username");
        loginPg.sendUsername("Admin");
        logger.info("Sending Password");
        loginPg.sendPassword("admin123");
        logger.info("Clicking login button");
        loginPg.clickLoginButton();
        logger.info(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));
    }


    private String testAllLinksAreWorking(URL url) {
        try {
            HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
            urlconn.connect();
            String responseMessage = urlconn.getResponseMessage();
            urlconn.disconnect();
            return responseMessage;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    protected boolean isUserLoggedin(){
        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));
        return true;
    }
}
