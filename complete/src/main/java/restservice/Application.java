package restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        //runs LocationController every 60 seconds to fetch new locations and update DB.
        Timer timer = new Timer();
        timer.schedule(new LocationController(), 0, 60000);

        SpringApplication.run(Application.class, args);
    }
}
