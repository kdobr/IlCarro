import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase {

    WebElement cityElement;
    WebElement dateElement;



    public void searchCar(String city, String from, String to) throws InterruptedException {
        typeCity(city);
        typeDate(from, to);
        Thread.sleep(1000);
    }

    private void typeCity(String city) throws InterruptedException {
        cityElement = wd.findElement(By.id("city"));
        clickAndClear(cityElement);
        cityElement.sendKeys(city);
        new Actions(wd).moveToElement(wd.findElement(By.id("city"))).moveByOffset(0, 50).click().perform();
        Thread.sleep(500);
    }

    private void typeDate(String from, String to)  {
        dateElement = wd.findElement(By.id("dates"));
        dateElement.click();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        LocalDate fromDate = LocalDate.parse(from, formatter);
        LocalDate toDate = LocalDate.parse(to, formatter);
        int diffYear;
        int diffMonth;
        diffMonth = (fromDate.getYear()-currentDate.getYear()==0)?
                fromDate.getMonthValue() - currentDate.getMonthValue():
                (12-currentDate.getMonthValue())+fromDate.getMonthValue();
        for (int i = 0; i < diffMonth; i++) {
            wd.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        }
        wd.findElement(By.xpath("//div[text()=' " + fromDate.getDayOfMonth() + " ']")).click();
        diffYear = toDate.getYear() - fromDate.getYear();
        diffMonth = diffYear==0?
                toDate.getMonthValue() - fromDate.getMonthValue():
                (12-fromDate.getMonthValue())+toDate.getMonthValue();

        for (int i = 0; i < diffMonth; i++) {
            wd.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        }
        wd.findElement(By.xpath("//div[text()=' " + toDate.getDayOfMonth() + " ']")).click();
    }

    public boolean isCarListPresent(){
        return isElementPresent(By.cssSelector(".cars-container.ng-star-inserted"));
    }

    public boolean ifPositiveResultAppered() {
        String url = wd.getCurrentUrl();
        System.out.println(url);
        return url.contains("results");
    }

    public void searchCarInThePast(String city, String from, String to) throws InterruptedException {
                typeCity(city);
        dateElement = wd.findElement(By.id("dates"));
        dateElement.sendKeys(from+" - "+to);
    }

    public boolean earlyPickUpProhibitedText(){
        return wd.findElement(By.xpath("//div[@class='ng-star-inserted']")).getText().contains("You can't pick date before today");
    }

}
