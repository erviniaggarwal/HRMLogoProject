package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ResetPasswordPage extends BasePage {

    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@name='username']")
    WebElement username;

    @FindBy(xpath = "//button[contains(@class,orangehrm-forgot-password-button--reset) and @type='submit']")
    WebElement forgotPwdSubmitBtn;


    public void sendUsername(String Username){

        username.sendKeys(Username);
    }
    public void clickResetPwdButton(){

        forgotPwdSubmitBtn.click();
    }


}
