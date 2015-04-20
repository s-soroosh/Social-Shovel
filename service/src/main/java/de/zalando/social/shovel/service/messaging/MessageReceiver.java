package de.zalando.social.shovel.service.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by SOROOSH on 4/19/15.
 */
@Component
public class MessageReceiver {

    @JmsListener(destination = "dest1")
    public void onMessage(Message msg){
        System.out.println(msg.getName());

    }
}
