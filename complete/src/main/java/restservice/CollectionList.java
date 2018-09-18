package restservice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CollectionList extends ArrayList<Object> {

    String nameOfVehicle;
    String dateOfStops;

    /**
     *
     * @param vehicleName
     * @param date - format as YYYY-MM-DD
     */
    public CollectionList(String vehicleName, String date) {

        nameOfVehicle = vehicleName;
        dateOfStops = date;

        getStops();

    }

    private void getStops() {

        RestCall rest = null;
        try {
            rest = new RestCall("GET", "?vehicle=" + nameOfVehicle +
                    "&date=" + dateOfStops, "collection");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(rest.getJson());

        System.out.println(rest.getJson().getClass());

        JSONArray response = (JSONArray) rest.getJson();

        for (Object stopObj : response) {
            JSONObject stop = (JSONObject) stopObj;
            super.add(stop.toString());
        }

    }

}
