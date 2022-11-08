package org.example;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Complaint implements Bulk{
    static int currentlyAvailableID = 1;
    int ID;
    Officer officer;
    String description;
    Date date;

    public Complaint(List<Bulk> officers, Boolean t2) {
        ID = currentlyAvailableID++;

        Faker faker = new Faker();
        description = faker.lorem().paragraph();
        int randomNum = ThreadLocalRandom.current().nextInt(0, officers.size());
        officer = Officer.class.cast(officers.get(randomNum));
        if(!t2)
        date = faker.date().between(officer.employmentDate, new GregorianCalendar(Util.YEAR_T1
                , Util.MONTH_T1 -1, Util.DAY_T1).getTime());
        else {
            Date start = new GregorianCalendar(Util.YEAR_T1
                    , Util.MONTH_T1 - 1, Util.DAY_T1).getTime();
            if(start.before(officer.employmentDate))
                start = officer.employmentDate;
            date = faker.date().between(start, new GregorianCalendar(Util.YEAR_T2
                    , Util.MONTH_T2 - 1, Util.DAY_T2).getTime());
        }
    }

    @Override
    public String getBulkLine(){
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        String result = Util.stitch(String.valueOf(ID),String.valueOf(officer.ID)
                ,description,f.format(date));
        return result;
    }
}
