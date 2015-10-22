package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.eventprocessing.RuntimeEnvironment;

import java.util.List;

public final class EventProcessingRegistration {

    @JsonProperty("description")
    private String description;

    @JsonProperty("account_settings")
    private List<Setting> accountSettings;

    @JsonProperty("supported_event_types")
    private List<Event.Type> supportedEventTypes;

    @JsonProperty("supported_runtime_environments")
    private List<RuntimeEnvironment.Type> supportedRuntimeEnvironments;

    @JsonProperty("max_data_age_hours")
    private int maxDataAgeHours = 24;

    public String getDescription() {
        return description;
    }

    public EventProcessingRegistration setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Setting> getAccountSettings() {
        return accountSettings;
    }

    public EventProcessingRegistration setAccountSettings(List<Setting> accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }

    public List<Event.Type> getSupportedEventTypes() {
        return supportedEventTypes;
    }

    public EventProcessingRegistration setSupportedEventTypes(List<Event.Type> supportedEventTypes) {
        this.supportedEventTypes = supportedEventTypes;
        return this;
    }

    public int getMaxDataAgeHours() {
        return maxDataAgeHours;
    }

    public EventProcessingRegistration setMaxDataAgeHours(int maxDataAgeHours) {
        this.maxDataAgeHours = maxDataAgeHours;
        return this;
    }

    public List<RuntimeEnvironment.Type> getSupportedRuntimeEnvironments() {
        return supportedRuntimeEnvironments;
    }

    public EventProcessingRegistration setSupportedRuntimeEnvironments(List<RuntimeEnvironment.Type> supportedRuntimeEnvironments) {
        this.supportedRuntimeEnvironments = supportedRuntimeEnvironments;
        return this;
    }
}
