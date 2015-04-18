package de.zalando.social.shovel.social;

import de.zalando.social.shovel.websocket.SimpleHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * Created by SOROOSH on 4/3/15.
 */
@Component
public class ScheduledTasks {
//    @Scheduled(fixedRate = 15000)
    public void doIt() {
        System.out.println("Number of sessions: " + SimpleHandler.sessions.size());
        for (WebSocketSession s : SimpleHandler.sessions) {
            try {
                s.sendMessage(new TextMessage("Hello"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hi man, i am from scheduler.");

    }
}
