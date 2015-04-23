package de.zalando.social.shovel.service.criteria;

import org.springframework.data.annotation.Id;

/**
 * Created by vvenkatraman on 21/04/15.
 */
public class ProviderStats
{
    @Id String id;

    private String provider;
    private int count;

    public String getProvider() {
        return provider;
    }

    public int getCount() {
        return count;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(provider).append("\t").append(count);
        return stringBuilder.toString();
    }

}
