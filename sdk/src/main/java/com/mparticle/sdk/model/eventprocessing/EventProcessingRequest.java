package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.eventprocessing.consent.ConsentState;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;
import com.mparticle.sdk.model.registration.Account;

import java.util.List;
import java.util.Map;

/**
 * This message contains application events logged by the mobile SDKs.
 */
public final class EventProcessingRequest extends Message {

    public EventProcessingRequest() {
        super(Type.EVENT_PROCESSING_REQUEST);
    }

    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty(value="account", required=true)
    private Account account;

    @JsonProperty("partner_identities")
    private List<PartnerIdentity> partnerIdentities;

    @JsonProperty("user_identities")
    private List<UserIdentity> userIdentities;

    @JsonProperty("user_attributes")
    private Map<String, String> userAttributes;

    @JsonProperty("user_attribute_lists")
    private Map<String, List<String>> userAttributeLists;

    @JsonProperty("runtime_environment")
    private RuntimeEnvironment runtimeEnvironment;

    @JsonProperty("integration_attributes")
    private Map<String, String> integrationAttributes;

    @JsonProperty("events")
    private List<Event> events;

    @JsonProperty("source_channel")
    private String sourceChannel;

    @JsonProperty("device_application_stamp")
    private String deviceApplicationStamp;

    @JsonProperty("consent_state")
    private ConsentState consentState;

    @JsonProperty("system_notifications")
    private List<SystemNotification> systemNotifications;

    @JsonProperty("mpid")
    private String mpId;

    @JsonProperty("platform_fields")
    private PlatformFields platformFields;

    /**
     *
     * @return source id
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     *
     * @param sourceId source id
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     *
     * @return module subscription account
     */
    public Account getAccount() {
        return account;
    }

    /**
     *
     * @param account module subscription account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

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
     * @return user attributes
     */
    public Map<String, String> getUserAttributes() {
        return userAttributes;
    }

    /**
     *
     * @param userAttributes user attributes
     */
    public void setUserAttributes(Map<String, String> userAttributes) {
        this.userAttributes = userAttributes;
    }

    /**
     *
     * @return application execution environment
     */
    public RuntimeEnvironment getRuntimeEnvironment() {
        return runtimeEnvironment;
    }

    /**
     *
     * @param runtimeEnvironment application execution environment
     */
    public void setRuntimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
        this.runtimeEnvironment = runtimeEnvironment;
    }

    /**
     *
     * @return list of events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     *
     * @param events list of events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     *
     * @return a map of user attribute lists
     */
    public Map<String, List<String>> getUserAttributeLists() {
        return userAttributeLists;
    }

    /**
     *
     * @param userAttributeLists
     */
    public void setUserAttributeLists(Map<String, List<String>> userAttributeLists) {
        this.userAttributeLists = userAttributeLists;
    }

    /**
     * Integration attributes provide a way to set user/request-level attributes specific
     * to an integration. For example, a Kit in the mobile SDK can set an ID for use
     * by its respective server-side integration.
     *
     * @return
     */
    public Map<String, String> getIntegrationAttributes() {
        return integrationAttributes;
    }

    /**
     * Integration attributes provide a way to set user/request-level attributes specific
     * to an integration. For example, a Kit in the mobile SDK can set an ID for use
     * by its respective server-side integration.
     *
     * @param integrationAttributes
     */
    public void setIntegrationAttributes(Map<String, String> integrationAttributes) {
        this.integrationAttributes = integrationAttributes;
    }

    /**
     * Gets the source channel of this request. This is the originating source of the data, such
     * as one of mParticle's native SDKs, or server APIs.
     *
     * @see com.mparticle.sdk.model.Consts.ChannelSourceType
     * @return returns the string constant representing the source
     */
    public String getSourceChannel() {
        return sourceChannel;
    }

    /**
     *
     * @param sourceChannel the originating source of the data, such as one of mParticle's native SDKs, or server APIs.
     * @see com.mparticle.sdk.model.Consts.ChannelSourceType
     */
    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    /**
     *
     * @return Device Application Stamp
     */
    public String getDeviceApplicationStamp() {
        return deviceApplicationStamp;
    }

    /**
     *
     * @param deviceApplicationStamp Device Application Stamp
     */
    public void setDeviceApplicationStamp(String deviceApplicationStamp) {
        this.deviceApplicationStamp = deviceApplicationStamp;
    }

    /**
     *
     * @return the Consent State of the user profile to which this request applies.
     */
    public ConsentState getConsentState() {
        return consentState;
    }

    /**
     *
     * @param consentState the Consent State of the user profile to which this request applies.
     */
    public void setConsentState(ConsentState consentState) {
        this.consentState = consentState;
    }

    public List<SystemNotification> getSystemNotifications() {
        return systemNotifications;
    }

    public void setSystemNotifications(List<SystemNotification> systemNotifications) {
        this.systemNotifications = systemNotifications;
    }

    /**
     * Gets the MP ID for this user.
     *
     * @return mpid
     */
    public String getMpId() { return mpId; }

    /**
     * Sets the MP ID for this user.
     */
    public void setMpId(String mpid) { this.mpId = mpid; }

    /**
     * Gets the platform fields for this request.
     *
     * @return platform fields
     */
    public PlatformFields getPlatformFields() {
        return platformFields;
    }

    /**
     * Sets the platform fields for this request.
     */
    public void setPlatformFields(PlatformFields platformFields) { this.platformFields = platformFields; }

    /**
     *
     * @return partner identities
     */
    public List<PartnerIdentity> getPartnerIdentities() {
        return partnerIdentities;
    }

    /**
     *
     * @param partnerIdentities partner identities
     */
    public void setPartnerIdentities(List<PartnerIdentity> partnerIdentities) {
        this.partnerIdentities = partnerIdentities;
    }
}