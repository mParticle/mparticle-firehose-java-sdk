package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.Identity;

public class PartnerIdentityPermission {

    @JsonProperty(value="type", required=true)
    private final String type;

    @JsonProperty(value="encoding", required=true)
    private final Identity.Encoding encoding;

    @JsonProperty("required")
    private final boolean isRequired;
    /**
     *
     * @return partner identity type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return the encoding in which this id will be sent to the Partner
     */
    public Identity.Encoding getEncoding() {
        return encoding;
    }

    /**
     *
     * @return true if identity is required
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     *
     * @param type identity type
     * @param encoding identity encoding
     * @param isRequired if set to true, devices missing this identity will be ignored
     */
    @JsonCreator
    public PartnerIdentityPermission(
            @JsonProperty(value = "type", required = true) String type,
            @JsonProperty(value = "encoding", required = true) Identity.Encoding encoding,
            @JsonProperty("required") boolean isRequired) {
        this.type = type;
        this.encoding = encoding;
        this.isRequired = isRequired;
    }

    /**
     *
     * @param type identity type
     * @param encoding identity encoding
     */
    public PartnerIdentityPermission(String type, Identity.Encoding encoding) {
        this(type, encoding, false);
    }
}