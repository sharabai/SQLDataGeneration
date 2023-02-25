package org.example;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class Dispatcher implements Bulk {
    static int currentlyAvailableID = 1;
    String firstName;
    String lastName;
    String phone;
    String address;
    int ID;

    public Dispatcher() {
        Faker faker = new Faker();
        firstName = faker.name().firstName(); // Emory
        lastName = faker.name().lastName(); // Barton
        ID = currentlyAvailableID++;
        address = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
        phone = Util.generatePhoneNumber();
    }

    @Override
    public String getBulkLine(){
        String result = Util.stitch(String.valueOf(ID),firstName,
                lastName, address, phone);
        return result;
    }
}
