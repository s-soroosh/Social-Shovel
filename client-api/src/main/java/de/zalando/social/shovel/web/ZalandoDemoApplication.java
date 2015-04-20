package de.zalando.social.shovel.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan({"de.zalando.social.shovel.web.controller","de.zalando.social.shovel.web.configuration"})
//@Import()
public class ZalandoDemoApplication {

    public static void main(String[] args) {

//        StatusListener listener = new StatusListener() {
//            public void onStatus(Status status) {
//                System.out.println(status.getId() + " : " + status.getUser().getName() + " : " + status.getText());
//            }
//
//            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//                System.out.println(statusDeletionNotice.getStatusId());
//            }
//
//            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
//            }
//
//            @Override
//            public void onScrubGeo(long l, long l1) {
//
//            }
//
//            @Override
//            public void onStallWarning(StallWarning stallWarning) {
//
//            }
//
//            public void onException(Exception ex) {
//                ex.printStackTrace();
//            }
//        };
//        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
//        twitterStream.addListener(listener);
//
//        FilterQuery filterQuery = new FilterQuery();
//        filterQuery.track(new String[]{"alibaba"});
////        filterQuery.language(new String[]{"en"});
//        twitterStream.filter(filterQuery);
//        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
////        twitterStream.sample();
        SpringApplication.run(ZalandoDemoApplication.class, args);

    }
}
