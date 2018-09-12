package restservice;

import java.util.*;

/**
     * TODO: Return date?
     * TODO: Add database support.
 */
public class LocationList extends HashMap {

    public LocationList() {

        //placeholder code
        addTruckLocationInfo("001", new TruckLocationInfo("harry", "12-25-1999",
                101.5, 202.7));

        //populate LocationList from database
        //fetchTruckData();

    }

    public void addTruckLocationInfo(String truckID, TruckLocationInfo truckLocation) {

        super.put(truckID, truckLocation);

    }

    public void fetchTruckData() {
        //SELECT truck ID, date, latitude/x, longitude/y coordinates from DB WHERE
        //for each truck returned:
        //addTruckLocationInfo(truckID, new TruckLocationInfo(name, date, latitude, longitude);
    }
}
