package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
        @JsonSubTypes.Type(name="promotion_action", value=PromotionActionEvent.class),
        @JsonSubTypes.Type(name="impression", value=ImpressionEvent.class),
        @JsonSubTypes.Type(name="attribution", value=AttributionEvent.class),
        @JsonSubTypes.Type(name="push_message_open", value=PushMessageOpenEvent.class)
})
/**
 * Class representing an analytics-based event originating from an mParticle-instrumented application.
 */
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

    private EventProcessingRequest request;

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

    public Event(Type eventType) {
        this.type = eventType;
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
    }

    public EventProcessingRequest getRequest() {
        return request;
    }

    public void setRequest(EventProcessingRequest request) {
        this.request = request;
    }

    /**
     * Event types.
     */
    public enum Type {
        /**
         * This represents when a *user session* starts and the app has come into the foreground.
         */
        SESSION_START,
        /**
         * This represents when a *user session* ends. This will occur once the user has left the app, and the app is in the background for an amount of time. Once the session times-out, the session end event will be fired.
         */
        SESSION_END,
        /**
         * This is the generic event object used throughout all of mParticle's SDKs, used to log discrete events, associate with a name, an event type, and a map of keys and values.
         */
        CUSTOM_EVENT,
        /**
         * This represents when a user views a particular screen or page of an application.
         */
        SCREEN_VIEW,
        /**
         * This type of data represents an error that has occurred in an application, such as an exception, regardless of if it led to an app crash (unhandled) or not (handled).
         */
        ERROR,
        /**
         * This represents when a user has chosen to opt-out or opt-in to a service.
         */
        PRIVACY_SETTING_CHANGE,
        /**
         * The mParticle mobile and Javascript SDKs provide APIs to associate user attributes with the current user. This event represents when a user changes, either by adding, removing, or updated an attribute associated with profile.
         */
        USER_ATTRIBUTE_CHANGE,
        /**
         * Similar to user attributes, users can have 1 or more associated IDs, such as email. This event will fire when a user adds, removed, or updates an identity.
         */
        USER_IDENTITY_CHANGE,
        /**
         * This event represents when a user subscribes to push notifications with Apple's push notification service, or Google's Cloud Messaging service, and will typically contain the associated push token or ID.
         */
        PUSH_SUBSCRIPTION,
        /**
         * When a user cold-starts, resumes, leaves, or force-closes an app, an AST event will be generated. Note that a user may leave and return to an app several times during a single session.
         */
        APPLICATION_STATE_TRANSITION,
        /**
         * This event represents when the device receives a push notification.
         */
        PUSH_MESSAGE_RECEIPT,
        /**
         * This is the event used for all Product-related eCommerce events. There are several types of product actions, such as purchase and refund.
         */
        PRODUCT_ACTION,
        /**
         * This is the event used for eCommerce Promotion events. There are two types of promotion events, view and click.
         */
        PROMOTION_ACTION,
        /**
         * This is the event used for eCommerce Impression events.
         */
        IMPRESSION,

        ATTRIBUTION,
        /**
         * This event represents when the users opens a push notification.
         */
        PUSH_MESSAGE_OPEN;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}