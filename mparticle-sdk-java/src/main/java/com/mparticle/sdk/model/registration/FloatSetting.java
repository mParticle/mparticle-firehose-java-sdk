package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class FloatSetting extends Setting {

    @JsonProperty("default_value")
    private Double defaultValue;

    @JsonProperty("min_value")
    private Double minValue;

    @JsonProperty("max_value")
    private Double maxValue;

    @JsonProperty("required")
    private boolean isRequired;

    public Double getDefaultValue() {
        return defaultValue;
    }

    public FloatSetting setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public Double getMinValue() {
        return minValue;
    }

    public FloatSetting setMinValue(Double minValue) {
        this.minValue = minValue;
        return this;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public FloatSetting setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public FloatSetting setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public FloatSetting(String id, String name) {
        super(Type.FLOAT, id, name);
    }
}
