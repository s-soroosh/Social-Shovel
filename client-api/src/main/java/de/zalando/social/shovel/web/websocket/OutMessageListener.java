package de.zalando.social.shovel.web.websocket;

import com.google.gson.Gson;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.messaging.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by mlitvin on 23/04/15.
 */
@Service
public class OutMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutMessageListener.class);
    private static final Gson gson = new Gson();
    @Autowired
    private WebSocketHelper webSocketHelper;

    @JmsListener(destination = "${shovel.queue.message.out.destination}")
    public void onMessage(String message) throws Exception {
        webSocketHelper.sendMessageToAll(gson.fromJson(message, Message.class));
    }
}