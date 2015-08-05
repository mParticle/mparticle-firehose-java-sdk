package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class EventProcessingResult {

    @JsonProperty(value="event_id", required=true)
    private UUID eventId;

    @JsonProperty(value="action", required=true)
    private Action action;

    @JsonProperty("error_code")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

    private EventProcessingResult() {
        // required by serializer
    }

    public enum Action {
        PROCESSED, DISCARDED;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public UUID getEventId() {
        return eventId;
    }

    public Action getAction() {
        return action;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public EventProcessingResult(UUID eventId, Action action, int errorCode, String errorMessage) {
        this.eventId = eventId;
        this.action = action;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public EventProcessingResult(UUID eventId, Action action) {
        this(eventId, action, 0, null);
    }
}
