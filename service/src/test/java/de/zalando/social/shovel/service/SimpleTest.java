package de.zalando.social.shovel.service;

import de.zalando.social.shovel.service.configuration.ServiceConfiguration;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.messaging.MessageRepository;
import de.zalando.social.shovel.service.social.JMSMessagePublisher;
import de.zalando.social.shovel.service.social.specification.MessagePublisher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by soroosh on 4/21/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ServiceConfiguration.class)
//public class SimpleTest {
//
//    @Autowired
//    private MessagePublisher publisher;
//
//    @Autowired
//    private MessageRepository repository;
//
//    @Before
//    public void before(){
////        repository.deleteAll();
//    }
//
//    @Test
//    public void when_a_new_message_added_to_queue_it_should_be_save_in_db(){
//        Message.MessageBuilder messageBuilder = new Message.MessageBuilder("simple content", "twitter", "simple");
//        Message msg = messageBuilder.on(new Date()).build();
//        publisher.publish(msg);
//        Assert.assertEquals(1, repository.findAll().size());
//
//    }
//}
