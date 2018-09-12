package restservice;

import java.util.*;

public class LocationList extends HashMap {

    public LocationList() {

        addTrucks();

        //populate list from database here

    }

    public void addTrucks() {

        double[] coordinates = {28.5988385, -81.1990983};

        super.put("0", coordinates);

    }
}
