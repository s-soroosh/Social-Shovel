package de.zalando.social.shovel.web.Fake;

import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.messaging.UserInfo;
import de.zalando.social.shovel.web.websocket.messages.StatisticMessage;

import java.util.*;

/**
 * Created by mlitvin on 20/04/15.
 */
public class MessagesGenerator {
    private static MessagesGenerator instance = null;
    private String[] languages = null;
    private String[] countries = null;
    private String[] categories = null;
    private String[] providers = null;
    private String[] names = null;
    private String[] lastNames = null;
    private String[] satisfactions = null;

    private Random random = new Random();

    public static MessagesGenerator getInstance() {
        if (instance == null) instance = new MessagesGenerator();
        return instance;
    }

    public StatisticMessage generateStatisticMessage() {
        return new StatisticMessage(
                getRandomString(satisfactions),
                getRandomString(providers),
                random.nextInt(100),
                1,
                getRandomString(countries),
                getRandomString(languages)
        );
    }

    public MessagesGenerator() {
        // init
        categories = "shoes,dresses".split(",");
        countries = "DEU,NLD,FRA,CZE".split(",");
        languages = "DE,EN".split(",");
        providers = "TWITTER,FACEBOOK".split(",");
        satisfactions = "positive,negative,neutral".split(",");
        names = "Alex,Mike,Soroosh,Frank,Dmitry,Vasanth,Bob,Jonh,Phil".split(",");
        lastNames = "Hans,Fritz,Mueller,Williams, Jackson, Robinson, Harris, Davis, Brow".split(",");
    }

    private String getRandomString(String[] list) {
        int length = list.length;
        return list[random.nextInt(length)];
    }

    private UserInfo generateUser() {
        return new UserInfo(
                UUID.randomUUID().toString(), //userId
                getRandomString(names),
                getRandomString(lastNames),
                "" // avatarURL
        );
    }
    private String[] generateTopics() {
        return "".split(",");
    }

    private String generateContent() {
       return "Generated content: " + UUID.randomUUID().toString() + ", at: " + new Date();
    }

    private Message.UserOpinion generateUserOpinion() {
        int val = random.nextInt(1000);
        if (val < 3300)
            return Message.UserOpinion.NEUTRAL;
        if (val < 6600)
            return Message.UserOpinion.SATISFIED;
        return Message.UserOpinion.UNSATISFIED;
    }

    public Message generateMessage() {
        Message m =  new Message(
                generateContent(),
                getRandomString(languages),
                generateUser(),
                getRandomString(providers),
                new Date(),
                generateTopics());

        Message.UserOpinion opinion = generateUserOpinion();
        if(opinion != Message.UserOpinion.NEUTRAL)
            m.changeUserOpinion(opinion);

        return m;
    }
}
