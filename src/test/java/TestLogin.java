import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestLogin extends BasicLogin {

// method with one permanent user

//    @Test
//    public void positiveLogin() throws IOException, InterruptedException {
//        fillAndSubmitLoginForm("ggztb2@google.com", "Aa1aaaaa");
//        Assert.assertEquals(positiveLoginTitle.getText(), "Logged in");
//    }

    // method reads list of users from file
    @Test
    public void positiveLogin() throws IOException, InterruptedException {
        File file = new File(
                "data.txt");

        try (BufferedReader br
                     = new BufferedReader(new FileReader(file))) {

            String[] strArr;
            String str;
            String email;
            String password;

            //reading all strings
            while ((str = br.readLine()) != null) {
                //split whole string to words by symol " "
                strArr = str.split(" ");
                email = strArr[4];
                password = strArr[9];
                fillAndSubmitLoginForm(email, password);
                Assert.assertEquals(positiveLoginTitle.getText(), "Logged in");
                wd.findElement(By.cssSelector("button.positive-button")).click();
                wd.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

            }
        }

    }

    @Test
    public void negativeLogin() throws InterruptedException {
        fillAndSubmitLoginForm("ggztb@google.com", "Aa1aaaaa");
        Assert.assertEquals(negativeLoginTitle.getText(), "Wrong email or password");
    }

}
