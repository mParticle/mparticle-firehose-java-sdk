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

    /**
     *
     * @return requested device identities
     */
    public List<DeviceIdentityPermission> getDeviceIdentities() {
        return deviceIdentities;
    }

    /**
     *
     * @param deviceIdentities requested device identities
     * @return this
     */
    public Permissions setDeviceIdentities(List<DeviceIdentityPermission> deviceIdentities) {
        this.deviceIdentities = deviceIdentities;
        return this;
    }

    /**
     *
     * @return requested user identities
     */
    public List<UserIdentityPermission> getUserIdentities() {
        return userIdentities;
    }

    /**
     *
     * @param userIdentities requested user identities
     * @return
     */
    public Permissions setUserIdentities(List<UserIdentityPermission> userIdentities) {
        this.userIdentities = userIdentities;
        return this;
    }

    /**
     *
     * @return true if requesting access to GEO location
     */
    public boolean isAllowAccessLocation() {
        return allowAccessLocation;
    }

    /**
     *
     * @param allowAccessLocation
     * @return true if requesting access to GEO location
     */
    public Permissions setAllowAccessLocation(boolean allowAccessLocation) {
        this.allowAccessLocation = allowAccessLocation;
        return this;
    }
}
