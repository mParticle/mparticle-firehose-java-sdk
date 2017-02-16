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

    /**
     *
     * @return default value
     */
    public Double getDefaultValue() {
        return defaultValue;
    }

    /**
     *
     * @param defaultValue default value
     * @return this
     */
    public FloatSetting setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     *
     * @return minimum accepted value
     */
    public Double getMinValue() {
        return minValue;
    }

    /**
     *
     * @param minValue minimum accepted value
     * @return this
     */
    public FloatSetting setMinValue(Double minValue) {
        this.minValue = minValue;
        return this;
    }

    /**
     *
     * @return maximum accepted value
     */
    public Double getMaxValue() {
        return maxValue;
    }

    /**
     *
     * @param maxValue maximum accepted value
     * @return this
     */
    public FloatSetting setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
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
    public FloatSetting setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public FloatSetting(String id, String name) {
        super(Type.FLOAT, id, name);
    }
}
