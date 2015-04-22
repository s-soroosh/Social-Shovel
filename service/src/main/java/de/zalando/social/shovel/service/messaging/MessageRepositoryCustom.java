package de.zalando.social.shovel.service.messaging;

import com.mongodb.DBObject;
import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import de.zalando.social.shovel.service.criteria.CriteriaStats;

import java.util.List;
import java.util.Map;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public interface MessageRepositoryCustom {
    public DBObject aggrMsg(AggregateCriteria criteria);
}
