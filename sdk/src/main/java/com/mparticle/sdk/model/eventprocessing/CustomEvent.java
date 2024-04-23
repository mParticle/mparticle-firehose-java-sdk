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

    @JsonProperty("custom_flags")
    private Map<String, String> customFlags;

    /**
     *
     * @return event name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name event name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return custom event type
     */
    public CustomType getCustomType() {
        return customType;
    }

    /**
     *
     * @param customType  custom event type
     */
    public void setCustomType(CustomType customType) {
        this.customType = customType;
    }

    /**
     *
     * @return event attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes  event attributes
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * @return custom flags map
     */
    public Map<String, String> getCustomFlags() { return customFlags; }

    /**
     *
     * @param customFlags custom flags map
     */
    public void setCustomFlags(Map<String, String> customFlags) { this.customFlags = customFlags; }

    public CustomEvent() {
        super(Type.CUSTOM_EVENT);
    }

    /**
     * Custom event types.
     */
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
