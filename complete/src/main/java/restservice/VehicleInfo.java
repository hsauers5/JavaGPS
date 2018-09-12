package restservice;

import java.util.HashMap;

public class VehicleInfo extends HashMap {

    HashMap basicInfo = new HashMap();
    String division = new String();
    HashMap driverInfo = new HashMap();

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

        super.put("basic", basicInfo);
        super.put("division", division);
        super.put("driver", driverInfo);

    }

    public static Boolean doesVehicleNameExist(String vehicleName) {
        //check if name exists in database
        return true;
    }

    private void fetchVehicleInfoFromName(String vehicleName) {
        //populate basicInfo, division, and driverInfo
    }
}
