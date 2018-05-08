package com.mparticle.sdk.model.eventprocessing.consent;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record of consent under the GDPR.
 */
public final class GDPRConsent {

    @JsonProperty(value = "consented", required = true)
    private boolean consented;

    @JsonProperty("document")
    private String document;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("location")
    private String location;

    @JsonProperty("hardware_id")
    private String hardwareId;

    public boolean isConsented() {
        return consented;
    }

    public String getDocument() {
        return document;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getLocation() {
        return location;
    }

    public String getHardwareId() {
        return hardwareId;
    }

    public GDPRConsent setConsented(boolean consented) {
        this.consented = consented;
        return this;
    }

    public GDPRConsent setDocument(String document) {
        this.document = document;
        return this;
    }

    public GDPRConsent setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public GDPRConsent setLocation(String location) {
        this.location = location;
        return this;
    }

    public GDPRConsent setHardwareId(String hardwareId) {
        this.hardwareId = hardwareId;
        return this;
    }
}