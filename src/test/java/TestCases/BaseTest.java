package TestCases;

import PageObjects.BasePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
        protected static WebDriver driver;

        private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
        String baseurl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        protected static final Logger logger = LogManager.getLogger(LoginTests.class);

        @BeforeClass
        @Parameters({ "browser" })
        public void setUp(String browser) {
            if (browser.equals("chrome")) {
                driver = new ChromeDriver();
            } else if(browser.equals("firefox")){
                driver = new FirefoxDriver();
            }
            driverThread.set(driver);
            BasePage pg = new BasePage(driver);
            logger.error("Loading the HRM Logo Page");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
            driver.get(baseurl);
        }

        @AfterSuite
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

        public static WebDriver getInstance(){
            return driverThread.get();

        }
    }



