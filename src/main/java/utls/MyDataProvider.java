package utls;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.javafaker.Faker;
import models.Car;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyDataProvider {

    @DataProvider
    public static Object[][] loginWithsimpleUsers() {
        return new Object[][]
                {
                        {"ggztb2@google.com", "Aa1aaaaa"},
                        {"kmmhc54@gmail.com", "Aa1aaaaa"}
                };
    }

    @DataProvider
    public static Iterator<Object[]> registerWithFaker() {
        List<Object[]> list = new ArrayList<>();
         Faker faker = new Faker();
        list.add(new Object[]{new User(faker)});
        list.add(new Object[]{new User(faker)});
        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> loginFromCSV()  {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/users.csv"))) {
            String[] strArr;
            String str, email, password;
            // reading credentials from file
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                email = strArr[0];
                password = strArr[1];
                User user = new User(email, password);
                list.add(new Object[]{user});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> addCarFromXML() throws IOException {
        XmlMapper mapper = new XmlMapper();
        List<Object[]> carList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Car car = mapper.readValue(new File("src/test/resources/carSample.xml"), Car.class);
            int number = ThreadLocalRandom.current().nextInt(100, 10000 + 1);
            car.setRegNumber(Integer.toString(number));
            carList.add(new Object[]{car});
        }
        return carList.iterator();
    }
}
