package com.mparticle.sdk.model.eventprocessing;

/** Samsung TV uses the generic device and application fields in Firehose payloads. */
public final class SamsungTVRuntimeEnvironment extends GenericRuntimeEnvironment {

    public SamsungTVRuntimeEnvironment() {
        super(Type.SAMSUNGTV);
    }
}
