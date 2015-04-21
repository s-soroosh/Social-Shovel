package de.zalando.social.shovel.service.social.twitter;

import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.social.specification.MessagePublisher;
import de.zalando.social.shovel.service.social.specification.MessageTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * Created by soroosh on 4/20/15.
 */
public class TwitterStatusListener implements StatusListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(TwitterStatusListener.class);
    private final String[] topics;
    private final MessageTransformer<Status> messageTransformer;
    private final MessagePublisher publisherService;

    public TwitterStatusListener(String[] topics, MessageTransformer<Status> messageTransformer, MessagePublisher publisherService) {

        this.topics = topics;
        this.messageTransformer = messageTransformer;
        this.publisherService = publisherService;
    }

    @Override
    public void onStatus(Status status) {
        Message transformedMessage = messageTransformer.transform(status, topics);
        this.publisherService.publish(transformedMessage);
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
        LOGGER.error("Error on listening...",e);
    }
}
