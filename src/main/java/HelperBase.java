import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;

public class HelperBase {

    static WebDriver wd;
    static WebDriverWait wait;
    static Faker faker;


    public void startUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro-1578153671498.web.app/search");
        wd.manage().window().maximize();
        wait = new WebDriverWait(wd, Duration.ofSeconds(5));
        faker = new Faker();
    }


    public void tearDown() throws IOException {
        //making screenshot before browser closure
        TakesScreenshot ts = (TakesScreenshot) wd;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File(".\\screenshots\\error.png"));
        //closing browser
        wd.quit();
    }

    public static void type(By selector, String text) {
        WebElement element = wd.findElement(selector);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void logout() {
        wd.findElement(By.cssSelector("button.positive-button")).click();
        wd.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
    }

    //Generate random first name
    public static String generateFirstName() {
        return faker.name().firstName();
    }
    //Generate random last name
    public static String generateLastName() {
        return faker.name().lastName();
    }

    // Generate random email address
    public static String generateEmail(String domain) {
        StringBuilder emailAddress = new StringBuilder();
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
    // write new user to file
    public static void writeUserToFile(String email, String password) throws IOException {
        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter("data.txt", true)))) {
            pr.printf("New user email is: %s and its password is %s\n", email, password);
        }
    }

}
