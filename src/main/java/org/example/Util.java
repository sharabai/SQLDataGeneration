package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;

public class Util {

    private static final String PATH_JSONT2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\ReportsT2.json";
    public static String PATH_JSON = "C:\\Users\\shara\\Downloads\\Finished SQL\\Reports.json";
    public static String FILE_PATH_INCIDENTS = "C:\\Users\\shara\\Downloads\\Finished SQL\\Incidents.bulk";
    public static String FILE_PATH_EMERGENCY_CALLS = "C:\\Users\\shara\\Downloads\\Finished SQL\\EmergencyCalls.bulk";
    final static String FILE_PATH_OFFICERS = "C:\\Users\\shara\\Downloads\\Finished SQL\\Officers.bulk";
    final static String FILE_PATH_DISPATCHERS = "C:\\Users\\shara\\Downloads\\Finished SQL\\Dispatchers.bulk";
    final static String FILE_PATH_COMPLAINTS = "C:\\Users\\shara\\Downloads\\Finished SQL\\Complaints.bulk";
    final static String FILE_PATH_VACATIONS = "C:\\Users\\shara\\Downloads\\Finished SQL\\Vacations.bulk";
    final static String FILE_PATH_PATROLS = "C:\\Users\\shara\\Downloads\\Finished SQL\\Patrols.bulk";
    public static String FILE_PATH_INCIDENTST2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\IncidentsT2.bulk";
    public static String FILE_PATH_EMERGENCY_CALLST2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\EmergencyCallsT2.bulk";
    final static String FILE_PATH_OFFICERST2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\OfficersT2.bulk";
    final static String FILE_PATH_DISPATCHERST2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\DispatchersT2.bulk";
    final static String FILE_PATH_COMPLAINTST2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\ComplaintsT2.bulk";
    final static String FILE_PATH_VACATIONST2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\VacationsT2.bulk";
    final static String FILE_PATH_PATROLST2 = "C:\\Users\\shara\\Downloads\\Finished SQL\\PatrolsT2.bulk";

    final static int YEAR_START = 2000;
    final static int MONTH_START = 8;
    final static int DAY_START = 7;


    final static int YEAR_T1 = 2014;
    final static int MONTH_T1 = 11;
    final static int DAY_T1 = 8;
    final static int YEAR_T2 = 2022;
    final static int MONTH_T2 = 11;
    final static int DAY_T2 = 8;
    final static int YEARS_DIFF = 8;
    static String stitch (String... args){
        String result = args[0];
        String delim = "//";
        for(int i =1;i<args.length;i++)
            if (args[i] == "\r\n") {
                result += args[i] + args[i + 1];
                i++;
            }
            else
                result += delim + args[i];
        return result;
    }

    static String generatePhoneNumber(){
        Faker faker = new Faker();
        return faker.number().numberBetween(100,999) + "-" +
                faker.number().randomDigit()+ faker.number().randomDigit()+
                faker.number().randomDigit() + "-" + faker.number().randomDigit() +
                faker.number().randomDigit() + faker.number().randomDigit() +
                faker.number().randomDigit();
    }

    static void generateBulk(List<Bulk> list, String path){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(path);
            for(var e:list){
                writer.println(e.getBulkLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            writer.close();
        }


    }


    public static void assignIDsToEmergencyCalls(List<EmergencyCall> emergencyCalls) {
        for(var e:emergencyCalls)
            e.ID = EmergencyCall.currentlyAvailableID++;
    }

    public static void assignIDsToIncidents(List<Incident> incidents) {
        for(var e:incidents)
            e.ID = Incident.currentlyAvailableID++;
    }

    public static Patrol availablePatrol(Incident incident, List<Patrol> patrols) {
        Patrol found = null;
        for(var p: patrols){
            if ((!(incident.timeOfIncident.before(p.startDate) ||
                    incident.timeOfIncident.after(p.endDate))) &&
                    p.officer1 != incident.officer1)
                found = p;
        }
        return found;
    }

    public static String convertToString(Duration duration) {
        long seconds = duration.getSeconds();
        long HH = seconds / 3600;
        long MM = (seconds % 3600) / 60;
        long SS = seconds % 60;
        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }

    public static void generateJSON(List<Report> reports, Boolean t2) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        PrintWriter writer = null;
        try {
            if (!t2)
                writer = new PrintWriter(PATH_JSON);
            else
                writer = new PrintWriter(PATH_JSONT2);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reports);
            writer.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
