package de.zalando.social.shovel.service;

import de.zalando.social.shovel.service.configuration.ServiceConfiguration;
import de.zalando.social.shovel.service.messaging.Message;
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

/**
 * Created by soroosh on 4/21/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceConfiguration.class)
@EnableJms
public class SimpleTest {

    @Autowired
    private MessagePublisher publisher;

    @Test
    public void t1(){
        Message.MessageBuilder messageBuilder = new Message.MessageBuilder("simple content", "twitter", "simple");
        Message msg = messageBuilder.on(new Date()).build();
        publisher.publish(msg);

    }
}
