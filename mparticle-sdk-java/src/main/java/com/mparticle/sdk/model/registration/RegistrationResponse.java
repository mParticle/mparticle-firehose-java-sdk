package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import java.util.List;

public class RegistrationResponse extends Message {

    @JsonProperty(value="name", required=true)
    private String moduleName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("settings")
    private List<Setting> settings;

    @JsonProperty("max_data_age_hours")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int maxDataAgeHours;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        if (moduleName.isEmpty()) throw new IllegalArgumentException();
        this.moduleName = moduleName;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
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

    /*

    @JsonProperty("required_identities")
    public List<UserIdentity.Type> requiredIdentities;

    */

    private RegistrationResponse() {
        super(Message.Type.REGISTRATION_RESPONSE);
    }

    public RegistrationResponse(String moduleName) {
        this();
        setModuleName(moduleName);
    }

}
