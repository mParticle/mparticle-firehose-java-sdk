package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public final class AccessPermissions {

    @JsonProperty("device_identity_access_list")
    private List<DeviceIdentityAccessPermission> deviceIdentityAccessList;

    @JsonProperty("user_identity_access_list")
    private List<UserIdentityAccessPermission> userIdentityAccessList;

    @JsonProperty("allow_access_location")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAccessLocation;

    public List<DeviceIdentityAccessPermission> getDeviceIdentityAccessList() {
        return deviceIdentityAccessList;
    }

    public void setDeviceIdentityAccessList(List<DeviceIdentityAccessPermission> deviceIdentityAccessList) {
        this.deviceIdentityAccessList = deviceIdentityAccessList;
    }

    public List<UserIdentityAccessPermission> getUserIdentityAccessList() {
        return userIdentityAccessList;
    }

    public void setUserIdentityAccessList(List<UserIdentityAccessPermission> userIdentityAccessList) {
        this.userIdentityAccessList = userIdentityAccessList;
    }

    public boolean isAllowAccessLocation() {
        return allowAccessLocation;
    }

    public void setAllowAccessLocation(boolean allowAccessLocation) {
        this.allowAccessLocation = allowAccessLocation;
    }
}
