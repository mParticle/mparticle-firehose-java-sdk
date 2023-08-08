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
    
    @JsonProperty("account_settings")
    private List<Setting> accountSettings;

    @JsonProperty("supported_dsr_types")
    private List<DsrProcessingRequest.Type> supportedDsrTypes;

    @JsonProperty("domain")
    private String domain;

    /**
     *
     * @return account settings
     */
    public List<Setting> getAccountSettings() {
        return accountSettings;
    }

    /**
     *
     * @param accountSettings account settings
     * @return this
     */
    public DsrProcessingRegistration setAccountSettings(List<Setting> accountSettings) {
        this.accountSettings = accountSettings;
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
     * Sets the supported DSR types, currently only ERASURE is valid.
     * @param supportedDsrTypes a list of types
     * @return this object for method chaining
     */
    public DsrProcessingRegistration setSupportedDsrTypes(List<DsrProcessingRequest.Type> supportedDsrTypes) {
        this.supportedDsrTypes = supportedDsrTypes;
        return this;
    }

    /**
     *
     * @return the domain for request callbacks
     */
    public String getDomain() {
        return domain;
    }

    /**
     *
     * @param domain the domain for request callbacks
     * @return this object for method chaining
     */
    public DsrProcessingRegistration setDomain(String domain) {
        this.domain = domain;
        return this;
    }
}