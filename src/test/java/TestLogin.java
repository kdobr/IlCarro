import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestLogin extends BasicLogin {


    @Test
    public void correctLoginOneUser() throws InterruptedException {
        user.openLoginForm();
        user.fillLoginForm("ggztb2@google.com", "Aa1aaaaa");
        user.submitLoginForm();
        String positiveTitle = user.findPositiveTitle();
        Assert.assertEquals(positiveTitle, "Logged in");
        user.logout();
    }

    @Test
    public void correctLoginUsersFromFile() throws IOException, InterruptedException {
        File file = new File("data.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String[] strArr;
            String str, email, password;

            // reading credentials from file
            while ((str = br.readLine()) != null) {
                //split whole string to words by symol " "
                strArr = str.split(" ");
                email = strArr[4];
                password = strArr[9];
                user.openLoginForm();
                user.fillLoginForm(email, password);
                user.submitLoginForm();
                String positiveTitle = user.findPositiveTitle();
                Assert.assertEquals(positiveTitle, "Logged in");
                user.logout();
            }
        }
    }

    @Test
    public void incorrectLoginUserIncorrect() throws InterruptedException {
        user.openLoginForm();
        user.fillLoginForm("ggztb@google.com", "Aa1aaaaa");
        user.submitLoginForm();
        String negativeTitle = user.findNegativeTitle();
        Assert.assertEquals(negativeTitle, "Wrong email or password");
    }
}
