package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class UserIdentity extends Identity {

    @JsonProperty(value="type", required=true)
    private final Type type;

    /**
     *
     * @return user identity type
     */
    public Type getType() {
        return type;
    }

    @JsonCreator
    public UserIdentity(
        @JsonProperty(value="type", required=true) Type type,
        @JsonProperty(value="encoding", required=true) Identity.Encoding encoding,
        @JsonProperty(value="value", required=true) String value)
    {
        super(encoding, value);
        if (type == null) throw new IllegalArgumentException("Invalid identity type");
        this.type = type;
    }

    public enum Type {
        OTHER,
        CUSTOMER,
        FACEBOOK,
        TWITTER,
        GOOGLE,
        MICROSOFT,
        YAHOO,
        EMAIL,
        OTHER2,
        OTHER3,
        OTHER4,
        OTHER5,
        OTHER6,
        OTHER7,
        OTHER8,
        OTHER9,
        OTHER10,
        MOBILE_NUMBER,
        PHONE_NUMBER_2,
        PHONE_NUMBER_3;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
