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

    @JsonProperty("partner_identities")
    private List<PartnerIdentityPermission> partnerIdentities;

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

    @JsonProperty("allow_audience_user_attributes")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAudienceUserAttributeSharing;

    @JsonProperty("allow_access_http_user_agent")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAccessHttpUserAgent;

    @JsonProperty("allow_access_mpid")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowAccessMpid;

    @JsonProperty("allow_platform_fields")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowPlatformFields;

    @JsonProperty("allow_eventless_batches")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean allowEventlessBatches;

    /**
     *
     * @return requested partner identities
     */
    public List<PartnerIdentityPermission> getPartnerIdentities() {
        return partnerIdentities;
    }

    /**
     *
     * @param partnerIdentities requested partner identities
     * @return this
     */
    public Permissions setPartnerIdentities(List<PartnerIdentityPermission> partnerIdentities) {
        this.partnerIdentities = partnerIdentities;
        return this;
    }

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

    public boolean isAllowAudienceUserAttributeSharing() {
        return allowAudienceUserAttributeSharing;
    }

    /**
     * Setting this will allow a partner to receive audience user attribute updates.
     *
     * @param allowAudienceUserAttributeSharing
     * @return
     */
    public Permissions setAllowAudienceUserAttributeSharing(boolean allowAudienceUserAttributeSharing) {
        this.allowAudienceUserAttributeSharing = allowAudienceUserAttributeSharing;
        return this;
    }

    /**
     * Setting for allowing for an MPID to be forwarded as a part of the user profile.
     *
     * @return
     */
    public boolean isAllowAccessMpid() {
        return allowAccessMpid;
    }

    /**
     * Setting this will allow a partner to get an MPID as a part of the user profile.
     *
     * @param allowAccessMpid
     * @return
     */
    public Permissions setAllowAccessMpid(boolean allowAccessMpid) {
        this.allowAccessMpid = allowAccessMpid;
        return this;
    }

    /**
     * Setting this will allow a partner to receive platform fields like account id and workspace name.
     *
     * @return allowPlatformFields
     */
    public boolean isAllowPlatformFields() { return allowPlatformFields; }

    /**
     * Setting this will allow a partner to receive platform fields like account id and workspace name.
     *
     * @param allowPlatformFields Allow platform fields boolean
     * @return Permissions
     */
    public Permissions setAllowPlatformFields(boolean allowPlatformFields) {
        this.allowPlatformFields = allowPlatformFields;
        return this;
    }

    /**
     * Setting this will allow a partner to receive batches that don't contain any events.
     *
     * @return allowEventlessBatches
     */
    public boolean isAllowEventlessBatches() { return allowEventlessBatches; }

    /**
     * Setting this will allow a partner to receive batches that don't contain any events.
     *
     * @param allowEventlessBatches Allow eventless batches boolean
     * @return Permissions
     */
    public Permissions setAllowEventlessBatches(boolean allowEventlessBatches) {
        this.allowEventlessBatches = allowEventlessBatches;
        return this;
    }

    /**
     * Setting this will allow a partner to receive the HTTP user agent as a part of a runtime environment.
     *
     * @return
     */
    public boolean isAllowAccessHttpUserAgent() {
        return allowAccessHttpUserAgent;
    }

    /**
     * Setting this will allow a partner to receive the HTTP user agent as a part of a runtime environment.
     *
     * @param allowAccessHttpUserAgent
     * @return
     */
    public Permissions setAllowAccessHttpUserAgent(boolean allowAccessHttpUserAgent) {
        this.allowAccessHttpUserAgent = allowAccessHttpUserAgent;
        return this;
    }
}
