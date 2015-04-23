package de.zalando.social.shovel.web.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.web.websocket.messages.StatisticMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * Created by mlitvin on 22/04/15.
 */

@Component
public class WebSocketHelper {
    private static final Gson gson = new Gson();

    private void sendMessage(TextMessage message) {
        for (WebSocketSession s : SimpleHandler.sessions) {
            try {
                s.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getMessageType(Object message) throws Exception {
        if (message instanceof Message) return "tweet";
        if (message instanceof StatisticMessage) return "statistic";
        throw new Exception("Unsupported message");
    }

    public void sendMessageToAll(Object message) throws Exception {
        String type = getMessageType(message);
        JsonObject object = (JsonObject) gson.toJsonTree(message);
        object.addProperty("type", type);

        String log = "Number of sessions: " + SimpleHandler.sessions.size() +
                "\nmessage: " + object;
        System.out.println(log);
        sendMessage(new TextMessage(object.toString()));
    }
}
