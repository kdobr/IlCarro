import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BasicLogin {

    protected static HelperUserLogin user = new ApplicationManager().getUserLogin();

    @BeforeSuite
    public void start() {
        user.startUp();
    }


    @AfterSuite
    public void tearDown() throws IOException {
        user.tearDown();
    }
}
