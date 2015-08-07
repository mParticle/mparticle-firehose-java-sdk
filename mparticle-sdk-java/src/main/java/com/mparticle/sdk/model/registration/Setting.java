package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

    public void setId(String id) {
        if (id.isEmpty()) throw new IllegalArgumentException();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (id.isEmpty()) throw new IllegalArgumentException();
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Setting(Type type, String id, String name) {
        this.type = type;
        setId(id);
        setName(name);
    }

    public enum Type {
        TEXT, INTEGER, FLOAT, BOOLEAN;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
