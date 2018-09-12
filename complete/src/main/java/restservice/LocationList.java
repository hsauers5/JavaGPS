package restservice;

import java.util.*;

public class LocationList extends HashMap {

    private static final String key = "1234";

    public LocationList(String apiKey) {

        //ensures authentication
        if (apiKey == key) {
            double[] gps = {28.5988385, -81.1990983};
            addTrucks(0, gps);


            //populate list from database here


        }
    }

    public void addTrucks(int id, double[] coordinates) {

        super.put("0", coordinates);

    }
}
