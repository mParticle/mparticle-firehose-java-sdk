package com.mparticle.sdk.model.registration;

import com.mparticle.sdk.model.Message;

public class RegistrationRequest extends Message {
    public RegistrationRequest() {
        super(Type.REGISTRATION_REQUEST);
    }
}
