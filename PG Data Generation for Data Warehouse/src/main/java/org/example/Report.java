package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;
import org.example.RandomValues.IncidentType;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Report {
    List<Officer> officersOb;
    List<Integer> officers = new ArrayList<>();
    List<Participant> participants;
    String type;
    String address;
    String district;
    Duration responseTimeOb;
    String responseTime;
    String timeOfReport;
    Date timeOfReportOb;
    Duration durationOb;
    String duration;
    Incident incidentOb;

    public String getIncident() {
        return incident;
    }

    String incident;
    Boolean falseAlarm;

    public Report(List<Officer> officersOb, List<Participant> participants, Incident incidentOb) {
        this.officersOb = officersOb;
        this.participants = participants;
        this.incidentOb = incidentOb;
        var tmp = ThreadLocalRandom.current().nextInt(0, 100);
        falseAlarm = false;
        if(tmp <3) {
            type = IncidentType.get();
            falseAlarm = true;
        }
        else
            type = incidentOb.type;
        Faker faker = new Faker();
        address = faker.address().streetAddress();
        district = incidentOb.emergencyCall.patrol.district;
        Calendar c = Calendar.getInstance();
        c.setTime(incidentOb.emergencyCall.timeOfReport);
        c.add(Calendar.MINUTE, ThreadLocalRandom.current().nextInt(25, 41));
        timeOfReportOb = c.getTime();
        Date arrivalTime = faker.date().between(incidentOb.emergencyCall.timeOfReport, timeOfReportOb);
        Instant t1 = incidentOb.emergencyCall.timeOfReport.toInstant();
        Instant t2 = arrivalTime.toInstant();
        Instant t3 = timeOfReportOb.toInstant();
        durationOb = Duration.between(t2, t3);
        responseTimeOb = Duration.between(t1, t2);
        for (var e :officersOb){
            officers.add(e.ID);
        }
        incident = incidentOb.type;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        timeOfReport = f.format(timeOfReportOb);
        responseTime = Util.convertToString(responseTimeOb);
        duration = Util.convertToString(durationOb);
    }
    @JsonIgnore
    public List<Officer> getOfficersOb() {
        return officersOb;
    }

    public void setOfficers(List<Officer> officers) {
        this.officersOb = officers;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Duration responseTime) {
        this.responseTimeOb = responseTime;
    }

    public String getTimeOfReport() {
        return timeOfReport;
    }

    public void setTimeOfReport(Date timeOfReport) {
        this.timeOfReportOb = timeOfReport;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.durationOb = duration;
    }

    public void setIncident(Incident incident) {
        this.incidentOb = incident;
    }

    public Boolean getFalseAlarm() {
        return falseAlarm;
    }

    public void setFalseAlarm(Boolean falseAlarm) {
        this.falseAlarm = falseAlarm;
    }

    public List<Integer> getOfficers() {
        return officers;
    }
}
