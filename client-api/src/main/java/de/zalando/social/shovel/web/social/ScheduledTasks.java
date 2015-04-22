package de.zalando.social.shovel.web.social;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.web.Fake.MessagesGenerator;
import de.zalando.social.shovel.web.websocket.SimpleHandler;
import de.zalando.social.shovel.web.websocket.messages.StatisticMessage;
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

    private String getMessageType(Object message) throws Exception {
        if (message instanceof Message) return "tweet";
        if (message instanceof StatisticMessage) return "statistic";
        throw new Exception("Unsupported message");
    }

    private void sendMessage(Object message) throws Exception {
        sleep();
        String type = getMessageType(message);
        JsonObject object = (JsonObject) gson.toJsonTree(message);
        object.addProperty("type", type);


        String log = "Number of sessions: " + SimpleHandler.sessions.size() +
                "\nmessage: " + object;
        System.out.println(log);
        sendMessageToAll(new TextMessage(object.toString()));
    }

    // tweets
    @Scheduled(fixedRate = 1000)
    public void doTweet() throws Exception {
        sendMessage(MessagesGenerator.getInstance().generateMessage());
    }

    // statistick
    @Scheduled(fixedRate = 1000)
    public void doIt() throws Exception {
        sendMessage(MessagesGenerator.getInstance().generateStatisticMessage());
    }

}