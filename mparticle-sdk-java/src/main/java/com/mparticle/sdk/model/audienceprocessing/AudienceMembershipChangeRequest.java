package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.registration.Account;

import java.util.List;

public final class AudienceMembershipChangeRequest extends Message {

    @JsonProperty(value="account", required=true)
    private Account account;

    @JsonProperty("changes")
    private List<AudienceMembershipChange> membershipChanges;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<AudienceMembershipChange> getMembershipChanges() {
        return membershipChanges;
    }

    public void setMembershipChanges(List<AudienceMembershipChange> membershipChanges) {
        this.membershipChanges = membershipChanges;
    }

    public AudienceMembershipChangeRequest() {
        super(Message.Type.AUDIENCE_MEMBERSHIP_CHANGE_REQUEST);
    }
}
