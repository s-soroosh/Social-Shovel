package de.zalando.social.shovel.service;

import com.mongodb.DBObject;
import de.zalando.social.shovel.service.configuration.ServiceConfiguration;
import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import de.zalando.social.shovel.service.criteria.CriteriaStats;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.messaging.MessageRepository;
import de.zalando.social.shovel.service.social.JMSMessagePublisher;
import de.zalando.social.shovel.service.social.specification.MessagePublisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by vvenkatraman on 4/21/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceConfiguration.class)
@EnableJms
public class MongoInsertTest {

    @Autowired
    private MessageRepository repository;

//    @Test
//    public void testMongoInsert(){
//        Message.MessageBuilder messageBuilder = new Message.MessageBuilder("simple content", "twitter", "simple");
//        Message msg = messageBuilder.on(new Date()).build();
//        repository.insert(msg);
//        int count = 0;
//        for(Message m : repository.findByProvider("twitter")) {
//            ++count;
//        }
//
//        Assert.assertEquals(count,2);
//
//    }


    @Test
    public void testMongoAggregation(){

        String[] providers = new String[] {"twitter","facebook"};

        System.out.println("Inserting dummy entries for test");
        for(int i=0;i<providers.length;i++) {
            int insertCount = (i+1);
            for(int j=0;j<3*insertCount;j++) {
                // 3 twitter messages and 6 FB messages to be inserted
                Message.MessageBuilder messageBuilder = new Message.MessageBuilder("simple content", providers[i], "simple");
                Message msg = messageBuilder.on(new Date()).build();
                repository.insert(msg);
            }
        }

        DBObject results = repository.aggrMsg(AggregateCriteria.PROVIDER);
        for(String key : results.keySet()) {
            System.out.println(key + "" + results.get(key));
        }
    }
}