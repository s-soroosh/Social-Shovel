package de.zalando.social.shovel.service.messaging;

import de.zalando.social.shovel.service.messaging.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by vvenkatraman on 20/04/15.
 */
public interface MessageRepository extends MongoRepository<Message, String> {
    public List<Message> findByProvider(String provider);
    public List<Message> findByTopics(String topics);
    public List<Message> findByLanguage(String lang);
    public List<Message> findByUserInfo(String userHandle);
    public List<Message> findByPostedDate(Date date);
}
