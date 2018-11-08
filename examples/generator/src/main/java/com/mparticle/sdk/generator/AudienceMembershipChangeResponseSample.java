package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;

public class AudienceMembershipChangeResponseSample {

    public static AudienceMembershipChangeResponse GenerateMessage()
    {
        AudienceMembershipChangeResponse req = new AudienceMembershipChangeResponse();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);
        req.setMpId("12345");
        return req;
    }
}
