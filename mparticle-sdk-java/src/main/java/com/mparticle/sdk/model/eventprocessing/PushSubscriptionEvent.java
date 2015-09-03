package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PushSubscriptionEvent extends Event {

    @JsonProperty(value="action", required=true)
    private Action action;

    @JsonProperty(value="token", required=true)
    private String token;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PushSubscriptionEvent() {
        super(Type.PUSH_SUBSCRIPTION);
    }

    public enum Action {
        SUBSCRIBE,
        UNSUBSCRIBE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}