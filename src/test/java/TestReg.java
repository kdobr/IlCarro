import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestReg extends BasicRegLogin {

    @Test
    public void testRegistration() throws IOException {
        userReg.openRegForm();
        userReg.fillRegForm();
        userReg.submitRegForm();
        String regTitle = userReg.getRegTabTitle();
        String regText = userReg.getRegTabText();
        Assert.assertEquals(regTitle, "Registered");
        Assert.assertEquals(regText, "You are logged in success");
    }
}
