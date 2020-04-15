package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;

import java.util.AbstractMap;
import java.util.Map;

public class AudienceMembershipChangeResponseSample {

    public static Map.Entry<String, AudienceMembershipChangeResponse> GenerateMessage()
    {
        AudienceMembershipChangeResponse req = new AudienceMembershipChangeResponse();

        req.setTimestamp(System.currentTimeMillis());
        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
