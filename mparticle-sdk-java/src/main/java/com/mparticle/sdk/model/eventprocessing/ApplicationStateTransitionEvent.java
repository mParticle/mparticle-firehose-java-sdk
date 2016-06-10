package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ApplicationStateTransitionEvent extends Event {

    @JsonProperty(value="application_state", required=true)
    private ApplicationState applicationState;

    @JsonProperty(value="launch_referral")
    private String launchReferral;

    @JsonProperty(value="referral_application")
    private String referralApplication;

    @JsonProperty(value="launch_parameters")
    private String launchParameters;

    @JsonProperty(value="source_package")
    private String sourcePackage;

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public ApplicationStateTransitionEvent() {
        super(Type.APPLICATION_STATE_TRANSITION);
    }

    public String getSourcePackage() {
        return sourcePackage;
    }

    public void setSourcePackage(String sourcePackage) {
        this.sourcePackage = sourcePackage;
    }

    public String getLaunchParameters() {
        return launchParameters;
    }

    public void setLaunchParameters(String launchParameters) {
        this.launchParameters = launchParameters;
    }

    public String getReferralApplication() {
        return referralApplication;
    }

    public void setReferralApplication(String referralApplication) {
        this.referralApplication = referralApplication;
    }

    public String getLaunchReferral() {
        return launchReferral;
    }

    public void setLaunchReferral(String launchReferral) {
        this.launchReferral = launchReferral;
    }

    public enum ApplicationState {
        INSTALL,
        UPGRADE,
        INITIALIZE,
        EXIT,
        BACKGROUND,
        FOREGROUND;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}