package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.eventprocessing.EventProcessingRequest;
import com.mparticle.sdk.model.eventprocessing.RuntimeEnvironment;

import java.util.List;

public final class Permissions {

    @JsonProperty("device_identities")
    private List<DeviceIdentityPermission> deviceIdentities;

    @JsonProperty("user_identities")
    private List<UserIdentityPermission> userIdentities;

    @JsonProperty("allow_access_location")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAccessLocation;

    @JsonProperty("allow_access_ip_address")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAccessIpAddress;

    @JsonProperty("allow_access_device_application_stamp")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAccessDeviceApplicationStamp;

    @JsonProperty("allow_device_info")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowDeviceInformation;

    @JsonProperty("allow_user_attributes")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowUserAttributes;

    @JsonProperty("allow_consent_state")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowConsentState;

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

    /**
     *
     * @return true if requesting access to the client IP address
     */
    public boolean isAllowAccessIpAddress() {
        return allowAccessIpAddress;
    }

    /**
     * Request access to the IP address that sent this event data.
     *
     * Requests to the mParticle API may come from
     * a browser, and iOS/tvOS/Android device, or a server when data originates from
     * a server-to-server integration.
     *
     * @param allowAccessIpAddress
     *
     * @see RuntimeEnvironment#getClientIpAddress()
     *
     */
    public Permissions setAllowAccessIpAddress(boolean allowAccessIpAddress) {
        this.allowAccessIpAddress = allowAccessIpAddress;
        return this;
    }

    public boolean isAllowAccessDeviceApplicationStamp() {
        return allowAccessDeviceApplicationStamp;
    }

    /**
     * Request access to the "device application stamp" for this event data.
     *
     * The "device application stamp" is a unique identifier for this particular app and device instance.
     * It will stay persistent for a given device and application pair, as long as the app is installed and/or
     * cookies have not been cleared.
     *
     * @see EventProcessingRequest#getDeviceApplicationStamp()
     *
     * @param allowAccessDeviceApplicationStamp
     */
    public Permissions setAllowAccessDeviceApplicationStamp(boolean allowAccessDeviceApplicationStamp) {
        this.allowAccessDeviceApplicationStamp = allowAccessDeviceApplicationStamp;
        return this;
    }

    public boolean isAllowDeviceInformation() {
        return allowDeviceInformation;
    }

    /**
     * Request access to device information
     *
     *
     * @param allowDeviceInformation
     *
     */
    public Permissions setAllowDeviceInformation(boolean allowDeviceInformation) {
        this.allowDeviceInformation = allowDeviceInformation;
        return this;
    }

    public boolean isAllowUserAttributes() {
        return allowUserAttributes;
    }

    /**
     * Request access to user attributes
     *
     * Disabling this will also disable UserAttributeChange events
     *
     * @param allowUserAttributes
     *
     */
    public Permissions setAllowUserAttributes (boolean allowUserAttributes) {
        this.allowUserAttributes = allowUserAttributes;
        return this;
    }

    public boolean isAllowConsentState() {
        return allowConsentState;
    }

    /**
     * Setting this will allow a partner to receive the consent state of the mParticle user profile
     * to which this request pertains.
     *
     * @param allowConsentState
     * @return
     */
    public Permissions setAllowConsentState(boolean allowConsentState) {
        this.allowConsentState = allowConsentState;
        return this;
    }
}
