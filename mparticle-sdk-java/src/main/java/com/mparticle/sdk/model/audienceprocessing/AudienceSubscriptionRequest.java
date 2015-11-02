package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.registration.Account;

import java.util.Map;

/**
 * This message is sent when mParticle customers add, update, or remove audience subscription.
 */
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

    /**
     *
     * @return module subscription account
     */
    public Account getAccount() {
        return account;
    }

    /**
     *
     * @param account module subscription account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

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
     * @return audience description
     */
    public String getAudienceDescription() {
        return audienceDescription;
    }

    /**
     *
     * @param audienceDescription audience description
     */
    public void setAudienceDescription(String audienceDescription) {
        this.audienceDescription = audienceDescription;
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
     * @return audience subscription settings
     */
    public Map<String, String> getPreviousAudienceSubscriptionSettings() {
        return previousAudienceSubscriptionSettings;
    }

    /**
     *
     * @param previousAudienceSubscriptionSettings audience subscription settings
     */
    public void setPreviousAudienceSubscriptionSettings(Map<String, String> previousAudienceSubscriptionSettings) {
        this.previousAudienceSubscriptionSettings = previousAudienceSubscriptionSettings;
    }

    /**
     *
     * @return action
     */
    public SubscriptionAction getAction() {
        return action;
    }

    /**
     *
     * @param action action
     */
    public void setAction(SubscriptionAction action) {
        this.action = action;
    }

    public AudienceSubscriptionRequest() {
        super(Message.Type.AUDIENCE_SUBSCRIPTION_REQUEST);
    }

    /**
     * Audience subscription actions.
     */
    public enum SubscriptionAction {
        /**
         * User added a new audience subscription.
         */
        ADD,
        /**
         * User updated current subscription settings or name.
         */
        UPDATE,
        /**
         * User removed a subscription.
         */
        REMOVE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

}

