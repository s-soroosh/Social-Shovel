package de.zalando.social.shovel.web.websocket.messages;

/**
 * Created by mlitvin on 20/04/15.
 */
public class StatisticMessage {
    private final String category;
    private final String network;
    private final Integer value;
    private final Integer count;
    private final String country;
    private final String language;

    public StatisticMessage(String category, String network, Integer value,
                            Integer count, String country, String language) {
        this.category = category;
        this.network = network;
        this.value = value;
        this.count = count;
        this.country = country;
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public String getNetwork() {
        return network;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getCount() {
        return count;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "StatisticMessage{" +
                "category='" + category + '\'' +
                ", network='" + network + '\'' +
                ", value=" + value +
                ", count=" + count +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
