package utils;

import io.cucumber.java.et.Ja;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer{
    public static WebDriver driver;
    public void openBrowserAndLaunchApplication() throws IOException {

        switch (ConfigReader.read("browser")){
            case "Chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver=new ChromeDriver(options);
                break;
            case "FireFox":
                driver=new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");
        }
        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(ConfigReader.read("url"));
        initializePageObjects();
    }

    //----------------------------------------------------------Close Browser Method
    public static void closeBrowser() {
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
    public static byte[] takeScreenshot(String fileName){

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        //it is not going to take another screenshot, instead it will consider picByte
        //i.e array of byte as a source file for transfer
        File sourceFile = ts.getScreenshotAs(OutputType.FILE); // sourceFile - transfers file internally

        try {
            FileUtils.copyFile(sourceFile, new File
                    (Constants.SCREENSHOT_FILEPATH+fileName+
                            " "+ getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png")); //array of byte
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;  // returning array of byte
    }

    public static String getTimeStamp(String pattern){


        Date date = new Date();
        //yyyy-MM-dd-hh-mm-ss
        //dd-MM-yyyy-mm-hh-ss
        //to get the date in my acceptable format, i need to format it
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(date);
    }
    //----------------------------------------------------------- JS click operation
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();",element);
    }


    /*
    public static void takeScreenshot(WebDriver driver, String filePath) throws IOException {
    TakesScreenshot screenshotTaker = (TakesScreenshot)driver;
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        FileHandler.copy(srcFile,destFile);
    }
     */

//-----------------------------------------------------------Click Checkbox Method

//-----------------------------------------------------------Radiobutton Method
//-----------------------------------------------------------js click Method
}
