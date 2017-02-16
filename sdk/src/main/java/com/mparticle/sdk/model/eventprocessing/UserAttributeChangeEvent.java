package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class UserAttributeChangeEvent extends Event {

    @JsonProperty("added")
    private Map<String, String> added;

    @JsonProperty("removed")
    private Map<String, String> removed;

    public Map<String, String> getAdded() {
        return added;
    }

    public void setAdded(Map<String, String> added) {
        this.added = added;
    }

    public Map<String, String> getRemoved() {
        return removed;
    }

    public void setRemoved(Map<String, String> removed) {
        this.removed = removed;
    }

    public UserAttributeChangeEvent() {
        super(Type.USER_ATTRIBUTE_CHANGE);
    }
}