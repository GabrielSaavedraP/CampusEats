package org.example.campuseats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@Async
@SpringBootApplication
public class CampusEatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusEatsApplication.class, args);
    }

}
