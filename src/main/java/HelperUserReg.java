import models.User;
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


    public void openRegForm() {
        wd.findElement(signUpButtonSelector).click();
    }

//    public void fillRegForm() throws IOException {
//        String name = generateFirstName();
//        type(nameSelector, name);
//        String lastName = generateLastName();
//        type(lastNameSelector, lastName);
//        String email = generateEmail("google.com");
//        type(emailSelector, email);
//        type(passwordSelector, "Aa1aaaaa");
//        writeUserToFile(email, "Aa1aaaaa");
//    }

    public void fillRegForm(User user)  {
        type(nameSelector, user.getName());
        type(lastNameSelector, user.getLastName());
        type(emailSelector, user.getEmail());
        type(passwordSelector, user.getPassword());
    }

    public void iAgreeCheckBox() {
        Actions action = new Actions(wd);
        action.moveToElement(wd.findElement(iAgreeSelector), 7, 7).click().build().perform();

    }

    public void submitRegForm() {
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

    public void closePositiveTabAndLogout() {
        wd.findElement(By.xpath("//button[normalize-space()='Ok']")).click();
        wd.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
        wd.findElement(By.cssSelector("div.header img[alt='logo']")).click();

    }

    public void goHome() {
        wd.findElement(By.cssSelector("div.header img[alt='logo']")).click();

    }

    public boolean asserFailPasswordFormat() {
        WebElement lowNumberText = wd.findElement(By.xpath("//div[normalize-space()='Password must contain minimum 8 symbols']"));
        WebElement wrongFormatText = wd.findElement(By.xpath("//div[normalize-space()='Password must contain 1 uppercase letter, 1 lowercase letter and one number']"));
        WebElement yallahButton = wd.findElement(By.cssSelector("button[disabled][type='submit']"));
        return lowNumberText != null && wrongFormatText != null && yallahButton != null;
    }

    public void saveUserToFile(User user) throws IOException {
        user.saveUserToFile();
    }


}
