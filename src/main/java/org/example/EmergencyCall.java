package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmergencyCall implements Bulk{
    static int currentlyAvailableID = 1;
    int ID;
    Dispatcher dispatcher;
    Date timeOfReport;

    Patrol patrol;

    public EmergencyCall(Dispatcher dispatcher, Date time, Patrol patrol) {
        this.dispatcher = dispatcher;
        //ID = currentlyAvailableID++;
        timeOfReport = time;
        this.patrol = patrol;
    }

    @Override
    public String getBulkLine() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String result = Util.stitch(String.valueOf(ID),String.valueOf(dispatcher.ID),
                f.format(timeOfReport));
        return result;
    }
}
