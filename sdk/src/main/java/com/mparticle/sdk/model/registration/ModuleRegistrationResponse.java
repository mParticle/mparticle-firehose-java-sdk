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

    /**
     *
     * @return module registration name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name module registration name
     * @return this
     * @throws IllegalArgumentException
     */
    public ModuleRegistrationResponse setName(String name) throws IllegalArgumentException {
        if (Utils.isNullOrEmpty(name)) throw new IllegalArgumentException();
        this.name = name;
        return this;
    }

    /**
     *
     * @return user-friendly description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description user-friendly description
     * @return this
     */
    public ModuleRegistrationResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return module version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version module version
     * @return this
     */
    public ModuleRegistrationResponse setVersion(String version) {
        if (Utils.isNullOrEmpty(version)) throw new IllegalArgumentException();
        this.version = version;
        return this;
    }

    /**
     *
     * @return identity access requests
     */
    public Permissions getPermissions() {
        return permissions;
    }

    /**
     *
     * @param permissions identity access requests
     * @return
     */
    public ModuleRegistrationResponse setPermissions(Permissions permissions) {
        this.permissions = permissions;
        return this;
    }

    /**
     *
     * @return registration for receiving event data stream
     */
    public EventProcessingRegistration getEventProcessingRegistration() {
        return eventProcessingRegistration;
    }

    /**
     *
     * @param eventProcessingRegistration registration for receiving event data stream
     * @return this
     */
    public ModuleRegistrationResponse setEventProcessingRegistration(EventProcessingRegistration eventProcessingRegistration) {
        this.eventProcessingRegistration = eventProcessingRegistration;
        return this;
    }

    /**
     *
     * @return registration for receiving audience data stream
     */
    public AudienceProcessingRegistration getAudienceProcessingRegistration() {
        return audienceProcessingRegistration;
    }

    /**
     *
     * @param audienceProcessingRegistration registration for receiving audience data stream
     * @return this
     */
    public ModuleRegistrationResponse setAudienceProcessingRegistration(AudienceProcessingRegistration audienceProcessingRegistration) {
        this.audienceProcessingRegistration = audienceProcessingRegistration;
        return this;
    }

    private ModuleRegistrationResponse() {
        super(Message.Type.MODULE_REGISTRATION_RESPONSE);
    }

    /**
     *
     * @param name module registration name
     * @param version module version
     */
    public ModuleRegistrationResponse(String name, String version) {
        this();
        setName(name);
        setVersion(version);
    }
}
