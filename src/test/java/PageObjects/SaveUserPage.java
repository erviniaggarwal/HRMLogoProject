package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SaveUserPage extends UserManagementPage {



    public SaveUserPage(WebDriver driver) {
        super(driver);
    }

    //@FindBy(xpath= "(//input[contains(@class,'oxd-input oxd-input--active') and @autocomplete='off'])[1]")
     //       WebElement  userName;

    @FindBy(xpath= "(//input[contains(@class,'oxd-input oxd-input--active') and @type='password'])[1]")
    WebElement  password;

    @FindBy(xpath= "//form/div[2]/div/div[2]/div/div[2]/input")
    WebElement  confirmPassword;

    @FindBy(xpath= "//form/div[3]/button[2]")
            WebElement save_Btn;

    public void enterUsername(String userName){
        this.userName.sendKeys(signedOnName.getText());
    }

    public void enterPassword(String password ){
        this.password.sendKeys(password);
    }

    public void enterConfirmPassword(String password, WebDriver driver){
        this.confirmPassword.click();
        this.confirmPassword.sendKeys(password);
    }

    public void clickSaveBtn(WebDriver driver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, " + 500 + ");");
        jsExecutor.executeScript("arguments[0].click();", save_Btn);
        try {
            Thread.sleep(10000); // Pause for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }










}
