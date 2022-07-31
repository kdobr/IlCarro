import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utls.MyDataProvider;

import java.util.concurrent.ThreadLocalRandom;

public class AddNewCar extends BasicTest {

    @BeforeMethod (alwaysRun = true)
    public void preCondition() throws InterruptedException {
        if (!userCar.isUserLogged()) {
            userCar.login(new User().setEmail("ggztb2@google.com").setPassword("Aa1aaaaa"));
        }
    }

    @Test(groups = {"web", "smoke", "regress"})
    public void addNewCarSuccess() throws InterruptedException {

        int number = ThreadLocalRandom.current().nextInt(100, 10000 + 1);
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
                .regNumber(Integer.toString(number))
                .price("200")
                .distanceIncluded("800")
                .features("child seat")
                .about("no smoke")
                .build();
        userCar.openCarForm();
        userCar.fillCarForm(car);
        userCar.addPhoto("C:\\SeleniumQA34\\IlCarro\\auto1.jpeg");
        userCar.submit();

        String positiveTitle = userCar.findPositiveCarAddTitle();
        Assert.assertEquals(positiveTitle, "Car added");
    }

    @Test(dataProvider = "addCarFromXML", dataProviderClass = MyDataProvider.class)
    public void adNewCarSuccessFromXML(Car car) throws InterruptedException {
        userCar.openCarForm();
        userCar.fillCarForm(car);
        userCar.addPhoto("C:\\SeleniumQA34\\IlCarro\\auto1.jpeg");
        userCar.submit();
        String positiveTitle = userCar.findPositiveCarAddTitle();
        Assert.assertEquals(positiveTitle, "Car added");
    }

    @AfterMethod(alwaysRun = true)
    public void goHome() {
        userCar.returnHome();
    }
}
