package de.zalando.social.shovel.service.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by soroosh on 4/20/15.
 */
@SpringBootApplication
@Configuration
@EnableJms
@EnableMongoRepositories("de.zalando.social.shovel.service.messaging")
@ComponentScan({"de.zalando.social.shovel.service.social","de.zalando.social.shovel.service.messaging"})
public class ServiceConfiguration {
}
