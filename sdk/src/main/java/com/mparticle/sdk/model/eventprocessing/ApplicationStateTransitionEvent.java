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

    /**
     * This field is collected by the Android SDK at the time of an app launch. It indicates the package
     * name of the app that launched the app.
     *
     * @return
     */
    public String getSourcePackage() {
        return sourcePackage;
    }

    public void setSourcePackage(String sourcePackage) {
        this.sourcePackage = sourcePackage;
    }

    /**
     * This field is collected by both the iOS and Android SDKs at the time of an app launch. It will contain
     * any options or bundle extras that were passed into the app delegate (iOS) or Activity Intent (Android).
     *
     * @return
     */
    public String getLaunchParameters() {
        return launchParameters;
    }

    public void setLaunchParameters(String launchParameters) {
        this.launchParameters = launchParameters;
    }

    /**
     * This field is collected by the iOS SDK at the time of an app launch. It will contain the source application
     * indicated by the iOS framework's UIApplicationOpenURLOptionsSourceApplicationKey key.
     *
     * @return
     */
    public String getReferralApplication() {
        return referralApplication;
    }

    public void setReferralApplication(String referralApplication) {
        this.referralApplication = referralApplication;
    }

    /**
     * This field is collected by both the iOS and Androd SDKs at the time of an app launch. It will contain
     * the URI associated with the app launch.
     *
     * @return
     */
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