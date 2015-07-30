package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

import java.util.List;

public class EventProcessingResponse extends Message {

    @JsonProperty("errors")
    public Errors errors;

    @JsonProperty("stats")
    public Stats stats;

    public EventProcessingResponse() {
        super(Type.EVENT_PROCESSING_RESPONSE);
    }

    public static class Errors {

        public static class Message {
            public Code code;
            public String message;
        }

        public static enum Code {
            APP_EVENT,
            SESSION_START_EVENT
        }

        public List<Message> messages;

    }

    public static class Stats {
        public void add(Event event) {
        }
    }
}
