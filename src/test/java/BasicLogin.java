import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasicLogin {

    static WebDriver wd;
    static WebDriverWait wait;
    WebElement positiveLoginTitle;
    WebElement negativeLoginTitle;


    //By loginButtonSelector = By.xpath("//a[@class='navigation-link'][normalize-space()='Log in']");
    By loginButtonSelector = By.xpath("//a[starts-with(@class, 'navigation-link')][normalize-space()='Log in']");
    By emailSelector = By.xpath("//input[@id='email']");
    By passwordSelector = By.xpath("//input[@id='password']");
    By yallahSelector = By.xpath("//button[@type='submit']");
    By positiveLoginTitleSelector = By.xpath("//button[starts-with(@class, 'positive-button')]/../../h1");
    By negativeLoginTitleSelector = By.cssSelector("div.dialog-container>h2");

    @BeforeMethod
    public void start() {
        wd = new ChromeDriver();
        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(wd, Duration.ofSeconds(5));
    }

    public void fillAndSubmitLoginForm(String email, String password) throws InterruptedException {
        Thread.sleep(1000);
        wd.findElement(loginButtonSelector).click();

        type(emailSelector, email);
        type(passwordSelector, password);
        wd.findElement(yallahSelector).click();
        positiveLoginTitle = wd.findElement(positiveLoginTitleSelector);
        negativeLoginTitle = wd.findElement(negativeLoginTitleSelector);
    }

    public static void type(By selector, String text) {
        WebElement element = wd.findElement(selector);
        element.click();
        element.clear();
        element.sendKeys(text);

    }

    @AfterMethod
    public void tearDown() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) wd;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File(".\\screenshots\\error.png"));
        wd.quit();
    }
}
