package de.zalando.social.shovel.service.messaging;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by soroosh on 4/22/15.
 */
@Service
public class LangToCountryTransformer {
    private final Map<String,String> langToCountryMap = new HashMap<>();

    public LangToCountryTransformer(){
            langToCountryMap.put("en","United Kingdom");
            langToCountryMap.put("ja","Japan");
            langToCountryMap.put("pl","Poland");
            langToCountryMap.put("pt","Portugal");
            langToCountryMap.put("fr","France");
            langToCountryMap.put("in","Indonesia");
            langToCountryMap.put("cy","Cyprus");
            langToCountryMap.put("nl","Netherlands");
            langToCountryMap.put("de","Deutschland");
            langToCountryMap.put("no","Norway");
            langToCountryMap.put("nb","Norway");
            langToCountryMap.put("nn","Norway");
            langToCountryMap.put("se","Sweden");
            langToCountryMap.put("lv","Latvia");
            langToCountryMap.put("sl","Slovenia");
            langToCountryMap.put("ko","Korea");
            langToCountryMap.put("und","Undetermined");


    }

    public String transform(String langName){
        return langToCountryMap.get(langName);

    }

    public static void main(String[] args) {
        System.out.println(new LangToCountryTransformer().transform(""));
    }
}
