package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="unknown", value=UnknownRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="android", value=AndroidRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="ios", value=IosRuntimeEnvironment.class)
})
public abstract class RuntimeEnvironment {

    private final Type type;

    @JsonProperty("is_debug")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isDebug;

    public RuntimeEnvironment(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public enum Type {
        UNKNOWN,
        ANDROID,
        IOS;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
