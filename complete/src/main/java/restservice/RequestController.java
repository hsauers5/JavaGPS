package restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    private static final String template = "Hello, %s!";
    private final int counter = 0;
    private static final String key = "1234";

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter, String.format(template, name));
    }

    @RequestMapping("/locations")
    public ResponseEntity<ResponseEntity> location(@RequestParam(value="key", defaultValue = "1234") String apiKey) {
        //System.out.println(apiKey);
        //return new LocationList(apiKey);
        if (checkAuth(apiKey)) {
            return new ResponseEntity(new LocationList(), HttpStatus.OK);
        } else {
            return new ResponseEntity("401 UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/info")
    public ResponseEntity<ResponseEntity> vehicleInfo(@RequestParam(value="key", defaultValue = "1234") String apiKey,
                                                      @RequestParam(value="vehicle") String vehicleName) {
        if (checkAuth(apiKey)) {
            //if vehicle name exists
            if (VehicleInfo.doesVehicleNameExist(vehicleName)) {
                return new ResponseEntity(new VehicleInfo(vehicleName), HttpStatus.OK);
            } else {
                return new ResponseEntity("400 BAD REQUEST", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("401 UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
    }

    public Boolean checkAuth(String authtoken) {
        if (authtoken.equals(key)) {
            return true;
        } else {
            return false;
        }
    }
}
