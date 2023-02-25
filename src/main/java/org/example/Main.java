package org.example;

import com.github.javafaker.Faker;
import org.example.RandomValues.Incidents;
import org.example.RandomValues.InvolvementType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        List<Bulk> officers = new ArrayList<>();
        List<Bulk> officersT2 = new ArrayList<>();
        List<Bulk> officersJoined = new ArrayList<>();
        List<Bulk> complaints = new ArrayList<>();
        List<Bulk> complaintsT2 = new ArrayList<>();
        List<Bulk> dispatchers = new ArrayList<>();
        List<Bulk> dispatchersT2 = new ArrayList<>();
        List<Bulk> vacations = new ArrayList<>();
        List<Bulk> vacationsT2 = new ArrayList<>();
        List<Bulk> patrols = new ArrayList<>();
        List<Bulk> patrolsT2 = new ArrayList<>();
        //t1
        for(int i =0;i<2000;i++)
            officers.add(new Officer(false));
        //t2
        for(int i =0;i<250;i++)
            officersT2.add(new Officer(true));
        officersJoined.addAll(officers);
        officersJoined.addAll(officersT2);
        System.out.println("Officers are done");
        //t1
        for(int i =0;i<officers.size()*0.40;i++)
            complaints.add(new Complaint(officers, false));
        //t2
        for(int i =0;i<officersJoined.size()*0.40*0.5;i++)
            complaintsT2.add(new Complaint(officersJoined, true));
        System.out.println("Complaints are done");
        //t1
        for(int i =0;i<officers.size()*0.8;i++)
            dispatchers.add(new Dispatcher());
        //t2
        for(int i =0;i<officersJoined.size()*0.8*0.3;i++)
            dispatchersT2.add(new Dispatcher());
        System.out.println("Dispatchers are done");
        //t1
        for(int i =0;i<officers.size()*50;i++)
            vacations.add(new Vacation(officers, false));
        //t2
        for(int i =0;i<officersJoined.size()*50*0.5;i++)
            vacationsT2.add(new Vacation(officersJoined, true));
        System.out.println("Vacations are done");
        //t1
        for(int i =0;i< officers.size()*60;i++)
            patrols.add(new Patrol(officers, false));
        List<Patrol> patrolstmp = (List<Patrol>)(Object)patrols;
        Collections.sort(patrolstmp, (a, b) -> a.startDate.compareTo(b.startDate));
        //t2
        for(int i =0;i< officersJoined.size()*60*0.5;i++)
            patrolsT2.add(new Patrol(officersJoined, true));
        List<Patrol> patrolstmpT2 = (List<Patrol>)(Object)patrolsT2;
        Collections.sort(patrolstmpT2, (a, b) -> a.startDate.compareTo(b.startDate));
//        for(var e:patrols)
//            System.out.println(e.getBulkLine());
        System.out.println("Patrols are done");
        //t1
        Util.generateBulk(officers, Util.FILE_PATH_OFFICERS);
        Util.generateBulk(complaints, Util.FILE_PATH_COMPLAINTS);
        Util.generateBulk(dispatchers, Util.FILE_PATH_DISPATCHERS);
        Util.generateBulk(vacations, Util.FILE_PATH_VACATIONS);
        Util.generateBulk(patrols, Util.FILE_PATH_PATROLS);
        //t2
        Util.generateBulk(officersT2, Util.FILE_PATH_OFFICERST2);
        Util.generateBulk(complaintsT2, Util.FILE_PATH_COMPLAINTST2);
        Util.generateBulk(dispatchersT2, Util.FILE_PATH_DISPATCHERST2);
        Util.generateBulk(vacationsT2, Util.FILE_PATH_VACATIONST2);
        Util.generateBulk(patrolsT2, Util.FILE_PATH_PATROLST2);
        //t1
        List<EmergencyCall> emergencyCalls = new ArrayList<>();
        //t2
        List<EmergencyCall> emergencyCallsT2 = new ArrayList<>();
        Faker faker = new Faker();
        //t1
        for(var patrol:patrolstmp){
            var incidentCount = Incidents.get();
            for(var i=0;i<incidentCount+1;i++){
                emergencyCalls.add(new EmergencyCall((Dispatcher)dispatchers.get(ThreadLocalRandom.current().
                        nextInt(0, dispatchers.size())),faker.date().between(patrol.startDate, patrol.endDate),
                        patrol));
            }
        }
        Collections.sort(emergencyCalls, (a, b) -> a.timeOfReport.compareTo(b.timeOfReport));
        Util.assignIDsToEmergencyCalls(emergencyCalls);
        //t2
        for(var patrol:patrolstmpT2){
            var incidentCount = Incidents.get();
            for(var i=0;i<incidentCount+1;i++){
                emergencyCallsT2.add(new EmergencyCall((Dispatcher)dispatchers.get(ThreadLocalRandom.current().
                        nextInt(0, dispatchers.size())),faker.date().between(patrol.startDate, patrol.endDate),
                        patrol));
            }
        }
        Collections.sort(emergencyCallsT2, (a, b) -> a.timeOfReport.compareTo(b.timeOfReport));
        Util.assignIDsToEmergencyCalls(emergencyCallsT2);
        System.out.println("Emergency calls are done");
        //t1
        Util.generateBulk((List<Bulk>)(Object)emergencyCalls, Util.FILE_PATH_EMERGENCY_CALLS);
        //t2
        Util.generateBulk((List<Bulk>)(Object)emergencyCallsT2, Util.FILE_PATH_EMERGENCY_CALLST2);
        //t1
        List<Incident> incidents = new ArrayList<>();
        for(var e: emergencyCalls){
            incidents.add(new Incident(e));
        }
        //t2
        List<Incident> incidentsT2 = new ArrayList<>();
        for(var e: emergencyCallsT2){
            incidentsT2.add(new Incident(e));
        }
        //t1
        Collections.sort(incidents, (a, b) -> a.timeOfIncident.compareTo(b.timeOfIncident));
        Util.assignIDsToIncidents(incidents);
        Util.generateBulk((List<Bulk>)(Object)incidents, Util.FILE_PATH_INCIDENTS);
        //t2
        Collections.sort(incidentsT2, (a, b) -> a.timeOfIncident.compareTo(b.timeOfIncident));
        Util.assignIDsToIncidents(incidentsT2);
        Util.generateBulk((List<Bulk>)(Object)incidentsT2, Util.FILE_PATH_INCIDENTST2);
        System.out.println("Incidents are done");
        //participants are shared between t1 and t2
        List<Participant> participants = new ArrayList<>();
        var partCount = (incidents.size()+incidentsT2.size())*1.5;
        int k = 0;
        int limit = (int) (partCount/25);
        System.out.println("limit: "+ limit+" Total: "+ partCount);
        for(int i=0;i< partCount; i++){
            participants.add(new Participant());
            System.out.println(i);
        }
        System.out.println();
        System.out.println("Participants are done");
        //t1
        List<Report> reports = new ArrayList<>();
        //to delete
        int j=1;
        for(var incident:incidents){
            //to delete
            if (!(j++ % 6690 == 0)) continue;
            System.out.println(incident.ID);
            List<Officer> tmpOfficers = new ArrayList<>();
            List<Participant> tmpParticipants = new ArrayList<>();
            tmpOfficers.add(incident.officer1);
            tmpOfficers.add(incident.officer2);
            var rnd = ThreadLocalRandom.current().nextInt(0, 100);
            if(rnd<2){
                Patrol tmpPatrol = Util.availablePatrol(incident, patrolstmp);
                if( tmpPatrol != null){
                    tmpOfficers.add(tmpPatrol.officer1);
                    tmpOfficers.add(tmpPatrol.officer2);
                }
            }
            rnd = ThreadLocalRandom.current().nextInt(1, 4);
            for (int i = 0; i < rnd; i++) {
                Participant part = participants.get(
                        ThreadLocalRandom.current().nextInt(0, participants.size())
                );
                tmpParticipants.add(part);
            }
            rnd = ThreadLocalRandom.current().nextInt(0, 100);
            if(rnd<7){
                var newRnd = ThreadLocalRandom.current().nextInt(0, participants.size());
                var tmpParticipant = participants.get(newRnd);
                tmpParticipant.setInvolvementType(InvolvementType.get());
                tmpParticipants.add(tmpParticipant);
            }
            reports.add(new Report(tmpOfficers, tmpParticipants, incident));
        }
        Util.generateJSON(reports, false);
        //t2
        List<Report> reportsT2 = new ArrayList<>();
        List<Report> reportsJoined = new ArrayList<>();

        //to delete
        j=1;
        for(var incident:incidentsT2){
            //to delete
            if (!(j++ % 6690 == 0)) continue;
            System.out.println(incident.ID);
            List<Officer> tmpOfficers = new ArrayList<>();
            List<Participant> tmpParticipants = new ArrayList<>();
            tmpOfficers.add(incident.officer1);
            tmpOfficers.add(incident.officer2);
            var rnd = ThreadLocalRandom.current().nextInt(0, 100);
            if(rnd<2){
                Patrol tmpPatrol = Util.availablePatrol(incident, patrolstmp);
                if( tmpPatrol != null){
                    tmpOfficers.add(tmpPatrol.officer1);
                    tmpOfficers.add(tmpPatrol.officer2);
                }
            }
            rnd = ThreadLocalRandom.current().nextInt(1, 4);
            for (int i = 0; i < rnd; i++) {
                Participant part = participants.get(
                        ThreadLocalRandom.current().nextInt(0, participants.size())
                );
                tmpParticipants.add(part);
            }
            rnd = ThreadLocalRandom.current().nextInt(0, 100);
            if(rnd<7){
                var newRnd = ThreadLocalRandom.current().nextInt(0, participants.size());
                var tmpParticipant = participants.get(newRnd);
                tmpParticipant.setInvolvementType(InvolvementType.get());
                tmpParticipants.add(tmpParticipant);
            }
            reportsT2.add(new Report(tmpOfficers, tmpParticipants, incident));
        }
        reportsJoined.addAll(reports);
        reportsJoined.addAll(reportsT2);
        Util.generateJSON(reportsJoined, false);
    }
}