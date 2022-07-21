import com.github.javafaker.Faker;
import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Duration;

public class HelperBase {

    static WebDriver wd;
    static WebDriverWait wait;
    static Faker faker;
    WebDriverListener listener;
    Logger logger;
    WebElement dateElement;

    By yallahSelector = By.xpath("//button[@type='submit']");
    By positiveCarAddTitleSelector = By.cssSelector("[class='dialog-container'] h1");


    public void startUp() {
        logger = LoggerFactory.getLogger(HelperBase.class);
        listener = new MyListener();
        wd = new EventFiringDecorator(listener).decorate(new ChromeDriver());
        //wd = new EventFiringDecorator(listener).decorate(new FirefoxDriver());
        logger.info("all Tests start in ChromeDriver");
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        wd.manage().window().maximize();
        logger.info("=========Current URL is: "+wd.getCurrentUrl());
        wait = new WebDriverWait(wd, Duration.ofSeconds(10));
        faker = new Faker();


    }


    public void tearDown() throws IOException {
        //making screenshot before browser closure
        takeScreenShots();
        //closing browser
        wd.quit();
    }

    private void takeScreenShots() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) wd;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File(".\\screenshots\\error"+System.currentTimeMillis()/100000+".png"));
    }

    public static void type(By selector, String text) {
        WebElement element = wd.findElement(selector);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public boolean isUsedLogged(){
        return wd.findElements(By.xpath("//a[normalize-space()='Logout']")).size() >= 1;
    }

    public void logout() {
        wd.findElement(By.cssSelector("button.positive-button")).click();
        wd.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
    }
    public void submit() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(yallahSelector)).click();
      //  wd.findElement(yallahSelector).click();
        Thread.sleep(2000);
    }




    public boolean isElementPresent(By selector){
        return wd.findElements(selector).size()>=1;
    }

    public String findPositiveCarAddTitle() {
        WebElement positiveCarAddTitle = wd.findElement(positiveCarAddTitleSelector);
        wait.until(ExpectedConditions.textToBePresentInElement(positiveCarAddTitle, "Car added"));
        return positiveCarAddTitle.getText();
    }

    public void clickAndClear(WebElement element){

        element.click();
        element.clear();
    }

    public void newSearch(){
        //hit "Search" link
        wd.findElement(By.id("0")).click();
        //click inside date field
        dateElement = wd.findElement(By.id("dates"));
        dateElement.click();
        //closing calendar appeared by clicking escape
        dateElement.sendKeys(Keys.ESCAPE);
        //taking OS name
        String os = System.getProperty("os.name");
        //depending on OS delete text in date field by enter appropriate hotkeys combination
        if (os.startsWith("Windows")){
             dateElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }else{
            //need to add correct names of other OS and their hotkeys
            dateElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        }


    }

//    public boolean isLogged(){
//        return isElementPresent(By.xpath("//a[normalize-space()='Logout']"));
//    }
//    public boolean isLogged(){
//        return isElementPresent(By.xpath("//a[normalize-space()='Logout']"));
//    }
}
