package models;

import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class User {

    private String name, lastName, email, password, phone;

    public User() {
    }

    public User(Faker faker) {
        this.name = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = generateEmail("gmail.com");
        this.password = "Aa1aaaaa";
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "models.User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    // Generate random email address
    public static String generateEmail(String domain) {
        StringBuilder emailAddress = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        while (emailAddress.length() < 5) {
            int character = (int) (Math.random() * 26);
            emailAddress.append(alphabet.charAt(character));
        }
        emailAddress.append(Integer.valueOf((int) (Math.random() * 99))
                .toString());
        emailAddress.append("@").append(domain);
        return emailAddress.toString();
    }

    // write new user to file
    public void saveUserToFile() throws IOException {
        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter("data.txt", true)))) {
            pr.printf("New user email is: %s and its password is %s\n", this.email, this.password);
        }
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
