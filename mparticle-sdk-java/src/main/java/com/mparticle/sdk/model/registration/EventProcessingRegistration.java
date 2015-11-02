package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.eventprocessing.RuntimeEnvironment;

import java.util.List;

/**
 * Event data stream subscription settings.
 */
public final class EventProcessingRegistration {

    @JsonProperty("account_settings")
    private List<Setting> accountSettings;

    @JsonProperty("supported_event_types")
    private List<Event.Type> supportedEventTypes;

    @JsonProperty("supported_runtime_environments")
    private List<RuntimeEnvironment.Type> supportedRuntimeEnvironments;

    @JsonProperty("max_data_age_hours")
    private int maxDataAgeHours = 24;

    /**
     *
     * @return account settings required by the module
     */
    public List<Setting> getAccountSettings() {
        return accountSettings;
    }

    /**
     *
     * @param accountSettings account settings required by the module
     * @return this
     */
    public EventProcessingRegistration setAccountSettings(List<Setting> accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }

    /**
     *
     * @return requested event types
     */
    public List<Event.Type> getSupportedEventTypes() {
        return supportedEventTypes;
    }

    /**
     *
     * @param supportedEventTypes requested event types
     * @return this
     */
    public EventProcessingRegistration setSupportedEventTypes(List<Event.Type> supportedEventTypes) {
        this.supportedEventTypes = supportedEventTypes;
        return this;
    }

    /**
     *
     * @return acceptable age of the incoming events
     */
    public int getMaxDataAgeHours() {
        return maxDataAgeHours;
    }

    /**
     *
     * @param maxDataAgeHours acceptable age of the incoming events
     * @return this
     */
    public EventProcessingRegistration setMaxDataAgeHours(int maxDataAgeHours) {
        this.maxDataAgeHours = maxDataAgeHours;
        return this;
    }

    /**
     *
     * @return supported mobile platforms
     */
    public List<RuntimeEnvironment.Type> getSupportedRuntimeEnvironments() {
        return supportedRuntimeEnvironments;
    }

    /**
     *
     * @param supportedRuntimeEnvironments supported mobile platforms
     * @return this
     */
    public EventProcessingRegistration setSupportedRuntimeEnvironments(List<RuntimeEnvironment.Type> supportedRuntimeEnvironments) {
        this.supportedRuntimeEnvironments = supportedRuntimeEnvironments;
        return this;
    }
}
