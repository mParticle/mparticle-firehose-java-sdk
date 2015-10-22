package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TextSetting extends Setting {

    @JsonProperty("default_value")
    private String defaultValue;

    @JsonProperty("required")
    private boolean isRequired;

    @JsonProperty("confidential")
    private boolean isConfidential;

    public String getDefaultValue() {
        return defaultValue;
    }

    public TextSetting setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public TextSetting setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public boolean isConfidential() {
        return isConfidential;
    }

    public TextSetting setIsConfidential(boolean isConfidential) {
        this.isConfidential = isConfidential;
        return this;
    }

    public TextSetting(String id, String name) {
        super(Type.TEXT, id, name);
    }
}
