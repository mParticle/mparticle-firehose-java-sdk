package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;

public class ModuleRegistrationRequestSample {
    public static ModuleRegistrationRequest GenerateMessage() {
        ModuleRegistrationRequest req = new ModuleRegistrationRequest();

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(1454693235751L);

        return req;
    }
}
