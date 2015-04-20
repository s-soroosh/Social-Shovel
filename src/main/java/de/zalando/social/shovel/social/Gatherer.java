package de.zalando.social.shovel.social;

import java.util.List;
import java.util.Map;

/**
 * Created by SOROOSH on 4/3/15.
 */
public interface Gatherer {
    List<SocialMessage> gather(Map<String,String> info);

}
