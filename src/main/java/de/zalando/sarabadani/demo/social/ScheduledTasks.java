package de.zalando.sarabadani.demo.social;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by SOROOSH on 4/3/15.
 */
@Component
public class ScheduledTasks {
    @Scheduled(fixedRate =15000)
    public void doIt(){
        System.out.println("Hi man, i am from scheduler.");

    }
}
