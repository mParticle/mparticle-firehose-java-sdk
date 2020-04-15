package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;

import java.util.*;

public class AudienceMembershipChangeRequestSample {

    public static Map.Entry<String, AudienceMembershipChangeRequest> GenerateMessage()
    {
        AudienceMembershipChangeRequest req = new AudienceMembershipChangeRequest();

        req.setTimestamp(System.currentTimeMillis());

        req.setAccount(Sample.generateAccount());
        req.setUserProfiles(Collections.singletonList(Sample.generateUserProfile()));
        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
