package org.example.RandomValues;

import java.util.concurrent.ThreadLocalRandom;

public class Incidents {
    public static int get(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 101);
        if(randomNum<30)
            return 0;
        else if (randomNum<50)
            return 1;
        else if (randomNum< 70)
            return 2;
        else if (randomNum< 90)
            return 3;
        else
            return 4;
    }
}
