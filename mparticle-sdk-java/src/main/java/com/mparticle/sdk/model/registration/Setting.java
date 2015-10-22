package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mparticle.sdk.Utils;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="text", value=TextSetting.class),
        @JsonSubTypes.Type(name="integer", value=IntegerSetting.class),
        @JsonSubTypes.Type(name="float", value=FloatSetting.class),
        @JsonSubTypes.Type(name="boolean", value=BooleanSetting.class)
})
public abstract class Setting {

    private final Type type;

    @JsonProperty(value="id", required=true)
    private String id;

    @JsonProperty(value="name", required=true)
    private String name;

    @JsonProperty("description")
    private String description;

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Setting setId(String id) {
        if (Utils.isNullOrEmpty(id)) throw new IllegalArgumentException();
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Setting setName(String name) {
        if (Utils.isNullOrEmpty(name)) throw new IllegalArgumentException();
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Setting setDescription(String description) {
        this.description = description;
        return this;
    }

    public Setting(Type type, String id, String name) {
        this.type = type;
        setId(id);
        setName(name);
    }

    public enum Type {
        TEXT,
        INTEGER,
        FLOAT,
        BOOLEAN;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
