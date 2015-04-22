package de.zalando.social.shovel.web.social;

import com.google.gson.Gson;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.web.Fake.MessagesGenerator;
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

    private Gson gson = new Gson();
    private Random random = new Random();
    private void sleep() {
        try {
            // wait random time
            int sleepingTime = 1000 * random.nextInt(1100) / 100;
            System.out.println("Sleeping for " + sleepingTime + " ... ");
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void sendMessageToAll(TextMessage message) {
        for (WebSocketSession s : SimpleHandler.sessions) {
            try {
                s.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(fixedRate = 1000)
    public void doIt() {
        sleep();
        StatisticMessage mes = MessagesGenerator.getInstance().generate();
        String log = "Fake messages, msg : " + mes + "\n" +
                      "Number of sessions: " + SimpleHandler.sessions.size();

        System.out.println(log);
        TextMessage message = new TextMessage(gson.toJson(mes));
        sendMessageToAll(message);
    }

    // tweets
    @Scheduled(fixedRate = 1000)
    public void doTweet() {
        sleep();
        Message tweet = MessagesGenerator.getInstance().generateMessage();
        String log = "Fake messages, tweet : " + tweet + "\n" +
                "Number of sessions: " + SimpleHandler.sessions.size();

        System.out.println(log);
        TextMessage message = new TextMessage(gson.toJson(tweet));
        sendMessageToAll(message);
    }

}
