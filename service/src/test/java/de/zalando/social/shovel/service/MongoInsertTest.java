package de.zalando.social.shovel.service;

import de.zalando.social.shovel.service.configuration.ServiceConfiguration;
import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.messaging.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vvenkatraman on 4/21/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceConfiguration.class)
@EnableJms
public class MongoInsertTest {

    @Autowired
    private MessageRepository repository;

    @Test
    public void testMongoAggregationByMultipleCriteria(){

        String[] providers = new String[] {"twitter","facebook"};
        int[] providerCount = new int[] {18, 36}; // 18 twitter messages and 36 fb messages should be inserted
        for(int i=0;i<providers.length;i++) {
            int insertCount = (i+1);
            for(int j=0;j<3*insertCount;j++) {
                String[] dates = new String[] {"01/4/2015","02/4/2015","03/3/2015","04/3/2015","03/2/2015","04/2/2015"};
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

                for(String dt : dates) {
                    Message.MessageBuilder messageBuilder = new Message.MessageBuilder("simple content", providers[i], "simple");
                    Date date = null;
                    try{
                        date = sdf.parse(dt);
                    } catch(ParseException pse) {
                        date = new Date();
                    }

                    Message msg = messageBuilder.on(date).at("Germany").build();
                    if(j % 2 == 0) {
                        msg.changeUserOpinion(Message.UserOpinion.SATISFIED);
                    } else {
                        msg.changeUserOpinion(Message.UserOpinion.UNSATISFIED);
                    }
                    repository.insert(msg);
                }
            }

            // check whether the required documents were inserted successfully.
            Assert.assertEquals(providerCount[i], repository.findByProvider(providers[i]).size());
        }

        AggregateCriteria[] criterias = new AggregateCriteria[] {AggregateCriteria.PROVIDER, AggregateCriteria.MONTH, AggregateCriteria.OPINION};
        List<Map<String, Object>> results = repository.aggrCountByCriterias(criterias);
        for(Map<String, Object> map : results) {
            for(AggregateCriteria criteria : criterias) {
                Assert.assertEquals(true, map.containsKey(criteria.getValue()));
            }
            Assert.assertEquals(true, map.containsKey("count"));
        }
    }
}