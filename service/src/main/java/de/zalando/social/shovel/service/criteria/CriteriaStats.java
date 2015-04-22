package de.zalando.social.shovel.service.criteria;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public class CriteriaStats {
    @Id String id;
    String criteria;
    @Field("n") int count;

    public String getCriteria() {
        return criteria;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(criteria).append("\t").append(count);
        return stringBuilder.toString();
    }

}
