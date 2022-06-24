import org.testng.annotations.Test;

import java.io.IOException;

public class TestRegistration extends BasicReg{

    @Test
    public void testRegistration() throws IOException {
        fillRegisterForm();
    }

}
