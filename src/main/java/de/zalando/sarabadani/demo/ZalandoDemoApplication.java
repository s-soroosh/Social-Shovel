package de.zalando.sarabadani.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("de.zalando.sarabadani.demo.controller")
public class ZalandoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZalandoDemoApplication.class, args);
    }
}
