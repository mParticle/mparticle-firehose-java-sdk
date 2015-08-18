package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.DeviceIdentity;
import com.mparticle.sdk.model.eventprocessing.Identity;

public class DeviceIdentityAccessPermission {

    @JsonProperty(value="type", required=true)
    private final DeviceIdentity.Type type;

    @JsonProperty(value="encoding", required=true)
    private final Identity.Encoding encoding;

    public DeviceIdentity.Type getType() {
        return type;
    }

    public Identity.Encoding getEncoding() {
        return encoding;
    }

    @JsonCreator
    public DeviceIdentityAccessPermission(
            @JsonProperty(value="type", required=true) DeviceIdentity.Type type,
            @JsonProperty(value="encoding", required=true) Identity.Encoding encoding) {
        this.type = type;
        this.encoding = encoding;
    }
}
