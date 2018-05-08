package com.mparticle.sdk.model.eventprocessing.notification;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mparticle.sdk.model.eventprocessing.EventProcessingRequest;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="gdpr_consent_state", value=GDPRConsentStateNotification.class)
})
/**
 * Class representing a profile or system state change within the mParticle platform.
 */
public abstract class SystemNotification {

    private final Type type;
    private EventProcessingRequest request;

    public SystemNotification(Type notificationType) {
        this.type = notificationType;
    }

    public Type getType() {
        return type;
    }

    public void setRequest(EventProcessingRequest request) {
        this.request = request;
    }

    public EventProcessingRequest getRequest() {
        return request;
    }

    /**
     * Event types.
     */
    public enum Type {
        /**
         * This represents when a user's Consent State has been updated.
         */
        GDPR_CONSENT_STATE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}