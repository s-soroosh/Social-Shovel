package de.zalando.social.shovel.service.messaging;

import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;

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

    @Override
    public Map<String, Double> aggrCountByMultipleCriterias(AggregateCriteria... criterias) {
        String[] strCriterias = new String[criterias.length];
        int i=0;
        for(AggregateCriteria criteria : criterias) {
            strCriterias[i++] = criteria.getValue();
        }
        return compute(strCriterias);
    }

    private Map<String, Double> compute(String... criterias) {
        GroupByResults<HashMap> groupedResults = mongoTemplate.group("message",
                GroupBy.key(criterias).initialDocument("{ count: 0 }").reduceFunction("function(doc, prev) { prev.count += 1 }"),
                HashMap.class);


        HashMap<String, Double> results = new HashMap<>();

        Iterator<HashMap> it = groupedResults.iterator();
        while(it.hasNext()) {
            HashMap<String, Object> map = it.next();
            StringBuffer criteriaBuff = new StringBuffer();
            for(String criteria : criterias) {
                criteriaBuff.append(map.get(criteria).toString()).append("_");
            }
            results.put(criteriaBuff.substring(0, criteriaBuff.length()-1), (Double)map.get("count"));
        }

        return results;
    }
}
