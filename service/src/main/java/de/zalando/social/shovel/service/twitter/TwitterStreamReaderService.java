package de.zalando.social.shovel.service.twitter;

import org.springframework.stereotype.Service;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PostConstruct;

/**
 * Created by soroosh on 4/20/15.
 */
@Service
public class TwitterStreamReaderService {

    @PostConstruct
    public void init(){
        System.out.println("Initilizing twitter service");
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new TwitterStatusListener());

        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(new String[]{"zalando"});
//        filterQuery.language(new String[]{"en"});
        twitterStream.filter(filterQuery);
    }


}
