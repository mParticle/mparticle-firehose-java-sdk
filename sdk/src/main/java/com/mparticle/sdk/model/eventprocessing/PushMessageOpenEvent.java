package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PushMessageOpenEvent extends Event {

    @JsonProperty("payload")
    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public PushMessageOpenEvent() {
        super(Type.PUSH_MESSAGE_OPEN);
    }
}
