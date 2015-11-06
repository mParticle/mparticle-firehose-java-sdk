package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.DeviceIdentity;
import com.mparticle.sdk.model.eventprocessing.Identity;

public final class DeviceIdentityPermission {

    @JsonProperty(value="type", required=true)
    private final DeviceIdentity.Type type;

    @JsonProperty(value="encoding", required=true)
    private final Identity.Encoding encoding;

    @JsonProperty("required")
    private final boolean isRequired;

    /**
     *
     * @return device identity type
     */
    public DeviceIdentity.Type getType() {
        return type;
    }

    /**
     *
     * @return device identity encoding
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
    public DeviceIdentityPermission(
            @JsonProperty(value = "type", required = true) DeviceIdentity.Type type,
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
    public DeviceIdentityPermission(DeviceIdentity.Type type, Identity.Encoding encoding) {
        this(type, encoding, false);
    }
}
