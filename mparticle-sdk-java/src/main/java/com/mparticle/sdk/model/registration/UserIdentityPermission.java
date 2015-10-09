package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.Identity;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;


public final class UserIdentityPermission {

    @JsonProperty(value="type", required=true)
    private final UserIdentity.Type type;

    @JsonProperty(value="encoding", required=true)
    private final Identity.Encoding encoding;

    public UserIdentity.Type getType() {
        return type;
    }

    public Identity.Encoding getEncoding() {
        return encoding;
    }

    @JsonCreator
    public UserIdentityPermission(
            @JsonProperty(value = "type", required = true) UserIdentity.Type type,
            @JsonProperty(value = "encoding", required = true) Identity.Encoding encoding) {
        this.type = type;
        this.encoding = encoding;
    }
}