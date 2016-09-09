package com.suggestAPI.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyUtil {

    private static final Logger LOGGER = Logger.getLogger(PropertyUtil.class.getName() );

    public String getValue (String key){
        Properties prop = new Properties();
        String value = "";
        try {
            prop.load(PropertyUtil.class.getClassLoader().getResourceAsStream("endpoints.properties"));
             value = prop.getProperty(key);
        }
        catch (IOException e) {
            LOGGER.severe(String.format("Unable to get the key. %s", e.getMessage()));
        }

        return value;
    }
}
