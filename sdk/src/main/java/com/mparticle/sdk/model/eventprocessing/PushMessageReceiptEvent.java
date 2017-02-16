package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PushMessageReceiptEvent extends Event {

    @JsonProperty("payload")
    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public PushMessageReceiptEvent() {
        super(Type.PUSH_MESSAGE_RECEIPT);
    }
}
