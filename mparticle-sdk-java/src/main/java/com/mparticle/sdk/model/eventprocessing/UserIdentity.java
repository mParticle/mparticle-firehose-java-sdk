package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.Utils;

public class UserIdentity {

    @JsonProperty(value="identity_type", required=true)
    private final UserIdentity.Type identityType;

    @JsonProperty(value="identity_format", required=true)
    private final UserIdentity.Format identityFormat;

    @JsonProperty(value="value", required=true)
    private final String value;

    public Type getIdentityType() {
        return identityType;
    }

    public Format getIdentityFormat() {
        return identityFormat;
    }

    public String getValue() {
        return value;
    }

    public UserIdentity(Type identityType, Format identityFormat, String value) {
        if (identityType == null) throw new IllegalArgumentException("Invalid type");
        if (identityFormat == null) throw new IllegalArgumentException("Invalid format");
        if (Utils.isNullOrEmpty(value)) throw new IllegalArgumentException("Invalid value");

        this.identityType = identityType;
        this.identityFormat = identityFormat;
        this.value = value;
    }

    public enum Format {
        RAW,
        MD5,
        SHA1,
        SHA256;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public enum Type {
        ANDROID_DEVICE_ID,
        GOOGLE_AD_ID,
        IOS_IDFA,
        IOS_IDFV,
        MP_ID,
        CUSTOMER_ID,
        FACEBOOK_ID,
        TWITTER_ID,
        GOOGLE_ID,
        MICROSOFT_ID,
        YAHOO_ID,
        EMAIL_ID;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
