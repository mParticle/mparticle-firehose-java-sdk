package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.Utils;

public class ModuleRegistrationResponse extends Message {

    @JsonProperty(value="name", required=true)
    private String name;

    @JsonProperty(value="version", required=true)
    private String version;

    @JsonProperty("access_permissions")
    private AccessPermissions accessPermissions;

    @JsonProperty("event_processing_registration")
    private EventProcessingRegistration eventProcessingRegistration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Utils.isNullOrEmpty(name)) throw new IllegalArgumentException();
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        if (Utils.isNullOrEmpty(version)) throw new IllegalArgumentException();
        this.version = version;
    }

    public AccessPermissions getAccessPermissions() {
        return accessPermissions;
    }

    public void setAccessPermissions(AccessPermissions accessPermissions) {
        this.accessPermissions = accessPermissions;
    }

    private ModuleRegistrationResponse() {
        super(Message.Type.MODULE_REGISTRATION_RESPONSE);
    }

    public ModuleRegistrationResponse(String name, String version) {
        this();
        setName(name);
        setVersion(version);
    }

    public EventProcessingRegistration getEventProcessingRegistration() {
        return eventProcessingRegistration;
    }

    public void setEventProcessingRegistration(EventProcessingRegistration eventProcessingRegistration) {
        this.eventProcessingRegistration = eventProcessingRegistration;
    }
}
