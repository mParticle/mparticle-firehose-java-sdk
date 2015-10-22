package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class BooleanSetting extends Setting {

    @JsonProperty("checked")
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public BooleanSetting setIsChecked(boolean checked) {
        this.isChecked = checked;
        return this;
    }

    public BooleanSetting(String id, String name) {
        super(Type.BOOLEAN, id, name);
    }
}
