package com.mparticle.sdk.model.eventprocessing.consent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public final class ConsentState {

    /**
     * The default consent purpose for CCPA.
     * For more, reference: https://docs.mparticle.com/guides/consent-management#consent-properties
     */
    public static final String DEFAULT_CCPA_CONSENT_PURPOSE = "data_sale_opt_out";

    @JsonProperty("gdpr_consent_state")
    private Map<String, GDPRConsent> GDPR;

    @JsonProperty("ccpa_consent_state")
    private Map<String, CCPAConsent> CCPA;

    public Map<String, GDPRConsent> getGDPR() {
        return GDPR;
    }

    public void setGDPR(Map<String, GDPRConsent> GDPR) {
        this.GDPR = GDPR;
    }

    public Map<String, CCPAConsent> getCCPA() {
        return CCPA;
    }

    /**
     * Set-up the map with the default CCPA consent purpose.
     * @param CCPA
     */
    public void setCCPA(CCPAConsent CCPA) {
        Map<String, CCPAConsent> ccpaConsentMap = new HashMap<>();
        ccpaConsentMap.put(DEFAULT_CCPA_CONSENT_PURPOSE, CCPA);

        this.setCCPA(ccpaConsentMap);
    }

    public void setCCPA(Map<String, CCPAConsent> CCPA) {
        this.CCPA = CCPA;
    }
}