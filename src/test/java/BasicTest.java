import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BasicTest {

    //protected static HelperBase userBase = new ApplicationManager().getUserBase();
    static ApplicationManager am = new ApplicationManager();
    protected static HelperUserLogin userLogin = am.getUserLogin();
    protected static HelperUserReg userReg = am.getUserReg();
    protected static HelperUserCar userCar = am.getUserCar();
    protected static HelperSearch carSearch = am.getSearch();

    @BeforeSuite
    public void start() {
        userReg.startUp();
    }


    @AfterSuite
    public void tearDown() throws IOException {
        userReg.tearDown();
    }


}
