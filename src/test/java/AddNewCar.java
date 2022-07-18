import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utls.Retry;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AddNewCar extends BasicTest {

    @BeforeMethod
    public void preCondition() throws InterruptedException {
        if(!userCar.isUsedLogged()){
            userCar.login(new User().setEmail("ggztb2@google.com").setPassword("Aa1aaaaa"));
       }

    }

    @Test (retryAnalyzer = Retry.class)
    public void addNewCarSuccess() throws InterruptedException {

        Integer number = ThreadLocalRandom.current().nextInt(100, 1000 + 1);
        Car car = Car.builder()
                .address("Haifa, Israel")
                .producer("BMW")
                .model("5")
                .year("2021")
                .engine("3.0")
                .fuel("Petrol")
                .gear("AT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                .regNumber(number.toString())
                .price("200")
                .distanceIncluded("800")
                .features("child seat")
                .about("no smoke")
                .build();
        userCar.openCarForm();
        userCar.fillCarForm(car);
        userCar.addPhoto("C:\\SeleniumQA34\\IlCarro\\auto1.jpeg");
        userCar.submit();
        // Thread.sleep(1000);
        String positiveTitle = userCar.findPositiveCarAddTitle();
        Assert.assertEquals(positiveTitle, "Car added");
        userCar.returnHome();

    }
}
