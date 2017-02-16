package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Promotion {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("creative")
    private String creative;

    @JsonProperty("position")
    private String position;

    public String getId() {
        return id;
    }

    public Promotion setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Promotion setName(String name) {
        this.name = name;
        return this;
    }

    public String getCreative() {
        return creative;
    }

    public Promotion setCreative(String creative) {
        this.creative = creative;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Promotion setPosition(String position) {
        this.position = position;
        return this;
    }
}
