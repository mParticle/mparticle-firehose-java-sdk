package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class UserIdentityChangeEvent extends Event {

    @JsonProperty("added")
    private List<UserIdentity> added;

    @JsonProperty("removed")
    private List<UserIdentity> removed;

    public List<UserIdentity> getAdded() {
        return added;
    }

    public void setAdded(List<UserIdentity> added) {
        this.added = added;
    }

    public List<UserIdentity> getRemoved() {
        return removed;
    }

    public void setRemoved(List<UserIdentity> removed) {
        this.removed = removed;
    }

    public UserIdentityChangeEvent() {
        super(Type.USER_IDENTITY_CHANGE);
    }
}