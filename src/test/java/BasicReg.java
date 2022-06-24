import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.time.Duration;

public class BasicReg {

    static WebDriver wd;
    static WebDriverWait wait;
    WebElement confirmRegistrationTab;

    static Faker faker;

    By signUpButtonSelector = By.xpath("//a[@class='navigation-link'][normalize-space()='Sign up']");
    By nameSelector = By.xpath("//input[@id='name']");
    By lastNameSelector = By.xpath("//input[@id='lastName']");
    By emailSelector = By.xpath("//input[@id='email']");
    By passwordSelector = By.xpath("//input[@id='password']");
    By iAgreeSelector = By.cssSelector("input#terms-of-use");
    By confirmRegistrationTabSelector = By.cssSelector("div.dialog-container");
    By yallahSelector = By.xpath("//button[@type='submit']");

    @BeforeMethod
    public void start() {
        wd = new ChromeDriver();
        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        faker = new Faker();
    }

    public void fillRegisterForm() throws IOException {
        wd.findElement(signUpButtonSelector).click();
        String name = makeFirstName();
        type(nameSelector, name);
        String lastName = makeLastName();
        type(lastNameSelector, lastName);
        String email = makeEmail("google.com");
        type(emailSelector, email);
        type(passwordSelector, "Aa1aaaaa");
        writeUserToFile(email, "Aa1aaaaa");
        Actions builder = new Actions(wd);
        builder.moveToElement(wd.findElement(iAgreeSelector), 7, 7).click().build().perform();
        wd.findElement(yallahSelector).click();
        confirmRegistrationTab = wd.findElement(confirmRegistrationTabSelector);
        wait.until(ExpectedConditions.visibilityOf(confirmRegistrationTab));
        Assert.assertEquals(confirmRegistrationTab.findElement(By.cssSelector("h1.title")).getText(), "Registered");
        Assert.assertEquals(confirmRegistrationTab.findElement(By.cssSelector("h2.message")).getText(), "You are logged in success");
    }

    public static void type(By selector, String text) {
        WebElement element = wd.findElement(selector);
        element.click();
        element.clear();
        element.sendKeys(text);

    }

    public static String makeFirstName() {
        return faker.name().firstName();
    }

    public static String makeLastName() {
        return faker.name().lastName();
    }

    public static String makeEmail(String domain) {
        StringBuilder emailAddress = new StringBuilder();
        // Generate random email address
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        while (emailAddress.length() < 5) {
            int character = (int) (Math.random() * 26);
            emailAddress.append(alphabet.charAt(character));
        }
        emailAddress.append(Integer.valueOf((int) (Math.random() * 99))
                .toString());
        emailAddress.append("@").append(domain);
        return emailAddress.toString();
    }

    public static void writeUserToFile(String email, String password) throws IOException {
        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter("data.txt", true)))) {
            pr.printf("New user email is: %s and its password is %s\n", email, password);
        }
    }


    @AfterMethod
    public void tearDown() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) wd;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File(".\\screenshots\\error.png"));
        wd.quit();
    }
}
