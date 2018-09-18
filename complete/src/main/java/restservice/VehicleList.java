package restservice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class VehicleList extends ArrayList {
    public VehicleList(String divisionName) {

        try {
            divisionName = URLEncoder.encode(divisionName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RestCall rest = null;
        try {
            rest = new RestCall("GET", "?division=" + divisionName, "list");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(rest.getJson());

        //System.out.println(rest.getJson().getClass());

        JSONObject response = (JSONObject) rest.getJson();

        JSONArray vehicleList = (JSONArray) response.get("vehicles");

        for (int i = 0; i < vehicleList.length(); i++) {
            super.add(vehicleList.get(i).toString());
        }

        //System.out.println(super.toString());

    }
}
