package com.mparticle.sdk;

import com.mparticle.sdk.model.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.RequestContext;
import com.mparticle.sdk.model.registration.RegistrationRequest;
import com.mparticle.sdk.model.registration.RegistrationResponse;

public abstract class MessageProcessor {

    public final Message processMessage(Message request) {

        switch (request.type) {

            case REGISTRATION_REQUEST: {
                RegistrationResponse response = new RegistrationResponse();
                processRegistrationRequest((RegistrationRequest) request, response);
                return response;
            }

            case EVENT_PROCESSING_REQUEST: {
                EventProcessingResponse response = new EventProcessingResponse();
                processEventStreamRequest((EventProcessingRequest) request, response);
                return response;
            }

        }

        return null;
    }

    public abstract void processRegistrationRequest(RegistrationRequest request,RegistrationResponse result);

    private void processEventStreamRequest(EventProcessingRequest request, EventProcessingResponse response) {

        RequestContext context = new RequestContext();
        context.app = request.app;
        context.device = request.device;
        context.user = request.user;
        context.subscription = request.subscription;

        for (Event e : request.events) {

            switch (e.type) {

                case APP_EVENT:
                    processAppEvent((AppEvent)e, context);
                    break;

                case SESSION_START_EVENT:
                    processSessionStartEvent((SessionStartEvent) e, context);
                    break;
            }

            response.stats.add(e);
        }
    }

    public void processAppEvent(AppEvent event, RequestContext context) {

    }

    public void processSessionStartEvent(SessionStartEvent event, RequestContext context) {

    }
}
