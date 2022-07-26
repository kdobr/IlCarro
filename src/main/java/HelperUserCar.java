import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HelperUserCar extends HelperBase {


    public void openCarForm() throws InterruptedException {
        Thread.sleep(1000);
        wd.findElement(By.id("1")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[normalize-space()='Let the car work']") ));
    }

    public void addPhoto(String link) {
        wd.findElement(By.id("photos")).sendKeys(link);
    }

    public void fillCarForm(Car car) {
        typeAddress(car.getAddress());
        type(By.id("make"), car.getProducer());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        type(By.id("engine"), car.getEngine());
        select(By.id("fuel"), car.getFuel());
        select(By.id("gear"), car.getGear());
        select(By.id("wheelsDrive"), car.getWD());
        type(By.id("doors"), car.getDoors());
        type(By.id("seats"), car.getSeats());
        type(By.id("class"), car.getClasS());
        type(By.id("fuelConsumption"), car.getFuelConsumption());
        type(By.id("serialNumber"), car.getRegNumber());
        type(By.id("price"), car.getPrice());
        type(By.id("distance"), car.getDistanceIncluded());
        type(By.cssSelector("input[placeholder='Type feature']"), car.getFeatures());
        type(By.id("about"), car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
    }

    private void typeAddress(String address) {
        wd.findElement(By.id("pickUpPlace")).sendKeys(address);
        new Actions(wd).moveToElement(wd.findElement(By.id("pickUpPlace"))).moveByOffset(0, 50).click().perform();
    }

    public void login(User user) throws InterruptedException {
        HelperUserLogin userLogin = new HelperUserLogin();
        userLogin.openLoginForm();
        //userLogin.fillLoginForm("ggztb2@google.com", "Aa1aaaaa");
        userLogin.fillLoginForm(user);
        userLogin.submit();
        wd.findElement(By.cssSelector("button.positive-button")).click();
    }

    public void returnHome() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search cars']"))).click();
    }
}
