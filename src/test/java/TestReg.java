import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestReg extends BasicReg {

    @Test
    public void testRegistration() throws IOException {
        user.openRegForm();
        user.fillRegForm();
        user.submitRegForm();
        String regTitle = user.getRegTabTitle();
        String regText = user.getRegTabText();
        Assert.assertEquals(regTitle, "Registered");
        Assert.assertEquals(regText, "You are logged in success");

    }

}
