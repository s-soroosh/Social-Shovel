package de.zalando.social.shovel.web;

import de.zalando.social.shovel.service.configuration.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan({"de.zalando.social.shovel.web", "de.zalando.social.shovel.web.configuration"})
@EnableScheduling
@Import({ServiceConfiguration.class})
public class ZalandoDemoApplication {

    public static void main(String[] args) {



        SpringApplication.run(ZalandoDemoApplication.class, args);

    }
}
