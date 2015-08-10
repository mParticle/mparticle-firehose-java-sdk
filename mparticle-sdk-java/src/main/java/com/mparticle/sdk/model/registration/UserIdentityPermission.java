package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;

public class UserIdentityPermission extends Permission {

    @JsonProperty(value="identity_type", required=true)
    private final UserIdentity.Type identityType;

    @JsonProperty(value="identity_format", required=true)
    private final UserIdentity.Format identityFormat;

    public UserIdentity.Type getIdentityType() {
        return identityType;
    }

    public UserIdentity.Format getIdentityFormat() {
        return identityFormat;
    }

    public UserIdentityPermission(UserIdentity.Type identityType, UserIdentity.Format identityFormat) {
        super(Type.USER_IDENTITY);

        if (identityType == null) throw new IllegalArgumentException("Invalid identityType");
        if (identityFormat == null) throw new IllegalArgumentException("Invalid identityFormat");

        this.identityType = identityType;
        this.identityFormat = identityFormat;
    }
}
