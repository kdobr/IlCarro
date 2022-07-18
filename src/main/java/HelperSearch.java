import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase {

    WebElement cityElement;
    WebElement dateElement;


    public void searchCurrentMonth(String city, String from, String to) throws InterruptedException {
        typeCity(city);
        typeDate(from, to);
        Thread.sleep(1000);
    }

    private void typeCity(String city) {
        cityElement = wd.findElement(By.id("city"));
        clickAndClear(cityElement);
        cityElement.sendKeys(city);
        new Actions(wd).moveToElement(wd.findElement(By.id("city"))).moveByOffset(0, 50).click().perform();
    }

    private void typeDate(String fromData, String toData)  {
        dateElement = wd.findElement(By.id("dates"));
        dateElement.click();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        LocalDate from = LocalDate.parse(fromData, formatter);
        LocalDate to = LocalDate.parse(toData, formatter);
        int currentMonth = currentDate.getMonthValue();
        int monthStart = from.getMonthValue();
        int monthReturn = to.getMonthValue();
        //find difference between current month and start rent month
        int startDiff = monthStart - currentMonth;
        //find difference between start and return months
        int rentMonthDiff = monthReturn - monthStart;
        //set correct start rent month
        for (int i = 0; i < startDiff; i++) {
            wd.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        }
        wd.findElement(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']")).click();
        //set correct return month
        for (int i = 0; i < rentMonthDiff; i++) {
            wd.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        }
        wd.findElement(By.xpath("//div[text()=' " + to.getDayOfMonth() + " ']")).click();
    }
}
