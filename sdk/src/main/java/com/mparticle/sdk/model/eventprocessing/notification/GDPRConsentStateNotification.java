package com.mparticle.sdk.model.eventprocessing.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.consent.GDPRConsent;

public final class GDPRConsentStateNotification extends SystemNotification{

    @JsonProperty(value="purpose", required=true)
    private String purpose;

    @JsonProperty("old_gdpr_consent_state")
    private GDPRConsent oldConsentState;

    @JsonProperty("new_gdpr_consent_state")
    private GDPRConsent newConsentState;

    public GDPRConsentStateNotification() {
        super(SystemNotification.Type.GDPR_CONSENT_STATE);
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public GDPRConsent getOldConsentState() {
        return oldConsentState;
    }

    public void setOldConsentState(GDPRConsent oldConsentState) {
        this.oldConsentState = oldConsentState;
    }

    public GDPRConsent getNewConsentState() {
        return newConsentState;
    }

    public void setNewConsentState(GDPRConsent newConsentState) {
        this.newConsentState = newConsentState;
    }
}