package de.zalando.social.shovel.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * Created by SOROOSH on 4/19/15.
 */
@Component
public class MessageReceiver {

    @Autowired
    MessageRepository msgRepository;

    @Autowired
    ConfigurableApplicationContext context;

    @JmsListener(destination = "dest1")
    public void onMessage(Message msg){
        System.out.println("Persisting message : " + msg);
        msgRepository.save(msg);
    }
}
