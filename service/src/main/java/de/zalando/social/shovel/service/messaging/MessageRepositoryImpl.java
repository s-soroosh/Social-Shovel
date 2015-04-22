package de.zalando.social.shovel.service.messaging;

import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Map<String, Double> aggrCount(AggregateCriteria criteria) {
        return compute(criteria.getValue());
    }

    private Map<String, Double> compute(String criteria) {
        GroupByResults<HashMap> groupedResults = mongoTemplate.group("message",
                GroupBy.key(criteria).initialDocument("{ count: 0 }").reduceFunction("function(doc, prev) { prev.count += 1 }"),
                HashMap.class);

        System.out.println(groupedResults.getRawResults());
        HashMap<String, Double> results = new HashMap<>();

        Iterator<HashMap> it = groupedResults.iterator();
        while(it.hasNext()) {
            HashMap<String, Object> map = it.next();
            System.out.println(map);
            results.put(map.get(criteria).toString(), (Double)map.get("count"));
        }
        System.out.println(results);
        return results;
    }
}
