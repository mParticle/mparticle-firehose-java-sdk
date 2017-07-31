package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Web Environment
 */
public final class WebRuntimeEnvironment extends RuntimeEnvironment {

    @JsonProperty("identities")
    private List<DeviceIdentity> Identities;

    public List<DeviceIdentity> getIdentities() {
        return Identities;
    }

    public void setIdentities(List<DeviceIdentity> identities) {
        Identities = identities;
    }

    public WebRuntimeEnvironment() {
        super(Type.MOBILEWEB);
    }
}

