package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Setting {

    @JsonProperty(value="name", required=true)
    public String name;

    @JsonProperty(value="data_type", required=true)
    public DataType dataType;

    @JsonProperty(value="display_name", required=true)
    public String displayName;

    @JsonProperty("default_value")
    public String defaultValue;

    @JsonProperty("description")
    public String description;

    @JsonProperty("is_required")
    public boolean isRequired;

    @JsonProperty("is_confidential")
    public boolean isConfidential;

    private Setting() {
        // required by serializer
    }

    public Setting(String name, DataType dataType, String displayName) {
        this.name = name;
        this.displayName = displayName;
        this.dataType = dataType;
    }

    public enum DataType {
        STRING, INT, FLOAT, BOOL;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }


}
