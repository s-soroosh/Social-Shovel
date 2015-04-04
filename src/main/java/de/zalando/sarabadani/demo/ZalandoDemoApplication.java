package de.zalando.sarabadani.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"de.zalando.sarabadani.demo.controller","de.zalando.sarabadani.demo.social"})
//@EnableScheduling
public class ZalandoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZalandoDemoApplication.class, args);
    }
}
