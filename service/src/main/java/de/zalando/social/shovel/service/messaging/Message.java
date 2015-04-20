package de.zalando.social.shovel.service.messaging;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by SOROOSH on 4/19/15.
 */
public class Message implements Serializable {
    private final String topic;
    private final String source;
    private final String content;
    private final String language;
    private final UserInfo userInfo;
    private final String provider;
    //TODO: Maybe a builder pattern is better here.
    public Message(String topic, String source, String content, String language, UserInfo userInfo, String provider, Date postedDate) {

        this.topic = topic;
        this.source = source;
        this.content = content;
        this.language = language;
        this.userInfo = userInfo;
        this.provider = provider;
        this.postedDate = postedDate;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public String getProvider() {
        return provider;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getLanguage() {
        return language;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }

    public String getTopic() {
        return topic;
    }

    private final Date postedDate;




}
