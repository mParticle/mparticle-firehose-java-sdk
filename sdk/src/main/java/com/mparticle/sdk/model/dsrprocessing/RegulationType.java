package com.mparticle.sdk.model.dsrprocessing;

public enum RegulationType {
    UNKNOWN,
    CCPA,
    GDPR;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
