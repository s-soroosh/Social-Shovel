package de.zalando.social.shovel.service.messaging;

import java.io.Serializable;

/**
 * Created by SOROOSH on 4/19/15.
 */
public class Message implements Serializable {
    public String getName() {
        return name;
    }

    private final String name;

    public Message(String name){
        this.name = name;
    }
}
