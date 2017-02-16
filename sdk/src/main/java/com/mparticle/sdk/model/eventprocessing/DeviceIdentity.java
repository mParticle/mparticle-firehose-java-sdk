package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class DeviceIdentity extends Identity {

    @JsonProperty(value="type", required=true)
    private final Type type;

    /**
     *
     * @return device identity type
     */
    public Type getType() {
        return type;
    }

    @JsonCreator
    public DeviceIdentity(

            @JsonProperty(value="type", required=true) Type type,
            @JsonProperty(value="encoding", required=true) Identity.Encoding encoding,
            @JsonProperty(value="value", required=true) String value)
    {
        super(encoding, value);
        if (type == null) throw new IllegalArgumentException("Invalid identity type");
        this.type = type;
    }


    public enum Type {
        ANDROID_ID,
        GOOGLE_ADVERTISING_ID,
        IOS_ADVERTISING_ID,
        IOS_VENDOR_ID,
        GOOGLE_CLOUD_MESSAGING_TOKEN,
        APPLE_PUSH_NOTIFICATION_TOKEN;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
