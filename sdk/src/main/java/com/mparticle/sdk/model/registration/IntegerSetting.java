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

    /**
     *
     * @return default value
     */
    public Long getDefaultValue() {
        return defaultValue;
    }

    /**
     *
     * @param defaultValue default value
     * @return this
     */
    public IntegerSetting setDefaultValue(Long defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     *
     * @param defaultValue default value
     * @return this
     */
    public IntegerSetting setDefaultValue(Integer defaultValue) {
        this.defaultValue = Long.valueOf(defaultValue);
        return this;
    }

    /**
     *
     * @return minimum accepted value
     */
    public Long getMinValue() {
        return minValue;
    }

    /**
     *
     * @param minValue minimum accepted value
     * @return this
     */
    public IntegerSetting setMinValue(Long minValue) {
        this.minValue = minValue;
        return this;
    }

    /**
     *
     * @param minValue minimum accepted value
     * @return this
     */
    public IntegerSetting setMinValue(Integer minValue) {
        this.minValue = Long.valueOf(minValue);
        return this;
    }

    /**
     *
     * @return maximum accepted value
     */
    public Long getMaxValue() {
        return maxValue;
    }

    /**
     *
     * @param maxValue maximum accepted value
     * @return this
     */
    public IntegerSetting setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    /**
     *
     * @param maxValue maximum accepted value
     * @return this
     */
    public IntegerSetting setMaxValue(Integer maxValue) {
        this.maxValue = Long.valueOf(maxValue);
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
    public IntegerSetting setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public IntegerSetting(String id, String name) {
        super(Type.INTEGER, id, name);
    }
}
