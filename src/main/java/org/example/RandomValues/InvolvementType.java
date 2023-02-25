package org.example.RandomValues;

import java.util.concurrent.ThreadLocalRandom;

public class InvolvementType {
    static String[] involvementType = {"witness", "suspect", "perpetrator", "person of interest",
    "possible suspect", "possible person of interest", "culprit", "respondent"};

    public static String get(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, involvementType.length);
        return involvementType[randomNum];
    };
}
