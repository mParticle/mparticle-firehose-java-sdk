package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BooleanSetting extends Setting {

    @JsonProperty("checked")
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        this.isChecked = checked;
    }

    public BooleanSetting(String id, String name) {
        super(Type.BOOLEAN, id, name);
    }
}
