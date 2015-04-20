package de.zalando.social.shovel.service.twitter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * Created by soroosh on 4/20/15.
 */
public class TwitterStatusListener implements StatusListener{
    @Override
    public void onStatus(Status status) {
        System.out.println(status.getId() + " : " + status.getUser().getName() + " : " + status.getText());
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {

    }

    @Override
    public void onException(Exception e) {

    }
}
