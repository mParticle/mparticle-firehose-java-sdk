package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public final class Audience {

    @JsonProperty(value="audience_id", required=true)
    private int audienceId;

    @JsonProperty(value="audience_name", required=true)
    private String audienceName;

    @JsonProperty("audience_subscription_settings")
    private Map<String, String> audienceSubscriptionSettings;

    @JsonProperty(value="action", required=true)
    private AudienceAction action;

    @JsonProperty("user_attributes")
    private List<UserAttributeAudienceEvent> userAttributes;

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

    /**
     *
     * @return the action taken on this audience
     */
    public AudienceAction getAudienceAction() {
        return action;
    }

    /**
     *
     * @param audienceAction the action taken on this audience
     */
    public void setAudienceAction(AudienceAction audienceAction) {
        this.action = audienceAction;
    }

    /**
     *
     * @return user's attributes for this audience
     */
    public List<UserAttributeAudienceEvent> getUserAttributes() {
        return userAttributes;
    }

    /**
     *
     * @param userAttributes user's attributes for this audience
     */
    public void setUserAttributes(List<UserAttributeAudienceEvent> userAttributes) {
        this.userAttributes = userAttributes;
    }

    public enum AudienceAction {
        ADD,
        DELETE,
        ATTRIBUTE_UPDATE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
