package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

import java.util.Map;

/**
 * A response for the audience subscription request.
 *
 * <p>A module can return a set of audience subscription settings that need to be updated.</p>
 */
public final class AudienceSubscriptionResponse extends Message {

    @JsonProperty("audience_subscription_settings")
    private Map<String, String> audienceSubscriptionSettings;

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

    public AudienceSubscriptionResponse() {
        super(Type.AUDIENCE_SUBSCRIPTION_RESPONSE);
    }
}