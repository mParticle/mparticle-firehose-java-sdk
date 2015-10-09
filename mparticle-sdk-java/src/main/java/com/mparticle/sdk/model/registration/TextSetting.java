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

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public boolean isConfidential() {
        return isConfidential;
    }

    public void setIsConfidential(boolean isConfidential) {
        this.isConfidential = isConfidential;
    }

    public TextSetting(String id, String name) {
        super(Type.TEXT, id, name);
    }
}
