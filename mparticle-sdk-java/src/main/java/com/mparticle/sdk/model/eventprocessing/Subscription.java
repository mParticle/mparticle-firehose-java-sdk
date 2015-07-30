package com.mparticle.sdk.model.eventprocessing;

import java.util.Map;
import java.util.UUID;

public class Subscription {

    public UUID subscriptionId;

    public Map<String, String> settings;

    public String getStringSetting(String name, Boolean required, String defaultValue) {
        String stringValue = settings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return stringValue;
    }

    public Boolean getBooleanSetting(String name, Boolean required, Boolean defaultValue) {
        String stringValue = settings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Boolean.parseBoolean(stringValue);
    }

}
