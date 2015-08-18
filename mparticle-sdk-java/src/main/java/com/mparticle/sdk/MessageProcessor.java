package com.mparticle.sdk;

import com.mparticle.sdk.model.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;

import java.util.ArrayList;

public abstract class MessageProcessor {

    public final Message processMessage(Message request) {

        switch (request.getType()) {

            case MODULE_REGISTRATION_REQUEST: {
                return processRegistrationRequest((ModuleRegistrationRequest) request);
            }

            case EVENT_PROCESSING_REQUEST: {
                return processEventStreamRequest((EventProcessingRequest) request);
            }

        }

        // TODO: Return message NotSupported response?
        return null;
    }

    public abstract ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request);

    private EventProcessingResponse processEventStreamRequest(EventProcessingRequest request) {

        EventProcessingResponse response = new EventProcessingResponse();
        response.processingResults = new ArrayList<>();

        Event.Context context = new Event.Context(request);

        for (Event e : request.getEvents()) {

            e.setContext(context);
            EventProcessingResult result = null;

            switch (e.getType()) {

                case CUSTOM_EVENT:
                    result = processCustomEvent((CustomEvent) e);
                    break;

                case SESSION_START_EVENT:
                    result = processSessionStartEvent((SessionStartEvent) e);
                    break;

                case SESSION_END_EVENT:
                    result = processSessionEndEvent((SessionEndEvent) e);
                    break;
            }

            if (result == null) {
                result = new EventProcessingResult(e.getId(), EventProcessingResult.Action.DISCARDED);
            }

            response.processingResults.add(result);
        }

        return response;
    }

    public EventProcessingResult processSessionStartEvent(SessionStartEvent event) {
        return null;
    }

    public EventProcessingResult processSessionEndEvent(SessionEndEvent event) {
        return null;
    }

    public EventProcessingResult processCustomEvent(CustomEvent event) {
        return null;
    }
}
