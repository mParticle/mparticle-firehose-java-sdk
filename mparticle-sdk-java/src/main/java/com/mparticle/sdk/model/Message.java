package com.mparticle.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mparticle.sdk.model.eventprocessing.EventProcessingRequest;
import com.mparticle.sdk.model.eventprocessing.EventProcessingResponse;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;

import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="module_registration_request", value=ModuleRegistrationRequest.class),
        @JsonSubTypes.Type(name="module_registration_response", value=ModuleRegistrationResponse.class),
        @JsonSubTypes.Type(name="event_processing_request", value=EventProcessingRequest.class),
        @JsonSubTypes.Type(name="event_processing_response", value=EventProcessingResponse.class)
})
public abstract class Message {

    private final Type type;

    @JsonProperty(value="id", required=true)
    private final UUID id;

    @JsonProperty(value="timestamp_ms", required=true)
    private long timestamp;

    public Type getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Message(Type type) {
        this.type = type;
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
    }

    public enum Type {
        MODULE_REGISTRATION_REQUEST,
        MODULE_REGISTRATION_RESPONSE,
        EVENT_PROCESSING_REQUEST,
        EVENT_PROCESSING_RESPONSE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
