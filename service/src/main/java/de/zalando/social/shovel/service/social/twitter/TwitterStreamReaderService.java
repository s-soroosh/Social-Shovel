package de.zalando.social.shovel.service.social.twitter;

import de.zalando.social.shovel.service.social.specification.MessagePublisher;
import de.zalando.social.shovel.service.social.specification.MessageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PostConstruct;

/**
 * Created by soroosh on 4/20/15.
 */
@Service
public class TwitterStreamReaderService {

    @Autowired
    private MessageTransformer<Status> messageTransformer;

    @Autowired
    private MessagePublisher publisherService;

    @PostConstruct
    public void init(){
        System.out.println("Initilizing twitter service");
        String[] topics = {"zalando"};
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new TwitterStatusListener(topics,messageTransformer,publisherService));

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(topics);
//        filterQuery.language(new String[]{"en"});
        twitterStream.filter(filterQuery);
    }


}
