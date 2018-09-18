package restservice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class VehicleInfo extends JSONObject {

    JSONObject resp;

    /*
    {
      "basic": {
        "name": "string",
        "make": "string",
        "model": "string",
        "vin": "string",
        "latestVin": "string",
        "inServiceSince": "2017-10-28",
        "status": "0 (STATUS_DECOMMISSIONED)",
        "fuelType": "NONE",
        "bodyType": "string",
        "inService": true,
        "cam1Installed": true,
        "cam2Installed": true,
        "cam3Installed": true,
        "cam4Installed": true,
        "cam5Installed": true,
        "cam6Installed": true,
        "cam7Installed": true,
        "cam8Installed": true
      },
      "division": "string",
      "driver": {
        "employeeId": "string",
        "firstName": "string",
        "lastName": "string"
      }
    }
     */

    public VehicleInfo(String vehicleName) {

        fetchVehicleInfoFromName(vehicleName);

        super.put(vehicleName, resp);
        System.out.println(this);

    }

    public static Boolean doesVehicleNameExist(String vehicleName) {
        //check if name exists in database
        return true;
    }

    private void fetchVehicleInfoFromName(String vehicleName) {

        RestCall rest = null;
        try {
            rest = new RestCall("GET", "?vehicle=" + vehicleName, "info");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(rest.getJson());

        //System.out.println(rest.getJson().getClass());
        JSONObject response = (JSONObject) rest.getJson();

        this.resp = response;

        System.out.println(this.resp);

    }
}
