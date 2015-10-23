package com.mparticle.sdk;

import com.mparticle.sdk.model.*;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionResponse;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;

import java.io.IOException;

public abstract class MessageProcessor {

    public final Message processMessage(Message request)  throws IOException {

        switch (request.getType()) {

            case MODULE_REGISTRATION_REQUEST:
                return processRegistrationRequest((ModuleRegistrationRequest) request);

            case EVENT_PROCESSING_REQUEST:
                return processEventProcessingRequest((EventProcessingRequest) request);

            case AUDIENCE_SUBSCRIPTION_REQUEST:
                return processAudienceSubscriptionRequest((AudienceSubscriptionRequest) request);

            case AUDIENCE_MEMBERSHIP_CHANGE_REQUEST:
                return processAudienceMembershipChangeRequest((AudienceMembershipChangeRequest) request);

            default:
                throw new UnsupportedOperationException("The message type \"" + request.getType() + "\" is not supported.");
        }
    }

    public abstract ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request);

    public EventProcessingResponse processEventProcessingRequest(EventProcessingRequest request)  throws IOException {

        EventProcessingResponse response = new EventProcessingResponse();
        Event.Context context = new Event.Context(request);

        for (Event e : request.getEvents()) {

            e.setContext(context);

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
            }
        }

        return response;
    }

    private void processProductActionEvent(ProductActionEvent event) throws IOException {

    }

    public void processPushMessageReceiptEvent(PushMessageReceiptEvent event) throws IOException {

    }

    public void processApplicationStateTransitionEvent(ApplicationStateTransitionEvent event) throws IOException {

    }

    public void processPushSubscriptionEvent(PushSubscriptionEvent event)  throws IOException {

    }

    public void processUserIdentityChangeEvent(UserIdentityChangeEvent event)  throws IOException {

    }

    public void processUserAttributeChangeEvent(UserAttributeChangeEvent event)  throws IOException {

    }

    public void processSessionStartEvent(SessionStartEvent event)  throws IOException {

    }

    public void processSessionEndEvent(SessionEndEvent event)  throws IOException {

    }

    public void processCustomEvent(CustomEvent event)  throws IOException {

    }

    public void processErrorEvent(ErrorEvent event)  throws IOException {

    }

    public void processScreenViewEvent(ScreenViewEvent event)  throws IOException {

    }

    public void processPrivacySettingChangeEvent(PrivacySettingChangeEvent event)  throws IOException {

    }

    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {
        return new AudienceMembershipChangeResponse();
    }

    public AudienceSubscriptionResponse processAudienceSubscriptionRequest(AudienceSubscriptionRequest request) throws IOException {
        return new AudienceSubscriptionResponse();
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    protected final void log(String string) {
        if (this.logger != null)
            logger.log(string);
    }

    private Logger logger;
}
