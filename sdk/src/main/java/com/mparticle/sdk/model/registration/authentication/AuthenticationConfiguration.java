package com.mparticle.sdk.model.registration.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "oauth2", value = OAuth2Authentication.class),
})
public abstract class AuthenticationConfiguration {

    public AuthenticationConfiguration(Type type){
        this.type = type;
    }

    private final Type type;

    /**
     *
     * @return authentication type
     */
    public Type getType() { return type; }

    public enum Type {
        OAUTH2;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}

