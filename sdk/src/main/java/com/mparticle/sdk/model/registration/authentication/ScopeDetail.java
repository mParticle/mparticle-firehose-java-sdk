package com.mparticle.sdk.model.registration.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ScopeDetail {
    @JsonProperty(value="name", required = true)
    private String name;

    @JsonProperty(value="description", required = true)
    private String description;

    /**
     *
     * @return Description of the scope
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description Description of the scope
     * @return this
     */
    public ScopeDetail setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return Name of the OAuth2 Scope
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name Name of the OAuth2 Scope
     * @return this
     */
    public ScopeDetail setName(String name) {
        this.name = name;
        return this;
    }
}
