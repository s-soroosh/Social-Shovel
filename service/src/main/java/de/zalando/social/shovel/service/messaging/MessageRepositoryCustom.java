package de.zalando.social.shovel.service.messaging;

import com.mongodb.DBObject;
import de.zalando.social.shovel.service.criteria.AggregateCriteria;

import java.util.List;
import java.util.Map;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public interface MessageRepositoryCustom {
    public List<Map<String, Object>> aggrCountByCriterias(AggregateCriteria... criterias);
}
