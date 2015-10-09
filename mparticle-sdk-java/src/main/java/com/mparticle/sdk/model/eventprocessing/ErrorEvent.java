package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public final class ErrorEvent extends Event {

    @JsonProperty("message")
    private String message;

    @JsonProperty("is_crash")
    private boolean isCrash;

    @JsonProperty("stack_trace")
    private String stackTrace;

    @JsonProperty("breadcrumbs")
    private List<String> breadcrumbs;

    @JsonProperty("attributes")
    private Map<String, String> attributes;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCrash() {
        return isCrash;
    }

    public void setIsCrash(boolean isCrash) {
        this.isCrash = isCrash;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public List<String> getBreadcrumbs() {
        return breadcrumbs;
    }

    public void setBreadcrumbs(List<String> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }


    public ErrorEvent() {
        super(Type.ERROR);
    }
}