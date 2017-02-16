package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public final class AttributionEvent extends Event {

    public AttributionEvent() {
        super(Type.ATTRIBUTION);
    }

    @JsonProperty("partner_name")
    private String partner;

    @JsonProperty("publisher_name")
    private String publisher;

    @JsonProperty("campaign_name")
    private String campaign;

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
    
    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}