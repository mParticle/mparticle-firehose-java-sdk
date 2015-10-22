package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class AudienceProcessingRegistration {

    @JsonProperty("description")
    private String description;

    @JsonProperty("account_settings")
    private List<Setting> accountSettings;

    @JsonProperty("audience_subscription_settings")
    private List<Setting> audienceSubscriptionSettings;

    public String getDescription() {
        return description;
    }

    public AudienceProcessingRegistration setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Setting> getAccountSettings() {
        return accountSettings;
    }

    public AudienceProcessingRegistration setAccountSettings(List<Setting> accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }

    public List<Setting> getAudienceSubscriptionSettings() {
        return audienceSubscriptionSettings;
    }

    public AudienceProcessingRegistration setAudienceSubscriptionSettings(List<Setting> audienceSubscriptionSettings) {
        this.audienceSubscriptionSettings = audienceSubscriptionSettings;
        return this;
    }
}
