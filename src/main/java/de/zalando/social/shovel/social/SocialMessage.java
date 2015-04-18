package de.zalando.social.shovel.social;

/**
 * Created by SOROOSH on 4/3/15.
 */
public final class SocialMessage {
    private final String message;
    private final String network;

    public SocialMessage(String message, String network) {

        this.message = message;
        this.network = network;
    }

    public String getMessage() {
        return message;
    }

    public String getNetwork() {
        return network;
    }


}
