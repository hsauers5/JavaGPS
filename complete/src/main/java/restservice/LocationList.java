package restservice;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
     * TODO: Return date?
     * TODO: Add database support.
 */
public class LocationList extends ArrayList {

    public LocationList() {

        //placeholder code
        //addTruckLocationInfo("001", new TruckLocationInfo("harry", "12-25-1999", 101.5, 202.7));

        //populate LocationList from database
        fetchTruckData();

    }

    public void fetchTruckData() {
       // HttpParams param = new BasicHttpParams();
       // param.setParameter("vehicle", "RL1390_V4");
        RestCall rest = null;
        try {
            rest = new RestCall("GET", "", "location");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(rest.getJson());

        //System.out.println(rest.getJson().getClass());

        JSONArray response = (JSONArray) rest.getJson();

        for (Object truckObj : response) {
            JSONObject truck = (JSONObject) truckObj;
            HashMap truckMap = new HashMap();
            truckMap.put("latitude", truck.get("latitude"));
            truckMap.put("longitude", truck.get("longitude"));
            truckMap.put("date", truck.get("date"));
            truckMap.put("name", truck.get("name"));
            super.add(truckMap);
        }
    }
}
