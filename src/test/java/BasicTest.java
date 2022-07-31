import org.slf4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class BasicTest {

    static ApplicationManager am = new ApplicationManager();
    protected static HelperBase base = am.getUserBase();
    protected static HelperUserLogin userLogin = am.getUserLogin();
    protected static HelperUserReg userReg = am.getUserReg();
    protected static HelperUserCar userCar = am.getUserCar();
    protected static HelperSearch carSearch = am.getSearch();
    public static Logger logger;

    @BeforeSuite (alwaysRun = true)
    public void start() {
        base.startUp();
        logger = base.logger;
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method method) {
        logger.info("Start execute Test: " + method.getName());
    }


    @AfterSuite (alwaysRun = true)
    public void tearDown() throws IOException {
        base.tearDown();
    }
}
