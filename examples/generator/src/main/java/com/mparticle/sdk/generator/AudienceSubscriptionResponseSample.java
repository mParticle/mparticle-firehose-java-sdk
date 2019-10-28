package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionResponse;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class AudienceSubscriptionResponseSample {
    public static Map.Entry<String, AudienceSubscriptionResponse> GenerateMessage()
    {
        AudienceSubscriptionResponse req = new AudienceSubscriptionResponse();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);

        req.setAudienceSubscriptionSettings(new HashMap<>());
        req.getAudienceSubscriptionSettings().put("sample setting", "sample setting value");
        req.getAudienceSubscriptionSettings().put("sample setting 2", "sample setting value 2");
        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
