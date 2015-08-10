package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.Utils;

import java.util.List;

public class ModuleRegistrationResponse extends Message {

    @JsonProperty(value="name", required=true)
    private String name;

    @JsonProperty(value="version", required=true)
    private String version;

    @JsonProperty("description")
    private String description;

    @JsonProperty("settings")
    private List<Setting> settings;

    @JsonProperty("permissions")
    private List<Permission> permissions;

    @JsonProperty("max_data_age_hours")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int maxDataAgeHours;

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

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxDataAgeHours() {
        return maxDataAgeHours;
    }

    public void setMaxDataAgeHours(int maxDataAgeHours) {
        this.maxDataAgeHours = maxDataAgeHours;
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
