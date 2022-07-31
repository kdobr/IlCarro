import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utls.MyDataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestLogin extends BasicTest {

    @BeforeClass (alwaysRun = true)
    public void goToMain() {
        if (userLogin.isUserLogged())
            userLogin.firstLogout();
    }

    @Test(dataProvider = "loginWithsimpleUsers", dataProviderClass = MyDataProvider.class)
    public void correctLoginFromSimpleProvider(String email, String password) throws InterruptedException {
        userLogin.openLoginForm();
        userLogin.fillLoginForm(email, password);
        logger.info("login with mail: " + email + " and password: " + password);
        userLogin.submit();
        String positiveTitle = userLogin.findPositiveTitle();
        Assert.assertEquals(positiveTitle, "Logged in");
        userLogin.logout();
    }

    @Test(groups = {"web"})
    public void correctLoginUsersFromFile() throws IOException, InterruptedException {
        File file = new File("data.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[] strArr;
            String str, email, password;
            while ((str = br.readLine()) != null) {
                strArr = str.split(" ");
                email = strArr[4];
                password = strArr[9];
                userLogin.openLoginForm();
                userLogin.fillLoginForm(email, password);
                logger.info("login with email: " + email + ", and password: " + password);
                userLogin.submit();
                String positiveTitle = userLogin.findPositiveTitle();
                Assert.assertEquals(positiveTitle, "Logged in");
                userLogin.logout();
            }
        }
    }

    @Test(dataProvider = "loginFromCSV", dataProviderClass = MyDataProvider.class)
    public void correctLoginUsersFromCSVFile(User user) throws InterruptedException {
        String email = user.getEmail();
        String password = user.getPassword();
        userLogin.openLoginForm();
        userLogin.fillLoginForm(email, password);
        logger.info("login with email: " + email + ", and password: " + password);
        userLogin.submit();
        String positiveTitle = userLogin.findPositiveTitle();
        Assert.assertEquals(positiveTitle, "Logged in");
        userLogin.logout();
    }

    @Test
    public void incorrectLoginUser() throws InterruptedException {
        userLogin.openLoginForm();
        userLogin.fillLoginForm("ggztb@google.com", "Aa1aaaaa");
        logger.info("login with wrong mail 'ggztb@google.com' and password 'Aa1aaaaa'");
        userLogin.submit();
        String negativeTitle = userLogin.findNegativeTitle();
        Assert.assertEquals(negativeTitle, "Wrong email or password");
        userLogin.closeAuthorizeErrorTab();
    }
}
