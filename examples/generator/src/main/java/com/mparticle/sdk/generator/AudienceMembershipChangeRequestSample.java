package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;

import java.util.*;

public class AudienceMembershipChangeRequestSample {

    public static Map.Entry<String, AudienceMembershipChangeRequest> GenerateMessage()
    {
        AudienceMembershipChangeRequest req = new AudienceMembershipChangeRequest();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);

        req.setAccount(Sample.generateAccount());
        req.setUserProfiles(Collections.singletonList(Sample.generateUserProfiles()));
        req.setUserProfiles(Collections.singletonList(Sample.generateUserProfiles()));
        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
