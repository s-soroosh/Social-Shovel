package de.zalando.social.shovel.web.websocket;

import com.google.gson.Gson;
import de.zalando.social.shovel.service.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by mlitvin on 23/04/15.
 */
@Service
public class OutMessageListener {

    private String[] languages = null;
    private static final Gson gson = new Gson();

    @Autowired
    private WebSocketHelper webSocketHelper;

    @Value("${shovel.websockets.messages.filter.enabled}")
    private boolean filterEnabled;

    @Value("${shovel.websockets.messages.filter.languages}")
    private String languagesProp;

    @JmsListener(destination = "${shovel.queue.message.out.destination}")
    public void onMessage(String message) throws Exception {
        Message ms  = gson.fromJson(message, Message.class);

        boolean isFiltered = filterEnabled && isFilteredByTopic(ms);
        if(!isFiltered) {
            webSocketHelper.sendMessageToAll(ms);
        }
    }

    private boolean isFilteredByTopic(Message mes) {
        for(String topic : mes.getTopics()) {
            if ("zalando".equalsIgnoreCase(topic)) return false;
        }
        return true;
    }
}