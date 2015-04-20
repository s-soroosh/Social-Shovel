package de.zalando.social.shovel.service.social.twitter;


import de.zalando.social.shovel.service.messaging.Message;
import de.zalando.social.shovel.service.social.specification.MessageTransformer;
import org.springframework.stereotype.Component;
import twitter4j.Status;

/**
 * Created by soroosh on 4/20/15.
 */
@Component
public class TwitterMessageTransformer implements MessageTransformer<Status> {
    @Override
    public Message transform(Status status,String... topics ) {
        Message.MessageBuilder messageBuilder = new Message.MessageBuilder(status.getText(),"TWITTER",topics);
        return messageBuilder.on(status.getCreatedAt())
                .withLang(status.getLang())
                .withUserInfo(String.valueOf(status.getId()), status.getUser().getName(), "", status.getUser().getProfileImageURL())
                .build();
    }
}
