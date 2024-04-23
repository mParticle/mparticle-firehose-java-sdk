package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public final class ScreenViewEvent extends Event {

    @JsonProperty(value="screen_name", required=true)
    private String screenName;

    @JsonProperty("attributes")
    private Map<String, String> attributes;

    @JsonProperty("custom_flags")
    private Map<String, String> customFlags;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

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

    public ScreenViewEvent() {
        super(Type.SCREEN_VIEW);
    }
}
