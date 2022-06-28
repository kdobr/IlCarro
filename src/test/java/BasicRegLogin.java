import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BasicRegLogin {

    protected static HelperBase userBase = new ApplicationManager().getUserBase();
    protected static HelperUserLogin userLogin = new ApplicationManager().getUserLogin();
    protected static HelperUserReg userReg = new ApplicationManager().getUserReg();

    @BeforeSuite
    public void start() {
        userBase.startUp();
    }


    @AfterSuite
    public void tearDown() throws IOException {
        userBase.tearDown();
    }
}
