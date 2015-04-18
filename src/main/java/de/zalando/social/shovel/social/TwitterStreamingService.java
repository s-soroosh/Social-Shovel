package de.zalando.social.shovel.social;

import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by SOROOSH on 4/18/15.
 */
@Service
public class TwitterStreamingService {

    private final StreamListener listener;
    private final StreamingOperations streamingOperations;

    public TwitterStreamingService() {
        System.out.println("Initializing twitter streaming");
        listener = new StreamListener() {
            @Override
            public void onTweet(Tweet tweet) {
                System.out.println(tweet);
            }

            @Override
            public void onDelete(StreamDeleteEvent streamDeleteEvent) {

            }

            @Override
            public void onLimit(int i) {

            }

            @Override
            public void onWarning(StreamWarningEvent streamWarningEvent) {

            }
        };
        TwitterTemplate t = new TwitterTemplate("EYqDP7xAZPvfvh7Jz3vNBqrKe", "mHSTM7DTiSNWJFg3m8hMtDEMQmSXjjU2gp7EC75iqN3oytvDE4");
        streamingOperations = t.streamingOperations();
        Stream stream = streamingOperations.filter("#zalando", Arrays.asList(listener));
        stream.open();
    }

}
