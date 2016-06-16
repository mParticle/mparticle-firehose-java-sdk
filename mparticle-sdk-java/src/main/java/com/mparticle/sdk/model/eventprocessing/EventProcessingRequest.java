package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.registration.Account;

import java.util.List;
import java.util.Map;

/**
 * This message contains application events logged by the mobile SDK's.
 */
public final class EventProcessingRequest extends Message {

    public EventProcessingRequest() {
        super(Type.EVENT_PROCESSING_REQUEST);
    }

    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty(value="account", required=true)
    private Account account;

    @JsonProperty("user_identities")
    private List<UserIdentity> userIdentities;

    @JsonProperty("user_attributes")
    private Map<String, String> userAttributes;

    @JsonProperty("user_attribute_lists")
    private Map<String, List<String>> userAttributeLists;

    @JsonProperty("runtime_environment")
    private RuntimeEnvironment runtimeEnvironment;

    @JsonProperty("events")
    private List<Event> events;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

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
     * @return user identities
     */
    public List<UserIdentity> getUserIdentities() {
        return userIdentities;
    }

    /**
     *
     * @param userIdentities user identities
     */
    public void setUserIdentities(List<UserIdentity> userIdentities) {
        this.userIdentities = userIdentities;
    }

    /**
     *
     * @return user attributes
     */
    public Map<String, String> getUserAttributes() {
        return userAttributes;
    }

    /**
     *
     * @param userAttributes user attributes
     */
    public void setUserAttributes(Map<String, String> userAttributes) {
        this.userAttributes = userAttributes;
    }

    /**
     *
     * @return application execution environment
     */
    public RuntimeEnvironment getRuntimeEnvironment() {
        return runtimeEnvironment;
    }

    /**
     *
     * @param runtimeEnvironment application execution environment
     */
    public void setRuntimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
        this.runtimeEnvironment = runtimeEnvironment;
    }

    /**
     *
     * @return list of events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     *
     * @param events list of events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     *
     * @return a map of user attribute lists
     */
    public Map<String, List<String>> getUserAttributeLists() {
        return userAttributeLists;
    }

    /**
     *
     * @param userAttributeLists
     */
    public void setUserAttributeLists(Map<String, List<String>> userAttributeLists) {
        this.userAttributeLists = userAttributeLists;
    }
}


