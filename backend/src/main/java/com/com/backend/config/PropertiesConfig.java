package com.com.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:i18n/messages.properties")
public class PropertiesConfig {

    @Autowired
    private Environment env;

    public String getConfigValue(String configKey) {
        String s = env.getProperty(configKey);
        return s;
    }
}
