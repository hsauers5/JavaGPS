package restservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleTelemetry {

    String name;
    String date;
    List telemetryData;

    public VehicleTelemetry(String vehicleName, String date) {

        this.name = vehicleName;
        this.date = date;

        String selectInfo = "SELECT * FROM locations.trucklocations WHERE truck_name =\""
                + this.name + "\"&& date =\"" + this.date + "\";";

        telemetryData = DatabaseController.doQuery(selectInfo, "SELECT");

    }

    public static void main(String[] args) {
        VehicleTelemetry vehicle = new VehicleTelemetry("RO178", "2018-04-26");
        System.out.println(vehicle);
    }

    public String toString() {

        /*
        for (Object mapObj : telemetryData) {
            HashMap telemetry = (HashMap) mapObj;
        }
        */

        return telemetryData.toString();
    }

}
