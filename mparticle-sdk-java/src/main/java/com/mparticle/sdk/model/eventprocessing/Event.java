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
        @JsonSubTypes.Type(name="push_message_receipt", value=PushMessageReceiptEvent.class),
        @JsonSubTypes.Type(name="product_action", value=ProductActionEvent.class),
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

    /**
     *
     * @return event type
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @return event id
     */
    public UUID getId() {
        return id;
    }

    /**
     *
     * @return time in milliseconds
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp time in milliseconds
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return source event id
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     *
     * @param sourceId  source event id
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    /**
     *
     * @return session id
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     *
     * @param sessionId session id
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     *
     * @return Geo location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location Geo location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return account, user, and device information
     */
    public Context getContext() {
        return context;
    }

    /**
     *
     * @param context account, user, and device information
     */
    public void setContext(Context context) {
        this.context = context;
    }

    public Event(Type eventType) {
        this.type = eventType;
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Event types.
     */
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
        APPLICATION_STATE_TRANSITION,
        PUSH_MESSAGE_RECEIPT,
        PRODUCT_ACTION;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    /**
     * Account, user, and device information.
     */
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


