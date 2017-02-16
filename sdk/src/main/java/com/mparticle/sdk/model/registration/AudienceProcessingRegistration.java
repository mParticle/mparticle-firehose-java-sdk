package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Audience data stream subscription settings.
 */
public final class AudienceProcessingRegistration {

    @JsonProperty("account_settings")
    private List<Setting> accountSettings;



    @JsonProperty("audience_subscription_settings")
    private List<Setting> audienceConnectionSettings;

    /**
     *
     * @return account subscription settings
     */
    public List<Setting> getAccountSettings() {
        return accountSettings;
    }

    /**
     *
     * @param accountSettings account subscription settings
     * @return this
     */
    public AudienceProcessingRegistration setAccountSettings(List<Setting> accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }

    /**
     *
     * @param audienceSubscriptionSettings audience connection settings
     * @return this
     * @deprecated use {@link #setAudienceConnectionSettings(List)}
     */
    public AudienceProcessingRegistration setAudienceSubscriptionSettings(List<Setting> audienceSubscriptionSettings) {
        this.audienceConnectionSettings = audienceSubscriptionSettings;
        return this;
    }

    /**
     *
     * @param audienceConnectionSettings audience connection settings
     * @return this
     */
    public AudienceProcessingRegistration setAudienceConnectionSettings(List<Setting> audienceConnectionSettings) {
        this.audienceConnectionSettings = audienceConnectionSettings;
        return this;
    }

    /**
     *
     * @return
     */
    public List<Setting> getAudienceConnectionSettings() {
        return audienceConnectionSettings;
    }
}
