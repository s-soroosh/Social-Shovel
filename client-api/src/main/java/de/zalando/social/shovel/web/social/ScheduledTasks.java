package de.zalando.social.shovel.web.social;

import com.google.gson.Gson;
import de.zalando.social.shovel.web.Fake.StaticMessageGenerator;
import de.zalando.social.shovel.web.websocket.SimpleHandler;
import de.zalando.social.shovel.web.websocket.messages.StatisticMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Random;

/**
 * Created by SOROOSH on 4/3/15.
 */
@Component
public class ScheduledTasks {

    @Autowired
    private JmsTemplate template;

    private Gson gson = new Gson();
    private Random random = new Random();

    @Scheduled(fixedRate = 1000)
    public void doIt() {
        try {
            // wait random time
            int sleepingTime = 1000 * random.nextInt(1100) / 100;
            System.out.println("Sleeping for " + sleepingTime + " ... ");
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StatisticMessage mes = StaticMessageGenerator.getInstance().generate();
        String log = "Fake messages, msg : " + mes + "\n" +
                "Number of sessions: " + SimpleHandler.sessions.size();

        System.out.println(log);
        TextMessage message = new TextMessage(gson.toJson(mes));
        for (WebSocketSession s : SimpleHandler.sessions) {
            try {
                s.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
