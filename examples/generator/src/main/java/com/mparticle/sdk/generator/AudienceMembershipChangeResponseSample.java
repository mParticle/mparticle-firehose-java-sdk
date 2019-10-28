package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;

import java.util.AbstractMap;
import java.util.Map;

public class AudienceMembershipChangeResponseSample {

    public static Map.Entry<String, AudienceMembershipChangeResponse> GenerateMessage()
    {
        AudienceMembershipChangeResponse req = new AudienceMembershipChangeResponse();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);
        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
