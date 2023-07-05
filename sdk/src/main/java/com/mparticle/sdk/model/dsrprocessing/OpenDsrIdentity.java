package com.mparticle.sdk.model.dsrprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class OpenDsrIdentity {

    @JsonProperty(value="encoding", required=true)
    private Encoding encoding;

    @JsonProperty(value="value", required=true)
    private String value;

    /**
     *
     * @return the identity encoding
     */
    public Encoding getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the identity encoding
     */
    public void setEncoding(Encoding encoding) {
        this.encoding = encoding;
    }

    /**
     *
     * @return the identity value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the identity value
     */
    public void setValue(String value) {
        this.value = value;
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

    public enum Type {
    CONTROLLER_CUSTOMER_ID,
    ANDROID_ADVERTISING_ID,
    ANDROID_ID,
    EMAIL,
    FIRE_ADVERTISING_ID,
    IOS_ADVERTISING_ID,
    IOS_VENDOR_ID,
    MICROSOFT_ADVERTISING_ID,
    MICROSOFT_PUBLISHER_ID,
    ROKU_PUBLISHER_ID,
    ROKU_ADVERTISING_ID;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

}
