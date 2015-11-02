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

    /**
     *
     * @return error message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return true if the error has been captured by the crash reporter
     */
    public boolean isCrash() {
        return isCrash;
    }

    /**
     *
     * @param isCrash true if the error has been captured by the crash reporter
     */
    public void setIsCrash(boolean isCrash) {
        this.isCrash = isCrash;
    }

    /**
     *
     * @return stack trace
     */
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     *
     * @param stackTrace  stack trace
     */
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    /**
     *
     * @return bread crumbs
     */
    public List<String> getBreadcrumbs() {
        return breadcrumbs;
    }

    /**
     *
     * @param breadcrumbs bread crumbs
     */
    public void setBreadcrumbs(List<String> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    /**
     *
     * @return custom attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes custom attributes
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }


    public ErrorEvent() {
        super(Type.ERROR);
    }
}