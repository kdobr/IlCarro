import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class HelperUserReg extends HelperBase {

    WebElement confirmRegistrationTab;

    By signUpButtonSelector = By.xpath("//a[@class='navigation-link'][normalize-space()='Sign up']");
    By nameSelector = By.xpath("//input[@id='name']");
    By lastNameSelector = By.xpath("//input[@id='lastName']");
    By emailSelector = By.xpath("//input[@id='email']");
    By passwordSelector = By.xpath("//input[@id='password']");
    By iAgreeSelector = By.cssSelector("input#terms-of-use");
    By confirmRegistrationTabSelector = By.cssSelector("div.dialog-container");
    By yallahSelector = By.xpath("//button[@type='submit']");

    public void openRegForm() {
        wd.findElement(signUpButtonSelector).click();
    }

    public void fillRegForm() throws IOException {
        String name = generateFirstName();
        type(nameSelector, name);
        String lastName = generateLastName();
        type(lastNameSelector, lastName);
        String email = generateEmail("google.com");
        type(emailSelector, email);
        type(passwordSelector, "Aa1aaaaa");
        writeUserToFile(email, "Aa1aaaaa");
    }

    public void submitRegForm() {
        Actions builder = new Actions(wd);
        builder.moveToElement(wd.findElement(iAgreeSelector), 7, 7).click().build().perform();
        wd.findElement(yallahSelector).click();
    }

    public String getRegTabTitle() {
        confirmRegistrationTab = wd.findElement(confirmRegistrationTabSelector);
        wait.until(ExpectedConditions.visibilityOf(confirmRegistrationTab));
        return confirmRegistrationTab.findElement(By.cssSelector("h1.title")).getText();
    }

    public String getRegTabText() {
        confirmRegistrationTab = wd.findElement(confirmRegistrationTabSelector);
        wait.until(ExpectedConditions.visibilityOf(confirmRegistrationTab));
        return confirmRegistrationTab.findElement(By.cssSelector("h2.message")).getText();
    }
}
