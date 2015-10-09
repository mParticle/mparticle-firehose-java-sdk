package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

public final class AudienceMembershipChangeResponse extends Message {

    @JsonProperty("suspend_subscription")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean suspendSubscription;

    public AudienceMembershipChangeResponse() {
        super(Type.AUDIENCE_MEMBERSHIP_CHANGE_RESPONSE);
    }
}