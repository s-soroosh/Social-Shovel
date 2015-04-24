package de.zalando.social.shovel.service.messaging;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBObject;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;

    // STEP 1: Construct the query using all the criterias provided.
    private BasicDBObject constructCriteriaQuery(AggregateCriteria... criterias) {
        BasicDBObject dbObject = new BasicDBObject();
        for(AggregateCriteria it : criterias) {
            if(it == AggregateCriteria.MONTH || it == AggregateCriteria.DAY) {
                dbObject.append(it.getValue(), new BasicDBObject("$"+it.getValue(), "$" + AggregateCriteria.POSTEDDATE.getValue()));
            } else {
                dbObject.append(it.getValue(), "$"+it.getValue());
            }

        }
        return dbObject;
    }

    // STEP 2: Insert the criteria object into pipeline to be executed...
    private BasicDBObject constructAggregationPipeline(BasicDBObject dbObject) {
        BasicDBList pipeline = new BasicDBList();
        pipeline.add(
                new BasicDBObject("$group",
                        new BasicDBObject("_id", dbObject)
                                .append("count", new BasicDBObject("$sum", 1))
                )
        );

        BasicDBObject aggregation = new BasicDBObject("aggregate","message")
                .append("pipeline", pipeline);

        return aggregation;
    }

    private BasicDBObject generateCommand(AggregateCriteria... criterias) {
        BasicDBObject dbObject = null;
        dbObject = constructCriteriaQuery(criterias);
        BasicDBObject aggregationPipeline = constructAggregationPipeline(dbObject);
        return aggregationPipeline;
    }

    private List<Map<String, Object>> buildResult(CommandResult commandResult, AggregateCriteria... criterias) {
        List<Map<String, Object>> finalResults = new ArrayList<>();
        BasicDBList list = (BasicDBList)commandResult.get("result");
        for(int i=0;i<list.size();i++) {
            BasicDBObject dbo = (BasicDBObject)list.get(i);
            BasicDBObject dbIdObj = (BasicDBObject)dbo.get("_id");
            Map<String, Object> map = new HashMap<>();
            for(AggregateCriteria criteria : criterias) {
                Object key = dbIdObj.get(criteria.getValue());
                String keyValue = "";
                if(key != null) {
                    keyValue = key.toString();
                }
                map.put(criteria.getValue(), keyValue);
            }
            map.put("count", dbo.get("count"));
            finalResults.add(map);
        }
        return finalResults;
    }

    private List<Map<String, Object>> compute(AggregateCriteria... criterias) {
        BasicDBObject aggregation = generateCommand(criterias);
        CommandResult commandResult = mongoOperations.executeCommand(aggregation);
        return buildResult(commandResult, criterias);
    }

    @Override
    public List<Map<String, Object>> aggrCountByCriterias(AggregateCriteria... criterias) {
        return compute(criterias);
    }
}
