package com.mparticle.sdk.model.audienceprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.DeviceIdentity;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;

import java.util.List;

public final class UserProfile {

    @JsonProperty("user_identities")
    private List<UserIdentity> userIdentities;

    @JsonProperty("device_identities")
    private List<DeviceIdentity> deviceIdentities;

    @JsonProperty("audiences")
    private List<Audience> audiences;

    /**
     *
     * @return user identities
     */
    public List<UserIdentity> getUserIdentities() {
        return userIdentities;
    }

    /**
     *
     * @param userIdentities user identities
     */
    public void setUserIdentities(List<UserIdentity> userIdentities) {
        this.userIdentities = userIdentities;
    }

    /**
     *
     * @return device identities
     */
    public List<DeviceIdentity> getDeviceIdentities() {
        return deviceIdentities;
    }

    /**
     *
     * @param deviceIdentities device identities
     */
    public void setDeviceIdentities(List<DeviceIdentity> deviceIdentities) {
        this.deviceIdentities = deviceIdentities;
    }

    /**
     *
     * @return audiences user was either added to, removed from, or contained updates within
     */
    public List<Audience> getAudiences() {
        return audiences;
    }

    /**
     *
     * @param audiences audiences that contained some change for this user
     */
    public void setAudiences(List<Audience> audiences) {
        this.audiences = audiences;
    }

}
