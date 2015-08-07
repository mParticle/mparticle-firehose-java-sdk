package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BooleanSetting extends Setting {

    @JsonProperty("checked")
    private boolean isChecked;

    @JsonProperty("checked_value")
    private String checkedValue = "true";

    @JsonProperty("unchecked_value")
    private String uncheckedValue = "false";

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        this.isChecked = checked;
    }

    public String getCheckedValue() {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }

    public String getUncheckedValue() {
        return uncheckedValue;
    }

    public void setUncheckedValue(String uncheckedValue) {
        this.uncheckedValue = uncheckedValue;
    }

    public BooleanSetting(String id, String name) {
        super(Type.BOOLEAN, id, name);
    }
}
