package com.mparticle.sdk;

import com.mparticle.sdk.model.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;

import java.io.IOException;
import java.util.ArrayList;

public abstract class MessageProcessor {

    public final Message processMessage(Message request)  throws IOException {

        switch (request.getType()) {

            case MODULE_REGISTRATION_REQUEST: {
                return processRegistrationRequest((ModuleRegistrationRequest) request);
            }

            case EVENT_PROCESSING_REQUEST: {
                return processEventProcessingRequest((EventProcessingRequest) request);
            }

            default:
                throw new UnsupportedOperationException("The message type \"" + request.getType() + "\" is not supported.");
        }
    }


    public abstract ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request);

    public EventProcessingResponse processEventProcessingRequest(EventProcessingRequest request)  throws IOException {

        EventProcessingResponse response = new EventProcessingResponse();
        response.processingResults = new ArrayList<>();

        Event.Context context = new Event.Context(request);

        for (Event e : request.getEvents()) {

            e.setContext(context);
            EventProcessingResult result = null;

            switch (e.getType()) {

                case SESSION_START:
                    result = processSessionStartEvent((SessionStartEvent) e);
                    break;

                case SESSION_END:
                    result = processSessionEndEvent((SessionEndEvent) e);
                    break;

                case CUSTOM_EVENT:
                    result = processCustomEvent((CustomEvent) e);
                    break;

                case SCREEN_VIEW:
                    result = processScreenViewEvent((ScreenViewEvent) e);
                    break;

                case ERROR:
                    result = processErrorEvent((ErrorEvent) e);
                    break;

                case PRIVACY_SETTING_CHANGE:
                    result = processPrivacySettingChangeEvent((PrivacySettingChangeEvent) e);
                    break;

                case USER_ATTRIBUTE_CHANGE:
                    result = processUserAttributeChangeEvent((UserAttributeChangeEvent) e);
                    break;

                case USER_IDENTITY_CHANGE:
                    result = processUserIdentityChangeEvent((UserIdentityChangeEvent) e);
                    break;

                case PUSH_SUBSCRIPTION:
                    result = processPushSubscriptionEvent((PushSubscriptionEvent) e);
                    break;

                case APPLICATION_STATE_TRANSITION:
                    result = processApplicationStateTransitionEvent((ApplicationStateTransitionEvent) e);
                    break;
            }

            if (result == null) {
                result = new EventProcessingResult(e.getId(), EventProcessingResult.Action.DISCARDED);
            }

            response.processingResults.add(result);
        }

        return response;
    }

    public EventProcessingResult processApplicationStateTransitionEvent(ApplicationStateTransitionEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processPushSubscriptionEvent(PushSubscriptionEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processUserIdentityChangeEvent(UserIdentityChangeEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processUserAttributeChangeEvent(UserAttributeChangeEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processSessionStartEvent(SessionStartEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processSessionEndEvent(SessionEndEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processCustomEvent(CustomEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processErrorEvent(ErrorEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processScreenViewEvent(ScreenViewEvent event)  throws IOException {
        return null;
    }

    public EventProcessingResult processPrivacySettingChangeEvent(PrivacySettingChangeEvent event)  throws IOException {
        return null;
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
