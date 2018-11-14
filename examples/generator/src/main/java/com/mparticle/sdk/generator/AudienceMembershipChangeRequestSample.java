package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;

import java.util.Arrays;

public class AudienceMembershipChangeRequestSample {

    public static AudienceMembershipChangeRequest GenerateMessage()
    {
        AudienceMembershipChangeRequest req = new AudienceMembershipChangeRequest();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);

        req.setAccount(Sample.generateAccount());
        req.setUserProfiles(Arrays.asList(Sample.generateUserProfiles()));
        req.setUserProfiles(Arrays.asList(Sample.generateUserProfiles()));
        return req;
    }
}
