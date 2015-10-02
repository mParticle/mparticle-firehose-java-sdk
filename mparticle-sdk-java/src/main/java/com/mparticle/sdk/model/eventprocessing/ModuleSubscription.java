package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ModuleSubscription {

    @JsonProperty(value="id", required=true)
    private int Id;

    @JsonProperty("settings")
    private Map<String, String> settings;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

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

    public Integer getIntegerSetting(String name, Boolean required, Integer defaultValue) {
        String stringValue = settings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Integer.parseInt(stringValue);
    }

    public Double getFloatSetting(String name, Boolean required, Double defaultValue) {
        String stringValue = settings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Double.parseDouble(stringValue);
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
