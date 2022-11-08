package org.example;

import com.github.javafaker.Faker;
import org.example.RandomValues.District;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Patrol implements Bulk {
    Officer officer1 = null;
    Officer officer2 = null;
    Date startDate;
    String district;
    Date endDate;

    public Patrol(List<Bulk> officers, Boolean t2) {
        district = District.get();
        Faker faker = new Faker();
        if (!t2)
        startDate = faker.date().between(new GregorianCalendar(Util.YEAR_START
                , Util.MONTH_START -1, Util.DAY_START).getTime(), new GregorianCalendar(Util.YEAR_T1
                , Util.MONTH_T1 -1, Util.DAY_T1).getTime());
        else
            startDate = faker.date().between(new GregorianCalendar(Util.YEAR_T1
                    , Util.MONTH_T1 -1, Util.DAY_T1).getTime(), new GregorianCalendar(Util.YEAR_T2
                    , Util.MONTH_T2 -1, Util.DAY_T2).getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.HOUR_OF_DAY, ThreadLocalRandom.current().nextInt(3, 7));
        endDate = calendar.getTime();
        getOfficers((List<Officer>)(Object)officers);
    }

    public void getOfficers(List<Officer> officers){
        while(officer1 == null){
            int randomNum = ThreadLocalRandom.current().nextInt(0, officers.size());
            if(officers.get(randomNum).canWork(startDate) &&
                    officers.get(randomNum).canWork(endDate))
                officer1 = officers.get(randomNum);
        }
        while(officer2 == null){
            int randomNum = ThreadLocalRandom.current().nextInt(0, officers.size());
            if(officers.get(randomNum).canWork(startDate) &&
                    officers.get(randomNum).canWork(endDate) && officer1 != officers.get(randomNum))
                officer2 = officers.get(randomNum);
        }
    }

    @Override
    public String getBulkLine() {
        String result;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        result = Util.stitch(String.valueOf(officer1.ID), f.format(startDate),
                district, f.format(endDate), "\r\n", String.valueOf(officer2.ID), f.format(startDate),
                district, f.format(endDate));
        return result;
    }
}
