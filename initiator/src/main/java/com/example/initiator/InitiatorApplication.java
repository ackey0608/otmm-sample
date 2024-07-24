package com.example.initiator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class InitiatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitiatorApplication.class, args);
    }

}
