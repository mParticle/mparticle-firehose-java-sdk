package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class Audience {

    @JsonProperty(value="audience_id", required=true)
    private int audienceId;

    @JsonProperty(value="audience_name", required=true)
    private String audienceName;

    @JsonProperty("audience_subscription_settings")
    private Map<String, String> audienceSubscriptionSettings;

    /**
     *
     * @return audience unique identifier
     */
    public int getAudienceId() {
        return audienceId;
    }

    /**
     *
     * @param audienceId audience unique identifier
     */
    public void setAudienceId(int audienceId) {
        this.audienceId = audienceId;
    }

    /**
     *
     * @return audience name
     */
    public String getAudienceName() {
        return audienceName;
    }

    /**
     *
     * @param audienceName audience name
     */
    public void setAudienceName(String audienceName) {
        this.audienceName = audienceName;
    }

    /**
     *
     * @return audience subscription settings
     */
    public Map<String, String> getAudienceSubscriptionSettings() {
        return audienceSubscriptionSettings;
    }

    /**
     *
     * @param audienceSubscriptionSettings audience subscription settings
     */
    public void setAudienceSubscriptionSettings(Map<String, String> audienceSubscriptionSettings) {
        this.audienceSubscriptionSettings = audienceSubscriptionSettings;
    }
}
