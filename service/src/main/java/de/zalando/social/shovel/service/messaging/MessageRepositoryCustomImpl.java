package de.zalando.social.shovel.service.messaging;

import com.mongodb.DBObject;
import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import de.zalando.social.shovel.service.criteria.CriteriaStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {


    private final MongoOperations operations;

    @Autowired
    private MongoTemplate mongoTemplate;


    public MessageRepositoryCustomImpl(MongoOperations operations) {
        if(operations == null) {
            throw new IllegalArgumentException("Operations cannot be null!");
        }
        this.operations = operations;
    }

    @Override
    public DBObject aggrMsg(AggregateCriteria criteria) {
//        Aggregation aggregation = Aggregation.newAggregation(Message.class,
//                group(criteria.getValue()).count().as("n")
//        );
//
//        AggregationResults<CriteriaStats> results = operations.aggregate(aggregation, "message", CriteriaStats.class);

//        return results.getMappedResults();
        GroupByResults<Message> results = mongoTemplate.group("group_test_collection",
                GroupBy.key("provider").initialDocument("{ count: 0 }").reduceFunction("function(doc, prev) { prev.count += 1 }"),
                Message.class);

        DBObject res = results.getRawResults();
        return res;
    }
}
