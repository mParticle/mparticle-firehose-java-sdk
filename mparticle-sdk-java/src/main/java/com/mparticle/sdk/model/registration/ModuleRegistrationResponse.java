package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.Utils;

public final class ModuleRegistrationResponse extends Message {

    @JsonProperty("sdk_version")
    private final String sdkVersion = Consts.SDK_VERSION;

    @JsonProperty(value="name", required=true)
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty(value="version", required=true)
    private String version;


    @JsonProperty("permissions")
    private Permissions permissions;

    @JsonProperty("event_processing_registration")
    private EventProcessingRegistration eventProcessingRegistration;

    @JsonProperty("audience_processing_registration")
    private AudienceProcessingRegistration audienceProcessingRegistration;

    public String getName() {
        return name;
    }

    public ModuleRegistrationResponse setName(String name) {
        if (Utils.isNullOrEmpty(name)) throw new IllegalArgumentException();
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ModuleRegistrationResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public ModuleRegistrationResponse setVersion(String version) {
        if (Utils.isNullOrEmpty(version)) throw new IllegalArgumentException();
        this.version = version;
        return this;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public ModuleRegistrationResponse setPermissions(Permissions permissions) {
        this.permissions = permissions;
        return this;
    }

    public EventProcessingRegistration getEventProcessingRegistration() {
        return eventProcessingRegistration;
    }

    public ModuleRegistrationResponse setEventProcessingRegistration(EventProcessingRegistration eventProcessingRegistration) {
        this.eventProcessingRegistration = eventProcessingRegistration;
        return this;
    }

    public AudienceProcessingRegistration getAudienceProcessingRegistration() {
        return audienceProcessingRegistration;
    }

    public ModuleRegistrationResponse setAudienceProcessingRegistration(AudienceProcessingRegistration audienceProcessingRegistration) {
        this.audienceProcessingRegistration = audienceProcessingRegistration;
        return this;
    }

    private ModuleRegistrationResponse() {
        super(Message.Type.MODULE_REGISTRATION_RESPONSE);
    }

    public ModuleRegistrationResponse(String name, String version) {
        this();
        setName(name);
        setVersion(version);
    }
}
