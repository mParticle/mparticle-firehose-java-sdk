package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public final class ImpressionEvent extends Event {

    @JsonProperty("attributes")
    private Map<String, String> attributes;

    @JsonProperty("impressions")
    private List<Impression> impressions;

    @JsonProperty("custom_flags")
    private Map<String, String> customFlags;

    public ImpressionEvent() {
        super(Type.IMPRESSION);
    }

    public List<Impression> getImpressions() {
        return impressions;
    }

    public void setImpressions(List<Impression> impressions) {
        this.impressions = impressions;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public ImpressionEvent setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
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
}
