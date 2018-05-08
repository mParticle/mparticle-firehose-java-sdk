package com.mparticle.sdk;

import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionResponse;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.notification.GDPRConsentStateNotification;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;

import java.io.IOException;

/**
 * Provides basic structure for implementing a custom message processing module.
 * <p>A typical module</p>
 * <ol>
 *     <li>Implements processRegistrationRequest to return registration information about the module.</li>
 *     <li>Adds custom logic to processEventProcessingRequest and/or event-specific handlers.</li>
 *     <li>Implements processAudienceMembershipChangeRequest if the module subscribes to the audience data stream.</li>
 *     <li>Implements processAudienceSubscriptionRequest if the module needs to handle audience subscription updates.</li>
 * </ol>
 */
public abstract class MessageProcessor {

    /**
     * Parses the message and invokes specific handler.
     * @param request Request message
     * @return Response message
     * @throws IOException
     */
    public final Message processMessage(Message request)  throws IOException {

        switch (request.getType()) {

            case MODULE_REGISTRATION_REQUEST:
                return processRegistrationRequest((ModuleRegistrationRequest) request);

            case EVENT_PROCESSING_REQUEST:
                return processEventProcessingRequest((EventProcessingRequest) request);

            case AUDIENCE_SUBSCRIPTION_REQUEST:
                return processAudienceSubscriptionRequest((AudienceSubscriptionRequest) request);

            case AUDIENCE_MEMBERSHIP_CHANGE_REQUEST:
                AudienceMembershipChangeRequest audienceMembershipChangeRequest = (AudienceMembershipChangeRequest)request;
                // ignore NOOP call
                if (audienceMembershipChangeRequest.getUserProfiles() != null) {
                    return processAudienceMembershipChangeRequest(audienceMembershipChangeRequest);
                } else {
                    return new AudienceMembershipChangeResponse();
                }
            default:
                throw new UnsupportedOperationException("The message type \"" + request.getType() + "\" is not supported.");
        }
    }

    /**
     * Handler for processing module registration request.
     *
     * <p>Every module is required to specify description, user settings,
     * supported events, and request access to specific user and device identifiers.</p>
     *
     * @param request request
     * @return response
     */
    public abstract ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request);

    /**
     * Handler for processing event processing request.
     *
     * <p>Base implementation parses the request and calls individual event and system notification handlers.</p>
     *
     * @param request request
     * @return response
     * @throws IOException
     */
    public EventProcessingResponse processEventProcessingRequest(EventProcessingRequest request)  throws IOException {
        EventProcessingResponse response = new EventProcessingResponse();
        if (request.getSystemNotifications() != null) {
            for (SystemNotification notification : request.getSystemNotifications()) {
                notification.setRequest(request);

                switch (notification.getType()) {
                    case GDPR_CONSENT_STATE:
                        processGDPRConsentStateNotification((GDPRConsentStateNotification)notification);
                        break;
                }
            }
        }

        for (Event e : request.getEvents()) {
            e.setRequest(request);
            switch (e.getType()) {

                case SESSION_START:
                    processSessionStartEvent((SessionStartEvent) e);
                    break;

                case SESSION_END:
                    processSessionEndEvent((SessionEndEvent) e);
                    break;

                case CUSTOM_EVENT:
                    processCustomEvent((CustomEvent) e);
                    break;

                case SCREEN_VIEW:
                    processScreenViewEvent((ScreenViewEvent) e);
                    break;

                case ERROR:
                    processErrorEvent((ErrorEvent) e);
                    break;

                case PRIVACY_SETTING_CHANGE:
                    processPrivacySettingChangeEvent((PrivacySettingChangeEvent) e);
                    break;

                case USER_ATTRIBUTE_CHANGE:
                    processUserAttributeChangeEvent((UserAttributeChangeEvent) e);
                    break;

                case USER_IDENTITY_CHANGE:
                    processUserIdentityChangeEvent((UserIdentityChangeEvent) e);
                    break;

                case PUSH_SUBSCRIPTION:
                    processPushSubscriptionEvent((PushSubscriptionEvent) e);
                    break;

                case APPLICATION_STATE_TRANSITION:
                    processApplicationStateTransitionEvent((ApplicationStateTransitionEvent) e);
                    break;

                case PUSH_MESSAGE_RECEIPT:
                    processPushMessageReceiptEvent((PushMessageReceiptEvent) e);
                    break;

                case PRODUCT_ACTION:
                    processProductActionEvent((ProductActionEvent) e);
                    break;

                case PROMOTION_ACTION:
                    processPromotionActionEvent((PromotionActionEvent) e);
                    break;

                case IMPRESSION:
                    processImpressionEvent((ImpressionEvent) e);
                    break;

                case ATTRIBUTION:
                    processAttributionEvent((AttributionEvent) e);
                    break;
            }
        }

        return response;
    }

    /**
     * Handler for processing GDPR Consent State system notifications
     *
     * @param notification
     * @throws IOException
     */
    public void processGDPRConsentStateNotification(GDPRConsentStateNotification notification) throws IOException {

    }

    /**
     * 
     * @param event
     * @throws IOException
     */
    public void processAttributionEvent(AttributionEvent event) throws IOException {

    }

    /**
     * Handler for processing ImpressionEvents.
     *
     * @param event
     * @throws IOException
     */
    public void processImpressionEvent(ImpressionEvent event) throws IOException {

    }

    /**
     * Handler for processing PromotionActionEvents.
     *
     * @param event
     * @throws IOException
     */
    public void processPromotionActionEvent(PromotionActionEvent event) throws IOException {

    }

    /**
     * Handler for processing ProductActionEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processProductActionEvent(ProductActionEvent event) throws IOException {

    }

    /**
     * Handler for processing PushMessageReceiptEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processPushMessageReceiptEvent(PushMessageReceiptEvent event) throws IOException {

    }

    /**
     * Handler for processing ApplicationStateTransitionEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processApplicationStateTransitionEvent(ApplicationStateTransitionEvent event) throws IOException {

    }

    /**
     * Handler for processing PushSubscriptionEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processPushSubscriptionEvent(PushSubscriptionEvent event)  throws IOException {

    }

    /**
     * Handler for processing UserIdentityChangeEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processUserIdentityChangeEvent(UserIdentityChangeEvent event)  throws IOException {

    }

    /**
     * Handler for processing UserAttributeChangeEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processUserAttributeChangeEvent(UserAttributeChangeEvent event)  throws IOException {

    }

    /**
     * Handler for processing SessionStartEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processSessionStartEvent(SessionStartEvent event)  throws IOException {

    }

    /**
     * Handler for processing SessionEndEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processSessionEndEvent(SessionEndEvent event)  throws IOException {

    }

    /**
     * Handler for processing CustomEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processCustomEvent(CustomEvent event)  throws IOException {

    }

    /**
     * Handler for processing ErrorEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processErrorEvent(ErrorEvent event)  throws IOException {

    }

    /**
     * Handler for processing ScreenViewEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processScreenViewEvent(ScreenViewEvent event)  throws IOException {

    }

    /**
     * Handler for processing PrivacySettingChangeEvent.
     *
     * @param event event
     * @throws IOException
     */
    public void processPrivacySettingChangeEvent(PrivacySettingChangeEvent event)  throws IOException {

    }

    /**
     * Handler for processing audience membership changes.
     *
     * @param request request
     * @return response
     * @throws IOException
     */
    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {
        return new AudienceMembershipChangeResponse();
    }

    /**
     * Handler for processing audience subscriptions.
     *
     * @param request request
     * @return response
     * @throws IOException
     */
    public AudienceSubscriptionResponse processAudienceSubscriptionRequest(AudienceSubscriptionRequest request) throws IOException {
        return new AudienceSubscriptionResponse();
    }

    /**
     * Sets logger.
     * @param logger logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Logs a message.
     * @param message
     */
    protected final void log(String message) {
        if (this.logger != null)
            logger.log(message);
    }

    private Logger logger;
}
