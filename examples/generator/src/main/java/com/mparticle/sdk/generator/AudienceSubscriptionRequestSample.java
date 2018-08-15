package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionRequest;

public class AudienceSubscriptionRequestSample {
    public static AudienceSubscriptionRequest GenerateMessage()
    {
        AudienceSubscriptionRequest req = new AudienceSubscriptionRequest();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);

        req.setAccount(Sample.generateAccount());
        req.getAccount().getAccountSettings().put("apiKey", "sample API Key");

        req.setAudienceId(1234);
        req.setAudienceName("New Users Low Engagement");
        req.setAction(AudienceSubscriptionRequest.SubscriptionAction.ADD);
        return req;
    }
}
