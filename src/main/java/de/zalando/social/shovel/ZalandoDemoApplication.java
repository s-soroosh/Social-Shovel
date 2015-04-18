package de.zalando.social.shovel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import twitter4j.*;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan
@EnableScheduling
public class ZalandoDemoApplication {

    public static void main(String[] args) {

        StatusListener listener = new StatusListener() {
            public void onStatus(Status status) {
                System.out.println(status.getUser().getName() + " : " + status.getText());
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            @Override
            public void onScrubGeo(long l, long l1) {

            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {

            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(new String[]{"#Zalando"});
        twitterStream.filter(filterQuery);
        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
//        twitterStream.sample();
    }
}
