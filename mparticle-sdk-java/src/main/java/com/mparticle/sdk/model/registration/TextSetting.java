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

    /**
     *
     * @return true if setting is required
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     *
     * @param isRequired  true if setting is required
     * @return this
     */
    public TextSetting setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    /**
     *
     * @return true if setting must be masked in UI
     */
    public boolean isConfidential() {
        return isConfidential;
    }

    /**
     *
     * @param isConfidential true if setting must be masked in UI
     * @return this
     */
    public TextSetting setIsConfidential(boolean isConfidential) {
        this.isConfidential = isConfidential;
        return this;
    }

    public TextSetting(String id, String name) {
        super(Type.TEXT, id, name);
    }
}
