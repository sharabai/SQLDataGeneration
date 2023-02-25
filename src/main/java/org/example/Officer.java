package org.example;

import com.github.javafaker.Faker;
import org.example.RandomValues.Rank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.example.Util.YEARS_DIFF;

public class Officer implements Bulk{
    static int currentlyAvailableID = 1;
    String firstName;
    String lastName;
    int ID;
    String address;
    String phone;
    String rank;
    Date birthday;
    Date employmentDate;

    String notesFromCoworkers;
    List<Vacation> vacations = new ArrayList<>();

    public Officer(Boolean t2) {
        Faker faker = new Faker();

        firstName = faker.name().firstName(); // Emory
        lastName = faker.name().lastName(); // Barton
        ID = currentlyAvailableID++;
        address = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
        phone = Util.generatePhoneNumber();
        rank = Rank.get();
        if(!t2) {
            birthday = faker.date().birthday(21 + YEARS_DIFF, 65 + YEARS_DIFF);
            employmentDate = faker.date().birthday(0 + YEARS_DIFF, 15 + YEARS_DIFF);
        }else{
            birthday = faker.date().birthday(21, 65);
            employmentDate = faker.date().birthday(0, YEARS_DIFF);
        }
        notesFromCoworkers = faker.lorem().paragraph();
    }

    @Override
    public String getBulkLine(){
        String result;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        result = Util.stitch(String.valueOf(ID), f.format(birthday),firstName,lastName,
                address, phone, f.format(employmentDate), notesFromCoworkers, rank);
        return result;
    }

    boolean canWork(Date date){
        if (date.before(employmentDate))
            return false;
        for(var d:vacations){
            if (!(date.before(d.dateFrom) || date.after(d.dateTo)))
                return false;
        }
        return true;
    }
}
