package de.zalando.social.shovel.service.classification;

import com.google.gson.Gson;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.messaging.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by soroosh on 4/22/15.
 */
@Service
public class ClassificationListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassificationListener.class);
    private static final Gson gson = new Gson();
    @Autowired
    private MessageRepository messageRepository;

    @JmsListener(destination = "${shovel.queue.message.out.destination}")
    public void onMessage(String message) {
        Message msg = gson.fromJson(message, Message.class);
        LOGGER.info("Updating message with id:{}, new opinion:{}, new classification:{}",msg.getId(),msg.getUserOpinion(),msg.getMessageClass());
        this.messageRepository.save(msg);
    }
}
