package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;

import java.util.AbstractMap;
import java.util.Map;

public class ModuleRegistrationRequestSample {
    public static Map.Entry<String, ModuleRegistrationRequest> GenerateMessage() {
        ModuleRegistrationRequest req = new ModuleRegistrationRequest();

        req.setTimestamp(System.currentTimeMillis());

        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
