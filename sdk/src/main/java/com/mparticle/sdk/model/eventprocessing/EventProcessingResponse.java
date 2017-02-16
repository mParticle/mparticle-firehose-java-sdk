package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

public final class EventProcessingResponse extends Message {

    @JsonProperty("suspend_subscription")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean suspendSubscription;

    public EventProcessingResponse() {
        super(Type.EVENT_PROCESSING_RESPONSE);
    }
}
