package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionResponse;

import java.util.HashMap;

public class AudienceSubscriptionResponseSample {
    public static AudienceSubscriptionResponse GenerateMessage()
    {
        AudienceSubscriptionResponse req = new AudienceSubscriptionResponse();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);
        req.setMpId("12345");

        req.setAudienceSubscriptionSettings(new HashMap<String, String>());
        req.getAudienceSubscriptionSettings().put("sample setting", "sample setting value");
        req.getAudienceSubscriptionSettings().put("sample setting 2", "sample setting value 2");
        return req;
    }
}
