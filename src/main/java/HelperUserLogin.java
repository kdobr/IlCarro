import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HelperUserLogin extends HelperBase {

    WebElement positiveLoginTitle;
    WebElement negativeLoginTitle;

    By loginButtonSelector = By.xpath("//a[starts-with(@class, 'navigation-link')][normalize-space()='Log in']");
    By emailSelector = By.xpath("//input[@id='email']");
    By passwordSelector = By.xpath("//input[@id='password']");
    By yallahSelector = By.xpath("//button[@type='submit']");
    By positiveLoginTitleSelector = By.xpath("//button[starts-with(@class, 'positive-button')]/../../h1");
    By negativeLoginTitleSelector = By.cssSelector("div.dialog-container>h2");


    public void openLoginForm() throws InterruptedException {
        Thread.sleep(300);
        wd.findElement(loginButtonSelector).click();
    }

    public void fillLoginForm(String email, String password) {
        type(emailSelector, email);
        type(passwordSelector, password);
    }

    public void submitLoginForm() {
        wd.findElement(yallahSelector).click();
    }

    public String findPositiveTitle() {
        positiveLoginTitle = wd.findElement(positiveLoginTitleSelector);
        wait.until(ExpectedConditions.textToBePresentInElement(positiveLoginTitle, "Logged in"));
        return positiveLoginTitle.getText();
    }

    public String findNegativeTitle() {
        negativeLoginTitle = wd.findElement(negativeLoginTitleSelector);
        wait.until(ExpectedConditions.textToBePresentInElement(negativeLoginTitle, "Wrong email or password"));
        return negativeLoginTitle.getText();
    }
}
