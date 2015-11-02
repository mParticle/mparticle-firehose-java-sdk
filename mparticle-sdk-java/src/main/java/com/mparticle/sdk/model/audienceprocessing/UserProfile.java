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
     * @return custom attributes
     */
    public Map<String, String> getUserAttributes() {
        return userAttributes;
    }

    /**
     *
     * @param userAttributes custom attributes
     */
    public void setUserAttributes(Map<String, String> userAttributes) {
        this.userAttributes = userAttributes;
    }

    /**
     *
     * @return audiences user was added to
     */
    public List<Audience> getAddedAudiences() {
        return addedAudiences;
    }

    /**
     *
     * @param addedAudiences audiences user was added to
     */
    public void setAddedAudiences(List<Audience> addedAudiences) {
        this.addedAudiences = addedAudiences;
    }

    /**
     *
     * @return audiences user was removed from
     */
    public List<Audience> getRemovedAudiences() {
        return removedAudiences;
    }

    /**
     *
     * @param removedAudiences audiences user was removed from
     */
    public void setRemovedAudiences(List<Audience> removedAudiences) {
        this.removedAudiences = removedAudiences;
    }
}
