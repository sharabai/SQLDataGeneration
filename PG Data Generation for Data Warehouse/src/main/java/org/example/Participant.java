package org.example;

import com.github.javafaker.Faker;
import org.example.RandomValues.InvolvementType;

import java.util.concurrent.ThreadLocalRandom;

public class Participant {
    String name;
    String surname;
    int age;
    String sex;
    String race;
    String citizenId;
    String involvementType;


    public Participant() {
        Faker faker = new Faker();
        name = faker.name().firstName(); // Emory
        surname = faker.name().lastName(); // Barton
        age = ThreadLocalRandom.current().nextInt(16, 56);
        if (ThreadLocalRandom.current().nextInt(0, 31)% 2 == 0)
            sex = "M";
        else
            sex = "F";
        var tmp = ThreadLocalRandom.current().nextInt(0, 31)% 3;
        if (tmp == 0)
            race = "White";
        else if (tmp == 1)
            race = "Black";
        else
            race = "Asian";
        citizenId = faker.idNumber().valid();
        involvementType = InvolvementType.get();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getInvolvementType() {
        return involvementType;
    }

    public void setInvolvementType(String involvementType) {
        this.involvementType = involvementType;
    }
}
