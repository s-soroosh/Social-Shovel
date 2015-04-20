package de.zalando.social.shovel.service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by soroosh on 4/20/15.
 */
@Configuration
@EnableScheduling
@EnableJms
@ComponentScan({"de.zalando.social.shovel.service.social"})
public class ServiceConfiguration {
}
