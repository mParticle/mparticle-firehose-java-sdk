package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class UserAttributeAudienceEvent {

    @JsonProperty(value="key", required=true)
    private String key;

    @JsonProperty(value="value")
    private String value;

    @JsonProperty(value="action", required=true)
    private AttributeAction action;

    @JsonProperty(value="list_value")
    private List<String> listValue;

    /**
     *
     * @return the key name of this attribute
     */
    public String getKey() {
        return this.key;
    }

    /**
     *
     * @param key the key name of this attribute
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *
     * @return the value of this attribute, can be null if a tag or if using list_value
     */
    public String getValue() {
        return this.value;
    }

    /**
     *
     * @param value the value of this attribute, can be null if a tag or if using list_value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return the value of this attribute if it is a list
     */
    public List<String> getListValue() {
        return this.listValue;
    }

    /**
     *
     * @param listValue the value of this attribute if it is a list
     */
    public void setListValue(List<String> listValue) {
        this.listValue = listValue;
    }

    /**
     *
     * @return action associated with this attribute
     */
    public AttributeAction getAction() {
        return this.action;
    }

    /**
     *
     * @param action action associated with this attribute
     */
    public void setAction(AttributeAction action) {
        this.action = action;
    }

    public enum AttributeAction {
        UPSERT,
        DELETE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
