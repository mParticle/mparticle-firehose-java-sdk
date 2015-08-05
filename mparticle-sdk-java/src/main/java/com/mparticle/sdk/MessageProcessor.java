package com.mparticle.sdk;

import com.mparticle.sdk.model.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.EventProcessingContext;
import com.mparticle.sdk.model.registration.RegistrationRequest;
import com.mparticle.sdk.model.registration.RegistrationResponse;

import java.util.ArrayList;

public abstract class MessageProcessor {

    public final Message processMessage(Message request) {

        switch (request.type) {

            case REGISTRATION_REQUEST: {
                return processRegistrationRequest((RegistrationRequest) request);
            }

            case EVENT_PROCESSING_REQUEST: {
                return processEventStreamRequest((EventProcessingRequest) request);
            }

        }

        // TODO: Return message NotSupported response?
        return null;
    }

    public abstract RegistrationResponse processRegistrationRequest(RegistrationRequest request);

    private EventProcessingResponse processEventStreamRequest(EventProcessingRequest request) {

        EventProcessingResponse response = new EventProcessingResponse();
        response.processingResults = new ArrayList<>();

        EventProcessingContext context = new EventProcessingContext();
        context.app = request.app;
        context.device = request.device;
        context.user = request.user;
        context.subscription = request.subscription;

        for (Event e : request.events) {

            EventProcessingResult result = null;

            switch (e.type) {

                case APP_EVENT:
                    result = processAppEvent((AppEvent)e, context);
                    break;

                case SESSION_START_EVENT:
                    result = processSessionStartEvent((SessionStartEvent) e, context);
                    break;

                case SESSION_END_EVENT:
                    result = processSessionEndEvent((SessionEndEvent) e, context);
                    break;
            }

            if (result == null) {
                result = new EventProcessingResult(e.id, EventProcessingResult.Action.DISCARDED);
            }

            response.processingResults.add(result);
        }

        return response;
    }

    public EventProcessingResult processSessionStartEvent(SessionStartEvent event, EventProcessingContext context) {
        return null;
    }

    public EventProcessingResult processSessionEndEvent(SessionEndEvent event, EventProcessingContext context) {
        return null;
    }

    public EventProcessingResult processAppEvent(AppEvent event, EventProcessingContext context) {
        return null;
    }
}
