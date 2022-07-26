import com.github.javafaker.Faker;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utls.MyDataProvider;

import java.io.IOException;

public class TestReg extends BasicTest {

    @Test(dataProvider = "registerWithFaker", dataProviderClass = MyDataProvider.class)
    public void testRegistrationRandome(User user) throws IOException {
        userReg.openRegForm();
        userReg.fillRegForm(user);
        userReg.iAgreeCheckBox();
        userReg.submitRegForm();
        assertAndClose();
        userReg.saveUserToFile(user);
    }

    @Test
    public void testRegistrationLiz() {
        int i = (int) System.currentTimeMillis() / 100;
        User user = new User().setName("Liz").setLastName("Snow").setEmail("fox" + i + "@gmail.com").setPassword("Ff12345$");
        userReg.openRegForm();
        userReg.fillRegForm(user);
        userReg.iAgreeCheckBox();
        userReg.submitRegForm();
        assertAndClose();
    }

    @Test
    public void testRegistrationFailWithWrongPassFormat() {
        User user = new User(new Faker());
        user.setPassword("Zoa");
        userReg.openRegForm();
        userReg.fillRegForm(user);
        userReg.iAgreeCheckBox();
        Assert.assertTrue( userReg.asserFailPasswordFormat());
        userReg.goHome();
    }


    private void assertAndClose() {
        String regTitle = userReg.getRegTabTitle();
        String regText = userReg.getRegTabText();
        Assert.assertEquals(regTitle, "Registered");
        Assert.assertEquals(regText, "You are logged in success");
        userReg.closePositiveTabAndLogout();
    }
}
