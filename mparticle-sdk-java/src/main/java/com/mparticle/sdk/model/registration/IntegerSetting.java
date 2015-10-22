package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class IntegerSetting extends Setting {

    @JsonProperty("default_value")
    private Long defaultValue;

    @JsonProperty("min_value")
    private Long minValue;

    @JsonProperty("max_value")
    private Long maxValue;

    @JsonProperty("required")
    private boolean isRequired;

    public Long getDefaultValue() {
        return defaultValue;
    }

    public IntegerSetting setDefaultValue(Long defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public IntegerSetting setDefaultValue(Integer defaultValue) {
        this.defaultValue = Long.valueOf(defaultValue);
        return this;
    }

    public Long getMinValue() {
        return minValue;
    }

    public IntegerSetting setMinValue(Long minValue) {
        this.minValue = minValue;
        return this;
    }

    public IntegerSetting setMinValue(Integer minValue) {
        this.minValue = Long.valueOf(minValue);
        return this;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public IntegerSetting setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public IntegerSetting setMaxValue(Integer maxValue) {
        this.maxValue = Long.valueOf(maxValue);
        return this;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public IntegerSetting setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public IntegerSetting(String id, String name) {
        super(Type.INTEGER, id, name);
    }
}
