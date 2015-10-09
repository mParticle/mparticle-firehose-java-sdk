package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.DeviceIdentity;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;

import java.util.List;
import java.util.Map;

public final class AudienceMembershipChange {

    @JsonProperty(value="audience_id", required=true)
    private int audienceId;
    
    @JsonProperty("audience_subscription_settings")
    private Map<String, String> audienceSubscriptionSettings;
    
    @JsonProperty("added_user_identities")
    private List<UserIdentity> addedUserIdentities;
    
    @JsonProperty("removed_user_identities")
    private List<UserIdentity> removedUserIdentities;
    
    @JsonProperty("added_device_identities")
    private List<DeviceIdentity> addedDeviceIdentities;
    
    @JsonProperty("removed_device_identities")
    private List<DeviceIdentity> removedDeviceIdentities;

    public int getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(int audienceId) {
        this.audienceId = audienceId;
    }

    public Map<String, String> getAudienceSubscriptionSettings() {
        return audienceSubscriptionSettings;
    }

    public void setAudienceSubscriptionSettings(Map<String, String> audienceSubscriptionSettings) {
        this.audienceSubscriptionSettings = audienceSubscriptionSettings;
    }

    public List<UserIdentity> getAddedUserIdentities() {
        return addedUserIdentities;
    }

    public void setAddedUserIdentities(List<UserIdentity> addedUserIdentities) {
        this.addedUserIdentities = addedUserIdentities;
    }

    public List<UserIdentity> getRemovedUserIdentities() {
        return removedUserIdentities;
    }

    public void setRemovedUserIdentities(List<UserIdentity> removedUserIdentities) {
        this.removedUserIdentities = removedUserIdentities;
    }

    public List<DeviceIdentity> getAddedDeviceIdentities() {
        return addedDeviceIdentities;
    }

    public void setAddedDeviceIdentities(List<DeviceIdentity> addedDeviceIdentities) {
        this.addedDeviceIdentities = addedDeviceIdentities;
    }

    public List<DeviceIdentity> getRemovedDeviceIdentities() {
        return removedDeviceIdentities;
    }

    public void setRemovedDeviceIdentities(List<DeviceIdentity> removedDeviceIdentities) {
        this.removedDeviceIdentities = removedDeviceIdentities;
    }
}
