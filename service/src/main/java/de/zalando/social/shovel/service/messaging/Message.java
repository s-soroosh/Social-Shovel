package de.zalando.social.shovel.service.messaging;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by SOROOSH on 4/19/15.
 */
public class Message implements Serializable {
    public static class MessageBuilder {
        private final Message msg;

        public MessageBuilder(String content, String provider,String... topics) {
            this.msg = new Message(content, provider,topics);
        }

        public MessageBuilder withLang(String lang) {
            this.msg.language = lang;
            return this;
        }

        public MessageBuilder withUserInfo(String userId, String name, String lastName, String imageUrl) {
            UserInfo userInfo = new UserInfo(userId, name, lastName, imageUrl);
            this.msg.userInfo = userInfo;
            return this;

        }

        public MessageBuilder on(Date date) {
            this.msg.postedDate = date;
            return this;
        }

        public Message build() {
            return this.msg;
        }
    }

    @Id
    private String id;

    private final String[] topics;
    private final String provider;
    private final String content;
    private String language;
    private UserInfo userInfo;
    private Date postedDate;

    private Message(String content, String provider,String... topics) {
        this.topics = topics;
        this.content = content;
        this.provider = provider;
    }

    public Message(String content, String language, UserInfo userInfo, String provider, Date postedDate,String... topics) {
        this.topics = topics;
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

    public String[] getTopics() {
        return topics;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topics=" + Arrays.toString(topics) +
                ", provider='" + provider + '\'' +
                ", content='" + content + '\'' +
                ", language='" + language + '\'' +
                ", userInfo=" + userInfo +
                ", postedDate=" + postedDate +
                '}';
    }
}
