package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

import java.util.List;

public class EventProcessingResponse extends Message {

    @JsonProperty("results")
    private List<EventProcessingResult> processingResults;

    @JsonProperty("suspend_subscription")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean suspendSubscription;

    public List<EventProcessingResult> getProcessingResults() {
        return processingResults;
    }

    public void setProcessingResults(List<EventProcessingResult> processingResults) {
        this.processingResults = processingResults;
    }

    public EventProcessingResponse() {
        super(Type.EVENT_PROCESSING_RESPONSE);
    }
}
