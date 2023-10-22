package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePage {
    public BasePage(WebDriver driver){

        PageFactory.initElements(new AjaxElementLocatorFactory(driver,50 ),this);
    }


}
