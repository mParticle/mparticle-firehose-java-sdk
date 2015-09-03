package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ApplicationStateTransitionEvent extends Event {

    @JsonProperty(value="application_state", required=true)
    private ApplicationState applicationState;

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public ApplicationStateTransitionEvent() {
        super(Type.APPLICATION_STATE_TRANSITION);
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