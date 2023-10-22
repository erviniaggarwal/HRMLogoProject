package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LeavePage extends UserManagementPage {



    public LeavePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath= "(//input[contains(@class,'oxd-input--active')])[2]")
    WebElement  fromDate;

    @FindBy(xpath= "(//div[contains(@class,'oxd-date-input')]//input)[2]")
            WebElement  toDate;

    @FindBy(xpath= "(//i[contains(@class,'oxd-select-text--arrow')])[2]")
    WebElement  select_leaveType;

    @FindBy(xpath= "(//div[@role='row'])[2]//div[2]//div")
            WebElement fromDate_ToDate;

    @FindBy(xpath= "(//div[@role='row'])[2]//div[3]//div")
            WebElement EmpName;

    @FindBy(xpath= "(//div[@role='row'])[2]//div[4]//div")
            WebElement leaveType;

    @FindBy(xpath= "(//div[@role='row'])[2]//div[5]//div")
    WebElement leaveBalance;

    @FindBy(xpath= "(//div[@role='row'])[2]//div[6]//div")
    WebElement NoOfDays;

    //@FindBy(xpath= "(//div[@role='row'])[2]//div[7]//div]")
    WebElement status;

    @FindBy(xpath="//button[@type='submit']")
    WebElement srch_btn;

    public void enterFromDate(String fromDate){
        this.fromDate.sendKeys(fromDate);
        //this.fromDate.sendKeys(Keys.ENTER);
    }

    public void enterToDate(String toDate){
        this.toDate.sendKeys(toDate);
        this.toDate.sendKeys(Keys.ENTER);
    }

    public void selectLeaveType(WebDriver driver, int index){
        select_leaveType.click();
        try {
            Thread.sleep(10000);
        }catch(Exception e){
            e.getMessage();
        }
        Actions actions = new Actions(driver);
        // Simulate a single "keydown" action (press a key down)
        for(int i=0 ; i< index ;i++){
            actions.sendKeys(Keys.ARROW_DOWN);
        }

        try {
            Thread.sleep(10000);
        }catch(Exception e){
            e.getMessage();
        }
        // Simulate pressing the "enter" key
        actions.sendKeys(Keys.ENTER);
        // Perform the sequence of actions
        actions.build().perform();
    }


    public String getFromDate_ToDate(){
        return fromDate_ToDate.getText();
    }

    public String getSignedOnName(){
        return signedOnName.getText();
    }

    public String getEmpName(){
        return EmpName.getText();
    }

    public String getleaveType(){
        return leaveType.getText();
    }

    public String getleaveBalance(){
        return leaveBalance.getText();
    }

    public String getNoOfDays(){
        return NoOfDays.getText();
    }

    public String getstatus(){
        return status.getText();
    }

    public void clearFromDate(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.elementToBeClickable(fromDate));
        fromDate.clear();
        //
        //try {
        //     fromDate.click();
        //    ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", fromDate);
        //}catch(Exception e){
        //    e.getMessage();
        //}
    }

    public void clearToDate(WebDriver driver){
        toDate.click();
        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '';", toDate);
    }

    public void click_srchBtn(){
        srch_btn.click();
    }












}
