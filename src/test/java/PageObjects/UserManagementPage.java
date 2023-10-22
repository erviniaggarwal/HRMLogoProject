package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class UserManagementPage extends BasePage{
    public UserManagementPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="(//input[contains(@class,\'oxd-input--active\')])[2]")
    WebElement userName;

    @FindBy(xpath ="(//i[contains(@class,'oxd-icon bi-caret-down-fill')])[2]")
    WebElement select_drpdown;

    @FindBy(xpath="//input[@placeholder='Type for hints...']")
    WebElement empName;

    @FindBy(xpath ="(//i[contains(@class,'oxd-icon bi-caret-down-fill')])[3]")
    WebElement select_drpdown2;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement search_Btn;

    @FindBy(xpath ="//a[@href='/web/index.php/admin/viewAdminModule']")
    WebElement admin_home;

    @FindBy(xpath ="//div[text()='Admin']")
    WebElement admin_option;

    @FindBy(xpath ="//div[text()='Enabled']")
    WebElement enabled_option;

    @FindBy(xpath ="//button[contains(@class,'oxd-button--ghost')]")
    WebElement reset_btn;

    @FindBy(xpath ="//button[contains(@class,'oxd-button--secondary') and @type='button']")
    WebElement Add_btn;

    @FindBy(xpath ="//p[contains(@class,'oxd-userdropdown-name')]")
    WebElement signedOnName;

    @FindBy(xpath ="(//i[contains(@class,'oxd-checkbox-input-icon')])[1]")
    WebElement sel_Checkbox;

    @FindBy(xpath ="(//i[contains(@class,'oxd-icon bi-trash')])[1]")
    WebElement del_button;

    @FindBy(xpath ="(//button[contains(@class,'oxd-button--label-danger')])[2]")
    WebElement final_delete_btn;

    @FindBy(xpath ="//a[@href='/web/index.php/leave/viewLeaveModule']")
    WebElement leave_btn;


    public void sendUsername(String Username){
        userName.sendKeys(Username);
    }

    public void selectDrpDown(WebDriver driver){
        select_drpdown.click();
        //admin_option.click();
        Actions actions = new Actions(driver);
        // Simulate a single "keydown" action (press a key down)
        actions.sendKeys(Keys.ARROW_DOWN);
        // Simulate pressing the "enter" key
        actions.sendKeys(Keys.ENTER);
        // Perform the sequence of actions
        actions.build().perform();
    }

    public void sendEmpName(WebDriver driver){
        empName.sendKeys(signedOnName.getText());
        Actions action = new Actions(driver);
        try {
            Thread.sleep(5000); // Pause for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wait for the search results to become visible
        action.sendKeys(Keys.ARROW_DOWN);
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }

    public void selectStatus(WebDriver driver){
        select_drpdown2.click();
        Actions actions = new Actions(driver);
        // Simulate a single "keydown" action (press a key down)
        actions.sendKeys(Keys.ARROW_DOWN);
        // Simulate pressing the "enter" key
        actions.sendKeys(Keys.ENTER);
        // Perform the sequence of actions
        actions.build().perform();
    }

    public void clickSearch_btn(){
        search_Btn.click();
    }

    public void clickAdmin_btn(){
        admin_home.click();
    }

    public void click_leave_btn(){
        leave_btn.click();
    }

    public void clickResetBtn(){
        reset_btn.click();
    }

    public void clickAddBtn(){
        Add_btn.click();
    }


    public void click_selChkBox(){
        sel_Checkbox.click();
    }

    public void click_final_delete_btn(){
         final_delete_btn.click();
    }
    public void click_del_button(){
        del_button.click();
    }



    public void validateAllFieldsareEmpty(){
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(userName.getText().isEmpty());
        sa.assertTrue(select_drpdown.getText().isEmpty());
        sa.assertTrue(empName.getText().isEmpty());
        sa.assertTrue(select_drpdown2.getText().isEmpty());
        sa.assertAll();

    }

}
