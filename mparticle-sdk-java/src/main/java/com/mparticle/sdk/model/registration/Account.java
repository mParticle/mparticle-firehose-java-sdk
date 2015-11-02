package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class Account {

    @JsonProperty(value="account_id", required=true)
    private int accountId;

    @JsonProperty("account_settings")
    private Map<String, String> accountSettings;

    /**
     *
     * @return subscription account id
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     *
     * @param accountId subscription account id
     * @return this
     */
    public Account setAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     *
     * @return module subscription settings
     */
    public Map<String, String> getAccountSettings() {
        return accountSettings;
    }

    /**
     *
     * @param accountSettings  module subscription settings
     * @return this
     */
    public Account setAccountSettings(Map<String, String> accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }

    /**
     * Returns module subscription setting value as String.
     *
     * @param name setting name
     * @param required true - throws IllegalArgumentException if the setting is not found
     * @param defaultValue default value returned if setting is not found
     * @return setting value
     */
    public String getStringSetting(String name, Boolean required, String defaultValue) throws IllegalArgumentException {
        String stringValue = accountSettings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return stringValue;
    }

    /**
     * Returns module subscription setting value as Integer.
     *
     * @param name setting name
     * @param required true - throws IllegalArgumentException if the setting is not found
     * @param defaultValue default value returned if setting is not found
     * @return setting value
     */
    public Integer getIntegerSetting(String name, Boolean required, Integer defaultValue) throws IllegalArgumentException {
        String stringValue = accountSettings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Integer.parseInt(stringValue);
    }

    /**
     * Returns module subscription setting value as Double.
     *
     * @param name setting name
     * @param required true - throws IllegalArgumentException if the setting is not found
     * @param defaultValue default value returned if setting is not found
     * @return setting value
     */
    public Double getFloatSetting(String name, Boolean required, Double defaultValue) throws IllegalArgumentException {
        String stringValue = accountSettings.get(name);

        if (stringValue == null) {
            if (required) {
                throw new IllegalArgumentException("The required setting '" + name + "' is not provided.");
            }
            return defaultValue;
        }

        return Double.parseDouble(stringValue);
    }

    /**
     * Returns module subscription setting value as Boolean.
     *
     * @param name setting name
     * @param required true - throws IllegalArgumentException if the setting is not found
     * @param defaultValue default value returned if setting is not found
     * @return setting value
     */
    public Boolean getBooleanSetting(String name, Boolean required, Boolean defaultValue) throws IllegalArgumentException {
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
