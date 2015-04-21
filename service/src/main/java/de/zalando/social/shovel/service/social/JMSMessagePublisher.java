package de.zalando.social.shovel.service.social;

import com.google.gson.Gson;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.social.specification.MessagePublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by soroosh on 4/20/15.
 */
@Service
public class JMSMessagePublisher implements MessagePublisher {

    @Value("${shovel.queue.message.destination}")
    private String destination;
    private static final Logger LOGGER = LoggerFactory.getLogger(JMSMessagePublisher.class);
    private static final Gson gson = new Gson();

    @Autowired
    private JmsTemplate template;


    @Override
    public void publish(Message msg) {
        LOGGER.info("Publishing message:{}", msg);

        String stringifiedObject = gson.toJson(msg);
        template.convertAndSend(destination, stringifiedObject);
    }
}
