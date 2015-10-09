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

    public void setDefaultValue(Long defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setDefaultValue(Integer defaultValue) {
        this.defaultValue = Long.valueOf(defaultValue);
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = Long.valueOf(minValue);
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = Long.valueOf(maxValue);
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public IntegerSetting(String id, String name) {
        super(Type.INTEGER, id, name);
    }
}
