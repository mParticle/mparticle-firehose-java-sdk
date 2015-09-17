package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.Event;

import java.util.List;

public final class EventProcessingRegistration {

    @JsonProperty("description")
    private String description;

    @JsonProperty("settings")
    private List<Setting> settings;

    @JsonProperty("supported_event_types")
    private List<Event.Type> supportedEventTypes;

    @JsonProperty("max_data_age_hours")
    private int maxDataAgeHours = 24;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
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
}
