package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivacySettingChangeEvent extends Event {

    @JsonProperty(value="setting", required=true)
    private PrivacySetting setting;

    @JsonProperty("is_enabled")
    private boolean isEnabled;

    public PrivacySetting getSetting() {
        return setting;
    }

    public void setSetting(PrivacySetting setting) {
        this.setting = setting;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public PrivacySettingChangeEvent() {
        super(Type.PRIVACY_SETTING_CHANGE);
    }

    public enum PrivacySetting {
        APP_DATA_COLLECTION;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
