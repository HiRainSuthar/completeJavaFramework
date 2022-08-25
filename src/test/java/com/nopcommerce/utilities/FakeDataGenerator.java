package com.nopcommerce.utilities;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    static Faker faker = new Faker();

    String firstName;
    String lastName;
    String email;
    String companyName;

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getCompanyName() {
        return faker.company().name();
    }
}
