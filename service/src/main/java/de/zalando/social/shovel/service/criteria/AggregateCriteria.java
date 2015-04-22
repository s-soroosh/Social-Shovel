package de.zalando.social.shovel.service.criteria;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public enum AggregateCriteria {
    LOCATION("location"),
    OPINION("opinion"),
    CLASS("messageClass"),
    PROVIDER("provider");

    private String value;

    public String getValue() {
        return value;
    }
    AggregateCriteria(String val) {
        value = val;
    }
}
