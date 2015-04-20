package de.zalando.social.shovel.service.messaging;

/**
 * Created by soroosh on 4/20/15.
 */
public class UserInfo {
    private final String userId;
    private final String name;
    private final String lastName;
    private final String imageUrl;

    public UserInfo(String userId, String name, String lastName, String imageUrl) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
