package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class CustomEvent extends Event {

    @JsonProperty(value="name", required=true)
    private String name;

    @JsonProperty("custom_event_type")
    private CustomType customType;

    @JsonProperty("attributes")
    private Map<String, String> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomType getCustomType() {
        return customType;
    }

    public void setCustomType(CustomType customType) {
        this.customType = customType;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public CustomEvent() {
        super(Type.CUSTOM_EVENT);
    }

    public enum CustomType {
        UNKNOWN,
        NAVIGATION,
        LOCATION,
        SEARCH,
        TRANSACTION,
        USER_CONTENT,
        USER_PREFERENCE,
        SOCIAL,
        OTHER,
        MEDIA;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

}
