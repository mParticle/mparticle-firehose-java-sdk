package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mparticle.sdk.model.registration.Account;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="session_start", value=SessionStartEvent.class),
        @JsonSubTypes.Type(name="session_end", value=SessionEndEvent.class),
        @JsonSubTypes.Type(name="custom_event", value=CustomEvent.class),
        @JsonSubTypes.Type(name="screen_view", value=ScreenViewEvent.class),
        @JsonSubTypes.Type(name="error", value=ErrorEvent.class),
        @JsonSubTypes.Type(name="privacy_setting_change", value=PrivacySettingChangeEvent.class),
        @JsonSubTypes.Type(name="user_attribute_change", value=UserAttributeChangeEvent.class),
        @JsonSubTypes.Type(name="user_identity_change", value=UserIdentityChangeEvent.class),
        @JsonSubTypes.Type(name="push_subscription", value=PushSubscriptionEvent.class),
        @JsonSubTypes.Type(name="application_state_transition", value=ApplicationStateTransitionEvent.class),
})
public abstract class Event {

    private final Type type;

    @JsonProperty(value="id", required=true)
    private final UUID id;

    @JsonProperty(value="timestamp_ms", required=true)
    private long timestamp;

    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty("session_id")
    private Long sessionId;

    @JsonProperty("location")
    private Location location;

    private Context context;

    public Type getType() {
        return type;
    }

    public UUID getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Event(Type eventType) {
        this.type = eventType;
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
    }

    public enum Type {
        SESSION_START,
        SESSION_END,
        CUSTOM_EVENT,
        SCREEN_VIEW,
        ERROR,
        PRIVACY_SETTING_CHANGE,
        USER_ATTRIBUTE_CHANGE,
        USER_IDENTITY_CHANGE,
        PUSH_SUBSCRIPTION,
        APPLICATION_STATE_TRANSITION;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public static final class Context {

        private final Account account;
        private final List<UserIdentity> userIdentities;
        private final Map<String, String> userAttributes;
        private final RuntimeEnvironment runtimeEnvironment;

        public Account getAccount() {
            return account;
        }

        public List<UserIdentity> getUserIdentities() {
            return userIdentities;
        }

        public Map<String, String> getUserAttributes() {
            return userAttributes;
        }

        public RuntimeEnvironment getRuntimeEnvironment() {
            return runtimeEnvironment;
        }

        public Context(EventProcessingRequest request) {
            this.account = request.getAccount();
            this.userIdentities = request.getUserIdentities();
            this.userAttributes = request.getUserAttributes();
            this.runtimeEnvironment = request.getRuntimeEnvironment();
        }

    }
}


