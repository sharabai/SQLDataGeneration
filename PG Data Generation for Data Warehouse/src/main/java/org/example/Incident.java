package org.example;

import org.example.RandomValues.IncidentType;
import org.example.RandomValues.Incidents;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

public class Incident implements Bulk{
    static int currentlyAvailableID = 1;
    int ID;
    Date timeOfIncident;
    Officer officer1;
    EmergencyCall emergencyCall;
    Officer officer2;
    String type;

    public Incident(EmergencyCall emergencyCall) {
        this.emergencyCall = emergencyCall;
        officer1 = emergencyCall.patrol.officer1;
        officer2 = emergencyCall.patrol.officer2;
        type = IncidentType.get();
        Calendar c = Calendar.getInstance();
        c.setTime(emergencyCall.timeOfReport);
        c.add(Calendar.MINUTE, -1*ThreadLocalRandom.current().nextInt(0, 120));
        timeOfIncident = c.getTime();
    }

    @Override
    public String getBulkLine() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String result = Util.stitch(String.valueOf(ID), String.valueOf(officer1.ID),
                String.valueOf(officer2.ID), String.valueOf(emergencyCall.ID),
                f.format(timeOfIncident), type);
        return result;
    }
}
