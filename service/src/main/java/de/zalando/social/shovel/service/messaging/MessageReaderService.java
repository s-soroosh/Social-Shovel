package de.zalando.social.shovel.service.messaging;

import com.google.gson.Gson;
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
    private static final Gson gson = new Gson();

    @JmsListener(destination = "${shovel.queue.message.destination}")
    public void onMessage(String msg){
        System.out.println(msg);
        Message message = gson.fromJson(msg, Message.class);
        this.repository.insert(message);
    }
}
