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

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Setting> getAccountSettings() {
        return accountSettings;
    }

    public void setAccountSettings(List<Setting> accountSettings) {
        this.accountSettings = accountSettings;
    }

    public List<Event.Type> getSupportedEventTypes() {
        return supportedEventTypes;
    }

    public void setSupportedEventTypes(List<Event.Type> supportedEventTypes) {
        this.supportedEventTypes = supportedEventTypes;
    }

    public int getMaxDataAgeHours() {
        return maxDataAgeHours;
    }

    public void setMaxDataAgeHours(int maxDataAgeHours) {
        this.maxDataAgeHours = maxDataAgeHours;
    }

    public List<RuntimeEnvironment.Type> getSupportedRuntimeEnvironments() {
        return supportedRuntimeEnvironments;
    }

    public void setSupportedRuntimeEnvironments(List<RuntimeEnvironment.Type> supportedRuntimeEnvironments) {
        this.supportedRuntimeEnvironments = supportedRuntimeEnvironments;
    }
}
