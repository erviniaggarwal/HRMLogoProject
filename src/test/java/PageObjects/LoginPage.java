package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;

    @FindBy(xpath = "//a")
    List<WebElement> anchorLinks;

    @FindBy(xpath = "//img")
    List<WebElement> imgLinks;

    @FindBy(xpath="//p[contains(@class,'orangehrm-login-forgot-header')]")
    WebElement forgotpwdlink;


    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void sendUsername(String Username){

        username.sendKeys(Username);
    }

    public void sendPassword(String Password){

        password.sendKeys(Password);
    }

    public void clickLoginButton(){

        loginBtn.click();
    }

    public void clickForgotPwd(){
        forgotpwdlink.click();
    }

    public List getAlllinksOnLoginPage() {
        List<WebElement> linkstoclick = new ArrayList();
        linkstoclick.addAll(anchorLinks);
        linkstoclick.addAll(imgLinks);
        return linkstoclick;
    }



}
