package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.registration.Account;

import java.util.Map;

public final class AudienceSubscriptionRequest extends Message {

    @JsonProperty(value="account", required=true)
    private Account account;

    @JsonProperty(value="audience_id", required=true)
    private int audienceId;

    @JsonProperty(value="audience_name", required=true)
    private String audienceName;

    @JsonProperty("audience_description")
    private String audienceDescription;

    @JsonProperty("audience_subscription_settings")
    private Map<String, String> audienceSubscriptionSettings;

    @JsonProperty("previous_audience_subscription_settings")
    private Map<String, String> previousAudienceSubscriptionSettings;

    @JsonProperty(value="action", required=true)
    private SubscriptionAction action;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(int audienceId) {
        this.audienceId = audienceId;
    }

    public String getAudienceName() {
        return audienceName;
    }

    public void setAudienceName(String audienceName) {
        this.audienceName = audienceName;
    }

    public String getAudienceDescription() {
        return audienceDescription;
    }

    public void setAudienceDescription(String audienceDescription) {
        this.audienceDescription = audienceDescription;
    }

    public Map<String, String> getAudienceSubscriptionSettings() {
        return audienceSubscriptionSettings;
    }

    public void setAudienceSubscriptionSettings(Map<String, String> audienceSubscriptionSettings) {
        this.audienceSubscriptionSettings = audienceSubscriptionSettings;
    }

    public Map<String, String> getPreviousAudienceSubscriptionSettings() {
        return previousAudienceSubscriptionSettings;
    }

    public void setPreviousAudienceSubscriptionSettings(Map<String, String> previousAudienceSubscriptionSettings) {
        this.previousAudienceSubscriptionSettings = previousAudienceSubscriptionSettings;
    }

    public SubscriptionAction getAction() {
        return action;
    }

    public void setAction(SubscriptionAction action) {
        this.action = action;
    }

    public AudienceSubscriptionRequest() {
        super(Message.Type.AUDIENCE_SUBSCRIPTION_REQUEST);
    }

    public enum SubscriptionAction {
        ADD,
        UPDATE,
        REMOVE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

}

