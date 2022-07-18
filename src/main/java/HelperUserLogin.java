import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Optional;

public class HelperUserLogin extends HelperBase {

    WebElement positiveLoginTitle;
    WebElement negativeLoginTitle;
    WebElement okToCloseAuthorizeErrorTab;

    By loginButtonSelector = By.xpath("//a[starts-with(@class, 'navigation-link')][normalize-space()='Log in']");
    By emailSelector = By.xpath("//input[@id='email']");
    By passwordSelector = By.xpath("//input[@id='password']");
    By positiveLoginTitleSelector = By.xpath("//button[starts-with(@class, 'positive-button')]/../../h1");

    By negativeLoginTitleSelector = By.cssSelector("div.dialog-container>h2");
    By closeAuthorizeErrorTabSelector = By.xpath("//button[normalize-space()='Ok']");


    public void openLoginForm() throws InterruptedException {
        Thread.sleep(300);
        wd.findElement(loginButtonSelector).click();
    }

    public void fillLoginForm(String email, String password) {
        type(emailSelector, email);
        type(passwordSelector, password);
    }

    public void fillLoginForm(User user) {
        type(emailSelector, user.getEmail());
        type(passwordSelector, user.getPassword());
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

    public boolean isAlertDisplayed() {
        Optional<Alert> alert = Optional.of(wait.until(ExpectedConditions.alertIsPresent()));
        return alert.isPresent();
    }

    public void closeAuthorizeErrorTab(){
        okToCloseAuthorizeErrorTab = wd.findElement(closeAuthorizeErrorTabSelector);
        okToCloseAuthorizeErrorTab.click();
       // wait.until(ExpectedConditions.stalenessOf(okToCloseAuthorizeErrorTab));
        wd.findElement(By.cssSelector("div.header img[alt='logo']")).click();

    }
}
