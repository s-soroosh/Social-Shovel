package de.zalando.social.shovel.service.messaging;

import de.zalando.social.shovel.service.criteria.AggregateCriteria;
import de.zalando.social.shovel.service.criteria.CriteriaStats;
import de.zalando.social.shovel.service.messaging.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by vvenkatraman on 20/04/15.
 */
public interface MessageRepository extends MongoRepository<Message, String>, MessageRepositoryCustom {
    public List<Message> findByProvider(String provider);
    public List<Message> findByTopics(String topics);
    public List<Message> findByLanguage(String lang);
    public List<Message> findByUserInfo(String userHandle);
    public List<Message> findByPostedDate(Date date);
    public List<Message> findByCountry(String location);
    public List<Message> findByUserOpinion(Message.UserOpinion opinion);
    public List<Message> findByClass(String messageClass);

//    @Query(value = "{'$group':{'_id':?0,'count':{'$sum:1}}}")
//    public List<CriteriaStats> findByCriteria(String criteria);
}
