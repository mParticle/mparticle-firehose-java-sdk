package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SessionEndEvent extends Event {

    @JsonProperty("session_length_ms")
    private long sessionLength;

    /**
     *
     * @return session length in milliseconds
     */
    public long getSessionLength() {
        return sessionLength;
    }

    /**
     *
     * @param sessionLength  session length in milliseconds
     */
    public void setSessionLength(long sessionLength) {
        this.sessionLength = sessionLength;
    }

    public SessionEndEvent() {
        super(Type.SESSION_END);
    }
}

