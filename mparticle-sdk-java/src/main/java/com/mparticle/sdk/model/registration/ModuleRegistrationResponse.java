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

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public EventProcessingRegistration getEventProcessingRegistration() {
        return eventProcessingRegistration;
    }

    public void setEventProcessingRegistration(EventProcessingRegistration eventProcessingRegistration) {
        this.eventProcessingRegistration = eventProcessingRegistration;
    }

    public AudienceProcessingRegistration getAudienceProcessingRegistration() {
        return audienceProcessingRegistration;
    }

    public void setAudienceProcessingRegistration(AudienceProcessingRegistration audienceProcessingRegistration) {
        this.audienceProcessingRegistration = audienceProcessingRegistration;
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
