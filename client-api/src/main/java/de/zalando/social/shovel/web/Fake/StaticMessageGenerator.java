package de.zalando.social.shovel.web.Fake;

import de.zalando.social.shovel.web.websocket.messages.StatisticMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mlitvin on 20/04/15.
 */
public class StaticMessageGenerator {
    private static StaticMessageGenerator instance = null;
    private List<String> countries = null;
    private List<String> languages = null;
    private List<String> categories = null;
    private List<String> networks = null;

    private Random random = new Random();

    public static StaticMessageGenerator getInstance() {
        if (instance == null) instance = new StaticMessageGenerator();
        return instance;
    }

    public StatisticMessage generate() {
        return new StatisticMessage(
                getRandomString(categories),
                getRandomString(networks),
                random.nextInt(100),
                1,
                getRandomString(countries),
                getRandomString(languages)
        );
    }

    public StaticMessageGenerator() {
        // init
        countries = new ArrayList<String>();
        countries.add("DEU");
        countries.add("NLD");
        countries.add("FRA");
        countries.add("CZE");

        languages = new ArrayList<String>();
        languages.add("DE");
        languages.add("EN");

        categories = new ArrayList<String>();
        categories.add("positive");
        categories.add("neutrall");
        categories.add("negative");

        networks = new ArrayList<String>();
        networks.add("fb");
        networks.add("tw");
        networks.add("od");
    }

    private String getRandomString(List<String> list) {
        int length = list.size();
        return list.get(random.nextInt(length));
    }

}
