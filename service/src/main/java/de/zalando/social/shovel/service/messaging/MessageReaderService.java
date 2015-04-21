package de.zalando.social.shovel.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by soroosh on 4/21/15.
 */
@Service
public class MessageReaderService {
    @Autowired
    private MessageRepository repository;

    @JmsListener(destination = "${shovel.queue.message.destination}")
    public void onMessage(Message msg){
        this.repository.insert(msg);
    }
}
