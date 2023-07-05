package com.mparticle.sdk.generator;

import java.util.AbstractMap;
import java.util.Map;

import com.mparticle.sdk.model.dsrprocessing.DsrProcessingResponse;

public class DsrProcessingResponseSample {
    public static Map.Entry<String, DsrProcessingResponse> GenerateMessage()
    {
        DsrProcessingResponse req = new DsrProcessingResponse();

        req.setTimestamp(System.currentTimeMillis());
        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
