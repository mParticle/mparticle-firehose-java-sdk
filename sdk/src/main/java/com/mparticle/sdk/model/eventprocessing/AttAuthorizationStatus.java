package com.mparticle.sdk.model.eventprocessing;

public enum AttAuthorizationStatus {
    AUTHORIZED,
    DENIED,
    NOT_DETERMINED,
    RESTRICTED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
