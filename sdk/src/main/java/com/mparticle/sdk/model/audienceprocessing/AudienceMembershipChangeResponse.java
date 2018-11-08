package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;

public final class AudienceMembershipChangeResponse extends Message {

    @JsonProperty("suspend_subscription")
    private boolean suspendSubscription;

    /**
     *
     * @return true if this audience subscription should be suspended and the endpoint faulted
     */
    public boolean isSuspendSubscription() { return suspendSubscription; }

    /**
     * True if this audience subscription should be suspended and the endpoint faulted
     * @param suspendSubscription new value
     */
    public void setSuspendSubscription(boolean suspendSubscription) { this.suspendSubscription = suspendSubscription; }

    public AudienceMembershipChangeResponse() {
        super(Type.AUDIENCE_MEMBERSHIP_CHANGE_RESPONSE);
    }
}