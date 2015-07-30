package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="app_event", value=AppEvent.class),
        @JsonSubTypes.Type(name="session_start", value=SessionStartEvent.class)})
public abstract class Event {

    @JsonProperty(value="id", required=true)
    public final UUID id;

    @JsonProperty(value="timestamp_ms", required=true)
    public final long timestamp;

    public final Type type;

    public Event(Type eventType) {
        this.type = eventType;
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
    }

    public enum Type {
        APP_EVENT,
        SESSION_START_EVENT;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}


