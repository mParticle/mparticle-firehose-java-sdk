package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

import java.util.Map;

public final class AudienceSubscriptionResponse extends Message {

    @JsonProperty("audience_subscription_settings")
    private Map<String, String> audienceSubscriptionSettings;

    public Map<String, String> getAudienceSubscriptionSettings() {
        return audienceSubscriptionSettings;
    }

    public void setAudienceSubscriptionSettings(Map<String, String> audienceSubscriptionSettings) {
        this.audienceSubscriptionSettings = audienceSubscriptionSettings;
    }

    public AudienceSubscriptionResponse() {
        super(Type.AUDIENCE_SUBSCRIPTION_RESPONSE);
    }
}