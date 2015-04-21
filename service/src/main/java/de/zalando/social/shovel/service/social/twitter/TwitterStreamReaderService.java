package de.zalando.social.shovel.service.social.twitter;

import de.zalando.social.shovel.service.social.specification.MessagePublisher;
import de.zalando.social.shovel.service.social.specification.MessageTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.List;

/**
 * Created by soroosh on 4/20/15.
 */
@Service
public class TwitterStreamReaderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStreamReaderService.class);
    @Value("${shovel.twitter.streaming.topics}")
    private String[] topics;
    @Value("${shovel.twitter.streaming.langs}}")
    private String[] langs;

    @Autowired
    private MessageTransformer<Status> messageTransformer;

    @Autowired
    private MessagePublisher publisherService;

    @PostConstruct
    public void init(){
        LOGGER.info("Initilizing twitter service");
        LOGGER.info("Twitter streaming topics: <{}>",(Object)topics);
        LOGGER.info("Twitter streaming languages: <{}>", (Object)langs);

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new TwitterStatusListener(topics, messageTransformer, publisherService));

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(topics);
        filterQuery.language(langs);
        twitterStream.filter(filterQuery);
    }


}
