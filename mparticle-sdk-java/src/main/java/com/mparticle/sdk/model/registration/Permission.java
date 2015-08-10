package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="user_identity", value=UserIdentityPermission.class),
        @JsonSubTypes.Type(name="location", value=LocationPermission.class)
})
public abstract class Permission {
    private final Type type;

    public Type getType() {
        return type;
    }

    public Permission(Type type) {
        this.type = type;
    }

    public enum Type {
        USER_IDENTITY, LOCATION;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

}
