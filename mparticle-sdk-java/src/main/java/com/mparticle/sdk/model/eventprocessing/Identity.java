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

    /**
     *
     * @return identity encoding
     */
    public Encoding getEncoding() {
        return encoding;
    }

    /**
     *
     * @return identity value
     */
    public String getValue() {
        return value;
    }

    /**
     * Identity value encoding formats.
     */
    public enum Encoding {
        /**
         * No encoding applied
         */
        RAW,
        /**
         * Trimmed, converted to lower case, and hashed by MD5
         */
        MD5,
        /**
         * Trimmed, converted to lower case, and hashed by SHA-1
         */
        SHA1,
        /**
         * Trimmed, converted to lower case, and hashed by SHA-256
         */
        SHA256;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

}
