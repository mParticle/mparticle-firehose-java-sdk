package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class EventProcessingRegistration {

    @JsonProperty("description")
    private String description;

    @JsonProperty("settings")
    private List<Setting> settings;

    @JsonProperty("max_data_age_hours")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int maxDataAgeHours;

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

    public int getMaxDataAgeHours() {
        return maxDataAgeHours;
    }

    public void setMaxDataAgeHours(int maxDataAgeHours) {
        this.maxDataAgeHours = maxDataAgeHours;
    }
}
