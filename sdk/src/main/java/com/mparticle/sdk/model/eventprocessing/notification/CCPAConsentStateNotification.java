package com.mparticle.sdk.model.eventprocessing.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.consent.CCPAConsent;
import com.mparticle.sdk.model.eventprocessing.consent.ConsentState;

public final class CCPAConsentStateNotification extends SystemNotification{

    @JsonProperty("purpose")
    private String purpose = ConsentState.DEFAULT_CCPA_CONSENT_PURPOSE;

    @JsonProperty("old_ccpa_consent_state")
    private CCPAConsent oldConsentState;

    @JsonProperty("new_ccpa_consent_state")
    private CCPAConsent newConsentState;

    public CCPAConsentStateNotification() {
        super(Type.CCPA_CONSENT_STATE);
    }

    /**
     * Retrieve the consent purpose.
     * It will be set to the default for CCPA, unless it's explicitly overridden via the setter.
     * @return
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Optionally override the default consent purpose for CCPA.
     * @param purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public CCPAConsent getOldConsentState() {
        return oldConsentState;
    }

    public void setOldConsentState(CCPAConsent oldConsentState) {
        this.oldConsentState = oldConsentState;
    }

    public CCPAConsent getNewConsentState() {
        return newConsentState;
    }

    public void setNewConsentState(CCPAConsent newConsentState) {
        this.newConsentState = newConsentState;
    }
}