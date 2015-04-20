package de.zalando.social.shovel;

import de.zalando.social.shovel.web.ZalandoDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ZalandoDemoApplication.class)
//@WebAppConfiguration
public class ZalandoDemoApplicationTests {
	@Test
	public void loadSampleZalandoPosts() {
        TwitterTemplate template = new TwitterTemplate("EYqDP7xAZPvfvh7Jz3vNBqrKe", "mHSTM7DTiSNWJFg3m8hMtDEMQmSXjjU2gp7EC75iqN3oytvDE4");
        SearchParameters params = new SearchParameters("#zalando");
            params.lang("en");
        SearchResults search = template.searchOperations().search(params);
        for(Tweet tw:search.getTweets()){
            System.out.println(tw.getText());
        }
        System.out.println("Number of tweets: " + search.getTweets().size());

	}

}
