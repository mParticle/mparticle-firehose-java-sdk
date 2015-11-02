package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class BooleanSetting extends Setting {

    @JsonProperty("checked")
    private boolean isChecked;

    /**
     *
     * @return true if set by default
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     *
     * @param checked true if set by default
     * @return this
     */
    public BooleanSetting setIsChecked(boolean checked) {
        this.isChecked = checked;
        return this;
    }

    public BooleanSetting(String id, String name) {
        super(Type.BOOLEAN, id, name);
    }
}
