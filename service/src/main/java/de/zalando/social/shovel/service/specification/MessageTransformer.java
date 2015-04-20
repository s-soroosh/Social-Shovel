package de.zalando.social.shovel.service.specification;

import de.zalando.social.shovel.service.messaging.Message;

/**
 * Created by soroosh on 4/20/15.
 */
public interface MessageTransformer<T> {
    Message transform(T t,String... topics);
}
