package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Account {

    @JsonProperty(value="account_id", required=true)
    private int accountId;

    @JsonProperty("account_settings")
    private Map<String, String> accountSettings;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Map<String, String> getAccountSettings() {
        return accountSettings;
    }

    public void setAccountSettings(Map<String, String> accountSettings) {
        this.accountSettings = accountSettings;
    }

    public String getStringSetting(String name, Boolean required, String defaultValue) {
        String stringValue = accountSettings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return stringValue;
    }

    public Integer getIntegerSetting(String name, Boolean required, Integer defaultValue) {
        String stringValue = accountSettings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Integer.parseInt(stringValue);
    }

    public Double getFloatSetting(String name, Boolean required, Double defaultValue) {
        String stringValue = accountSettings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Double.parseDouble(stringValue);
    }

    public Boolean getBooleanSetting(String name, Boolean required, Boolean defaultValue) {
        String stringValue = accountSettings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Boolean.parseBoolean(stringValue);
    }

}
