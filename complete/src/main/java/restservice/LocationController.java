package restservice;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.Date;
import java.time.LocalTime;

public class LocationController extends TimerTask {

    public String time_index;

    public LocationController() {
        super();
        //this.time_index = time_index;
    }

    public void run() {
        //sets time_index to human readable time (24hr)
        time_index = LocalTime.now().toString().substring(0,5);

        //fetch current location data and write to DB
        LocationList newestLocationList = new LocationList();
        VehicleList listByDivision = new VehicleList("Orlando (100)");

        System.out.println(listByDivision.toString());

        ArrayList<HashMap> locationsToAdd = new ArrayList();

        //if Orlando division contains this truck, add it to pending list
        for (Object locationData : newestLocationList) {
            HashMap locationDataAsMap = (HashMap) locationData;
            if (listByDivision.contains(locationDataAsMap.get("name"))) {
                locationsToAdd.add(locationDataAsMap);
            }
        }

        for (HashMap locationData : locationsToAdd) {
            String date = "\"" + locationData.get("date").toString().substring(0,10) + "\",";
            String timeIndex = "\"" + time_index + "\",";
            String name = "\"" + locationData.get("name").toString() + "\",";
            String latitude = "\"" + locationData.get("latitude").toString() + "\",";
            String longitude = "\"" + locationData.get("longitude").toString() + "\"";

            String insertLocation = "INSERT INTO locations.trucklocations " +
                    "(date, time, truck_name, latitude, longitude) " +
                    "VALUES (" + date + timeIndex + name + latitude + longitude + ");";

            System.out.println(insertLocation);

            DatabaseController.doQuery(insertLocation, "INSERT");
        }

        System.out.println(LocalTime.now().toString().substring(0,5));

        /*
        if (time_index >= 1440 || LocalTime.now().toString().substring(0,5).equals("00:00")) {
            time_index = 0;
        }
        */
    }
}
