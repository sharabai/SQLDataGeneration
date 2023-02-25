package org.example.RandomValues;

import java.util.concurrent.ThreadLocalRandom;

public class District {
    static final String[] districts = {"Chusairmec Acre", "Fort Wofeag", "Cunk Market",
            "Letlep Circle", "Zeppussaink South", "Cloggaict South", "West Sappus", "Dub Cross",
            "Sphainig Hills", "Creollehird Corner", "Lower Roosseapt", "Stirrag Market", "Yeoheoct Valley",
            "Wigel Town", "Camurbob Park", "Deoffemlug Side", "North Qutmamp", "Upper North Toopt",
            "Smaleep Valley", "North Whank", "Smeffotluld Place", "Upper South Twirard", "Lower South Hearosk",
            "Waterside Calp", "Shreorrolziand West", "Upper South Fohep", "Lower West Gafim",
            "Stresop Circle", "Petnoc Corner", "Skeeseawoalp Valley", "Zemunift Avenue",
            "Splessursuft Side", "Upper North Coasios", "Briffeckil Road", "Crud Side",
            "Lunnesteen Town", "Upper North Threhap", "Zelp Bazaar", "Qek Heights",
            "Lower West Hessoan", "Downtown Whugluld", "Beark Yard", "Slettairk Plaza",
            "Upper West Batteest", "Sneahasp District", "Upper East Drim", "Little Scrap",
            "Skesenwaft Center"};

    public static String get() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, districts.length);
        return districts[randomNum];
    }
}
