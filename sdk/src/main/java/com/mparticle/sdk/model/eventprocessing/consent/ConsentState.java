package com.mparticle.sdk.model.eventprocessing.consent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class ConsentState {

    @JsonProperty("gdpr_consent_state")
    private Map<String, GDPRConsent> GDPR;

    public Map<String, GDPRConsent> getGDPR() {
        return GDPR;
    }

    public void setGDPR(Map<String, GDPRConsent> GDPR) {
        this.GDPR = GDPR;
    }
}