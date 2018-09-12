package restservice;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    private static final String template = "Hello, %s!";
    private final int counter = 0;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter, String.format(template, name));
    }

    @RequestMapping("/locations")
    public LocationList location(@RequestParam(value="key", defaultValue = "1234") String apiKey) {
        return new LocationList(apiKey);
    }
}
