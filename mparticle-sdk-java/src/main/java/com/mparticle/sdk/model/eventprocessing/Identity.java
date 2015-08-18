package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.Utils;

public abstract class Identity {

    @JsonProperty(value="encoding", required=true)
    private final Encoding encoding;

    @JsonProperty(value="value", required=true)
    private final String value;

    protected Identity(Encoding encoding, String value)
    {
        if (encoding == null) throw new IllegalArgumentException("Invalid identity encoding");
        if (Utils.isNullOrEmpty(value)) throw new IllegalArgumentException("Invalid identity value");

        this.encoding = encoding;
        this.value = value;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public String getValue() {
        return value;
    }

    public enum Encoding {
        RAW,
        MD5,
        SHA1,
        SHA256;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

}
