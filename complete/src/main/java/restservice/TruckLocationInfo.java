package restservice;

import java.util.HashMap;

public class TruckLocationInfo extends HashMap {

    public TruckLocationInfo(String name, String datestamp, double latitude, double longitude) {
        super.put("name", name);
        super.put("date", datestamp);
        super.put("latitude", latitude);
        super.put("longitude", longitude);
    }

}
