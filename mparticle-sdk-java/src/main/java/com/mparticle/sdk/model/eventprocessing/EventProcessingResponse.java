package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

import java.util.List;

public class EventProcessingResponse extends Message {

    @JsonProperty("results")
    public List<EventProcessingResult> processingResults;

    public EventProcessingResponse() {
        super(Type.EVENT_PROCESSING_RESPONSE);
    }
}
