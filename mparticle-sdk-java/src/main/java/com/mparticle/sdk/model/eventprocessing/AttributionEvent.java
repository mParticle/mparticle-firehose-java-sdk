package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public final class AttributionEvent extends Event {

    public AttributionEvent() {
        super(Type.CUSTOM_EVENT);
    }

    @JsonProperty("partner_name")
    private String partner;

    @JsonProperty("publisher_name")
    private String publisher;

    @JsonProperty("campaign_name")
    private String campaign;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("ad_network")
    private String adNetwork;

    @JsonProperty("click_time")
    private String clickTime;

    @JsonProperty("identities")
    private List<DeviceIdentity> deviceIdentities;

    @JsonProperty("limit_ad_tracking")
    private boolean isLimitAdTrackingEnabled;

    @JsonProperty("attributes")
    private Map<String, String> attributes;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAdNetwork() {
        return adNetwork;
    }

    public void setAdNetwork(String adNetwork) {
        this.adNetwork = adNetwork;
    }

    public String getClickTime() {
        return clickTime;
    }

    public void setClickTime(String clickTime) {
        this.clickTime = clickTime;
    }

    public List<DeviceIdentity> getDeviceIdentities() {
        return deviceIdentities;
    }

    public void setDeviceIdentities(List<DeviceIdentity> deviceIdentities) {
        this.deviceIdentities = deviceIdentities;
    }

    public boolean isLimitAdTrackingEnabled() {
        return isLimitAdTrackingEnabled;
    }

    public void setLimitAdTrackingEnabled(boolean limitAdTrackingEnabled) {
        isLimitAdTrackingEnabled = limitAdTrackingEnabled;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}