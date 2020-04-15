package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.eventprocessing.EventProcessingResponse;

import java.util.AbstractMap;
import java.util.Map;

public class EventProcessingResponseSample {
    public static Map.Entry<String, EventProcessingResponse> GenerateMessage()
    {
        EventProcessingResponse req = new EventProcessingResponse();

        req.setTimestamp(System.currentTimeMillis());

        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
