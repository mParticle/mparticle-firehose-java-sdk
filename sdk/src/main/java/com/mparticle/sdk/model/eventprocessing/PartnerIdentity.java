package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.Utils;

import java.util.UUID;

/**
 * Class representing a Partner Identity to send to a given Partner
 */
public class PartnerIdentity extends Identity {

    @JsonProperty(value="type", required=true)
    private final String type;

    @JsonCreator
    public PartnerIdentity(
        @JsonProperty(value="type", required=true) String type,
        @JsonProperty(value="encoding", required=true) Identity.Encoding encoding,
        @JsonProperty(value="value", required=true) String value)
    {
        super(encoding, value);
        if (Utils.isNullOrEmpty(type)) throw new IllegalArgumentException("Invalid identity type");
        this.type = type;
    }

    /**
     *
     * @return the partner identity type
     */
    public String getType() {
        return type;
    }
}
