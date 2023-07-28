package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.dsrprocessing.DsrProcessingRequest;

import java.util.List;

/**
 * The DsrProcessingRegistration object should be constructed by Firehose integrations that wish to function
 * as DSR integrations within the mParticle platform. Upon receiving a {@link com.mparticle.sdk.model.registration.ModuleRegistrationRequest},
 * it's up to the Firehose integration to respond with a populated {@link ModuleRegistrationResponse}.
 */
public final class DsrProcessingRegistration {
    
    @JsonProperty("")
    private List<Setting> settings;

    @JsonProperty("supported_dsr_types")
    private List<DsrProcessingRequest.Type> supportedDsrTypes;

    /**
     * Gets this integration's settings.
     * @return a list of {@link Setting}
     */
    public List<Setting> getSettings() {
        return settings;
    }

    /** 
     * Sets the settings registered by this integration.
     * @param settings the list of settings, settings
     * @return this object for method chaining
     */
    public DsrProcessingRegistration setSettings(List<Setting> settings) {
        this.settings = settings;
        return this;
    }

    /**
     * Gets the supported DSR types
     * @return a list with the supported types
     */
    public List<DsrProcessingRequest.Type> getSupportedDsrTypes() {
        return supportedDsrTypes;
    }

    /**
     * Sets the supported DSR types, currently only ERRASURE is valid.
     * @param supportedDsrTypes a list of types
     * @return this object for method chaining
     */
    public DsrProcessingRegistration setSupportedDsrTypes(List<DsrProcessingRequest.Type> supportedDsrTypes) {
        this.supportedDsrTypes = supportedDsrTypes;
        return this;
    }
}