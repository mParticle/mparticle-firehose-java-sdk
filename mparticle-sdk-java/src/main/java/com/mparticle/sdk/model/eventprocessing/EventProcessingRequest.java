package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

import java.util.List;
import java.util.Map;


public class EventProcessingRequest extends Message {

    public EventProcessingRequest() {
        super(Type.EVENT_PROCESSING_REQUEST);
    }

    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty("subscription")
    private Subscription subscription;

    @JsonProperty("user_identities")
    private List<UserIdentity> userIdentities;

    @JsonProperty("user_attributes")
    private Map<String, String> userAttributes;

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

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<UserIdentity> getUserIdentities() {
        return userIdentities;
    }

    public void setUserIdentities(List<UserIdentity> userIdentities) {
        this.userIdentities = userIdentities;
    }

    public Map<String, String> getUserAttributes() {
        return userAttributes;
    }

    public void setUserAttributes(Map<String, String> userAttributes) {
        this.userAttributes = userAttributes;
    }

    public RuntimeEnvironment getRuntimeEnvironment() {
        return runtimeEnvironment;
    }

    public void setRuntimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
        this.runtimeEnvironment = runtimeEnvironment;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}


