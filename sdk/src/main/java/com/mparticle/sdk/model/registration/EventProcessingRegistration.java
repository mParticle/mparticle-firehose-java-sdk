package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.eventprocessing.RuntimeEnvironment;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;

import java.util.List;

/**
 * The EventProcessingRegistration object should be constructed by Firehose integrations that wish to function
 * as event-integrations within the mParticle platform. Upon receiving a {@link com.mparticle.sdk.model.registration.ModuleRegistrationRequest},
 * it's up to the Firehose integration to response with a populated {@link ModuleRegistrationResponse}.
 */
public final class EventProcessingRegistration {

    @JsonProperty("account_settings")
    private List<Setting> accountSettings;

    @JsonProperty("event_connection_settings")
    private List<Setting> connectionSettings;

    @JsonProperty("supported_event_types")
    private List<Event.Type> supportedEventTypes;

    @JsonProperty("supported_runtime_environments")
    private List<RuntimeEnvironment.Type> supportedRuntimeEnvironments;

    @JsonProperty("supported_system_notification_types")
    private List<SystemNotification.Type> supportedSystemNotifications;

    @JsonProperty("max_data_age_hours")
    private int maxDataAgeHours = 24;

    /**
     * Gets the account-level settings registered by this integration.
     *
     * mParticle integrations may be configured with two different sets of settings:
     * - Account-level settings, meant to be reused across platforms and/or apps
     * - Subscription-level settings, meant to change specific behaviors for each instance of an output/integration
     *
     * As a Firehose integration developer, it's up to *you* to define which settings should be account-level,
     * and which should be configured on a per-integration-instance basis.
     *
     * @see #getConnectionSettings()  to get the integration-specific settings registered for this integration
     * @return account settings registered by the integration
     */
    public List<Setting> getAccountSettings() {
        return accountSettings;
    }

    /**
     * Register the account-level settings of this integration.
     *
     * mParticle integrations may be configured with two different sets of settings:
     * - Account-level settings, meant to be reused across platforms and/or apps
     * - Subscription-level settings, meant to change specific behaviors for each instance of an output/integration
     *
     * As a Firehose integration developer, it's up to *you* to define which settings should be account-level,
     * and which should be configured on a per-integration-instance basis.
     *
     * @see #setConnectionSettings(List)  to register integration-specific settings for this integration
     *
     * @param accountSettings account-level settings to register for this integration
     * @return returns this EventProcessingRegistration object for method chaining
     */
    public EventProcessingRegistration setAccountSettings(List<Setting> accountSettings) {
        this.accountSettings = accountSettings;
        return this;
    }

    /**
     * Gets the subscription-level settings registered by this integration.
     *
     * mParticle integrations may be configured with two different sets of settings:
     * - Account-level settings, meant to be reused across platforms and/or apps
     * - Subscription-level settings, meant to change specific behaviors for each instance of an output/integration
     *
     * As a Firehose integration developer, it's up to *you* to define which settings should be account-level,
     * and which should be configured on a per-integration-instance basis.
     *
     * @see #getAccountSettings() to get the account-level settings registered for this integration
     * @return account settings registered by the integration
     */
    public List<Setting> getConnectionSettings() {
        return connectionSettings;
    }

    /**
     * Register the specific connection-level settings of this integration.
     *
     * mParticle integrations may be configured with two different sets of settings:
     * - Account-level settings, meant to be reused across platforms and/or apps
     * - Connection-level settings, meant to change specific behaviors for each instance of an output/integration
     *
     * As a Firehose integration developer, it's up to *you* to define which settings should be account-level,
     * and which should be configured on a per-integration-instance basis.
     *
     * @see  #setAccountSettings(List) to register account-level settings for this integration
     *
     * @param connectionSettings subscription-level settings to register for this integration
     * @return returns this EventProcessingRegistration object for method chaining
     */
    public EventProcessingRegistration setConnectionSettings(List<Setting> connectionSettings) {
        this.connectionSettings = connectionSettings;
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
     * @return returns this EventProcessingRegistration object for method chaining
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
     * @return returns this EventProcessingRegistration object for method chaining
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
     *
     *
     * @param supportedRuntimeEnvironments supported mobile platforms
     * @return returns this EventProcessingRegistration object for method chaining
     */
    public EventProcessingRegistration setSupportedRuntimeEnvironments(List<RuntimeEnvironment.Type> supportedRuntimeEnvironments) {
        this.supportedRuntimeEnvironments = supportedRuntimeEnvironments;
        return this;
    }

    public List<SystemNotification.Type> getSupportedSystemNotifications() {
        return supportedSystemNotifications;
    }

    public EventProcessingRegistration setSupportedSystemNotifications(List<SystemNotification.Type> supportedSystemNotifications) {
        this.supportedSystemNotifications = supportedSystemNotifications;
        return this;
    }
}
