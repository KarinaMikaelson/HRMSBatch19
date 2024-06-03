package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CommonMethods extends PageInitializer{
    public static WebDriver driver;
    public static void openBrowserAndLaunchApplication() throws IOException {

//------------------------------------------------------Open Browser Method
        switch (ConfigReader.read("browser")){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "FireFox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("invalid Browser Name");
        }
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(ConfigReader.read("url"));
        initializePageObjects();
    }

    //----------------------------------------------------------Close Browser Method
    public static void closeBrowser() throws IOException {
        if(driver!=null){
            driver.quit();
        }
    }
    //----------------------------------------------------------Select From Dropdown Method
    public static void selectFromDropdown(WebElement dropdown, int index){
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }
    public static void selectFromDropdown(WebElement dropdown, String visibleText){
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }
    public static void selectFromDropdown(String value,WebElement dropdown){
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }
    //----------------------------------------------------------Send Text Common Method  (Class-11)
    public static void sendText(String text, WebElement element){
        //clear the text box
        element.clear();
        //send text to the element
        element.sendKeys(text);
    }
    //----------------------------------------------------------Explicit wait Common Method
    public static WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        return wait;
    }
    //----------------------------------------------------------Wait for Element to be Clickable Common Method
    public static void waitForElementToBeClickable(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    //-----------------------------------------------------------Click the Element Common Method
    public static void click(WebElement element){
        waitForElementToBeClickable(element);
        element.click();
    }
    //----------------------------------------------------------- Take Screenshot Common Method
    public static void takeScreenshot(WebDriver driver, String filePath) throws IOException {
        TakesScreenshot screenshotTaker = (TakesScreenshot)driver;
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        FileHandler.copy(srcFile,destFile);
    }
//-----------------------------------------------------------Click Checkbox Method

//-----------------------------------------------------------Radiobutton Method
//-----------------------------------------------------------js click Method
}
