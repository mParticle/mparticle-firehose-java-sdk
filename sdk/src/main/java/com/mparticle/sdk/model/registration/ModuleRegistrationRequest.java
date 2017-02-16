package com.mparticle.sdk.model.registration;

import com.mparticle.sdk.model.Message;

public class ModuleRegistrationRequest extends Message {
    public ModuleRegistrationRequest() {
        super(Type.MODULE_REGISTRATION_REQUEST);
    }
}
