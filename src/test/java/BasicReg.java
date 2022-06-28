import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BasicReg {

    protected static HelperUserReg user = new ApplicationManager().getUserReg();

    @BeforeSuite
    public void start() {
        user.startUp();
    }


    @AfterSuite
    public void tearDown() throws IOException {
        user.tearDown();
    }
}
