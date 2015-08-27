package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class Permissions {

    @JsonProperty("device_identities")
    private List<DeviceIdentityPermission> deviceIdentities;

    @JsonProperty("user_identities")
    private List<UserIdentityPermission> userIdentities;

    @JsonProperty("allow_access_location")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAccessLocation;

    public List<DeviceIdentityPermission> getDeviceIdentities() {
        return deviceIdentities;
    }

    public void setDeviceIdentities(List<DeviceIdentityPermission> deviceIdentities) {
        this.deviceIdentities = deviceIdentities;
    }

    public List<UserIdentityPermission> getUserIdentities() {
        return userIdentities;
    }

    public void setUserIdentities(List<UserIdentityPermission> userIdentities) {
        this.userIdentities = userIdentities;
    }

    public boolean isAllowAccessLocation() {
        return allowAccessLocation;
    }

    public void setAllowAccessLocation(boolean allowAccessLocation) {
        this.allowAccessLocation = allowAccessLocation;
    }
}
