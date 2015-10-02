package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class EventProcessingResult {

    @JsonProperty(value="event_id", required=true)
    private UUID eventId;

    @JsonProperty(value="action", required=true)
    private Action action;

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

    public EventProcessingResult(UUID eventId, Action action) {
        this.eventId = eventId;
        this.action = action;
    }
}
