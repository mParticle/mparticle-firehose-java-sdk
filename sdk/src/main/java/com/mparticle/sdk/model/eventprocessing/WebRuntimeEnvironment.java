package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Web Environment
 */
public final class WebRuntimeEnvironment extends RuntimeEnvironment {

    public WebRuntimeEnvironment() {
        super(Type.MOBILEWEB);
    }
}

