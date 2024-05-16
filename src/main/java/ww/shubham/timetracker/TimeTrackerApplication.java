package ww.shubham.timetracker;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class TimeTrackerApplication {

	@Autowired
    private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(TimeTrackerApplication.class, args);
		if(args.length < 2) {
			Logger.log("Error ! Not enough commands");
		}
		System.out.println("Hi");
	}

	@PostConstruct
    public void init() {
        String[] activeProfiles = env.getActiveProfiles();
        for (String profile : activeProfiles) {
            System.out.println("Active profile: " + profile);
        }
    }

}
