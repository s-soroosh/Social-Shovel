package de.zalando.social.shovel.service.criteria;


/**
 * Created by vvenkatraman on 21/04/15.
 */
public enum AggregateCriteria {
    COUNTRY("country"),
    OPINION("userOpinion"),
    CLASS("messageClass"),
    PROVIDER("provider"),
    POSTEDDATE("postedDate"),
    MONTH("month"),
    DAY("dayOfMonth"),
    YEAR("year");

    private String value;

    public String getValue() {
        return value;
    }
    AggregateCriteria(String val) {
        value = val;
    }
}
