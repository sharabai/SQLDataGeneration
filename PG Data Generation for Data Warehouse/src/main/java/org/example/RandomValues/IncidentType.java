package org.example.RandomValues;

import java.util.concurrent.ThreadLocalRandom;

public class IncidentType {
    static String[] incidents ={ "assault and battery", "arson", "child abuse", "domestic abuse", "kidnapping",
            "rape and statutory rape", "burglary", "larceny", "robbery", "auto theft", "shoplifting", "public Intoxication", "first-degree murder",
            "voluntary manslaughter", "vehicular homicide"};

    public static String get(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, incidents.length);
        return incidents[randomNum];
    };
}
