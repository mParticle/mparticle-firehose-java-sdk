package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.DeviceIdentity;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;

import java.util.List;
import java.util.Map;

public final class UserProfile {

    @JsonProperty("user_identities")
    private List<UserIdentity> userIdentities;

    @JsonProperty("device_identities")
    private List<DeviceIdentity> deviceIdentities;

    @JsonProperty("user_attributes")
    private Map<String, String> userAttributes;

    @JsonProperty("added_audiences")
    private List<Audience> addedAudiences;

    @JsonProperty("removed_audiences")
    private List<Audience> removedAudiences;

    public List<UserIdentity> getUserIdentities() {
        return userIdentities;
    }

    public void setUserIdentities(List<UserIdentity> userIdentities) {
        this.userIdentities = userIdentities;
    }

    public List<DeviceIdentity> getDeviceIdentities() {
        return deviceIdentities;
    }

    public void setDeviceIdentities(List<DeviceIdentity> deviceIdentities) {
        this.deviceIdentities = deviceIdentities;
    }

    public Map<String, String> getUserAttributes() {
        return userAttributes;
    }

    public void setUserAttributes(Map<String, String> userAttributes) {
        this.userAttributes = userAttributes;
    }

    public List<Audience> getAddedAudiences() {
        return addedAudiences;
    }

    public void setAddedAudiences(List<Audience> addedAudiences) {
        this.addedAudiences = addedAudiences;
    }

    public List<Audience> getRemovedAudiences() {
        return removedAudiences;
    }

    public void setRemovedAudiences(List<Audience> removedAudiences) {
        this.removedAudiences = removedAudiences;
    }
}
