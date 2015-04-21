package de.zalando.social.shovel.service.social.twitter;

import de.zalando.social.shovel.service.social.specification.MessagePublisher;
import de.zalando.social.shovel.service.social.specification.MessageTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.awt.*;
import java.util.List;

/**
 * Created by soroosh on 4/20/15.
 */
@Service
public class TwitterStreamReaderService implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStreamReaderService.class);
    @Value("${shovel.twitter.streaming.topics}")
    private String[] topics;
    @Value("${shovel.twitter.streaming.langs}}")
    private String[] langs;

    TwitterStream twitterStream;

    @Autowired
    private MessageTransformer<Status> messageTransformer;

    @Autowired
    private MessagePublisher publisherService;
    private ApplicationContext context;

    @PostConstruct
    public void init() {
        LOGGER.info("Initilizing twitter service");
        LOGGER.info("Twitter streaming topics: <{}>", (Object) topics);
        LOGGER.info("Twitter streaming languages: <{}>", (Object) langs);

        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new TwitterStatusListener(topics, messageTransformer, publisherService));

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(topics);
        filterQuery.language(new String[]{"en"});
        twitterStream.filter(filterQuery);
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("Shutting down twitter stream");
        this.twitterStream.shutdown();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
