package org.example.RandomValues;

import java.util.concurrent.ThreadLocalRandom;

public class Rank {
    static String[] ranks = {"Police Officer", "Senior Police Officer", "Sergeant", "Inspector", "Chief Inspector"};

    public static String get() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, ranks.length);
        return ranks[randomNum];
    }
}
