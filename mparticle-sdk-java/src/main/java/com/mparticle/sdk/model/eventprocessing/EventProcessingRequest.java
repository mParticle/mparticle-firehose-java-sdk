package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

import java.util.List;


public class EventProcessingRequest extends Message {

    //@JsonCreator
    //public EventStreamRequestMessage(@JsonProperty("id") String id) {
    //    this.id = id;
    //}

    public EventProcessingRequest() {
        super(Type.EVENT_PROCESSING_REQUEST);
    }

    public Application app;
    public Device device;
    public User user;
    public Subscription subscription;
    public Replay replay;

    @JsonProperty("events")
    public List<Event> events;
}


