package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.eventprocessing.Event;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;

import java.util.List;

public class RegistrationResponse extends Message {

    @JsonProperty(value="name", required=true)
    public String name;

    @JsonProperty("description")
    public String description;

    @JsonProperty("settings")
    public List<Setting> settings;

    @JsonProperty("supported_events")
    public List<Event.Type> supportedEvents;

    @JsonProperty("required_identities")
    public List<UserIdentity.Type> requiredIdentities;

    @JsonProperty("max_data_age_hours")
    public int maxDataAgeHours;

    public RegistrationResponse() {
        super(Message.Type.REGISTRATION_RESPONSE);
    }

}
