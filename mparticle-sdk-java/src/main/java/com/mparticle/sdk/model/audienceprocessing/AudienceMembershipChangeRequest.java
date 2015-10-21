package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.registration.Account;

import java.util.List;

public final class AudienceMembershipChangeRequest extends Message {

    @JsonProperty(value="account", required=true)
    private Account account;

    @JsonProperty("user_profiles")
    private List<UserProfile> userProfiles;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public AudienceMembershipChangeRequest() {
        super(Message.Type.AUDIENCE_MEMBERSHIP_CHANGE_REQUEST);
    }
}
