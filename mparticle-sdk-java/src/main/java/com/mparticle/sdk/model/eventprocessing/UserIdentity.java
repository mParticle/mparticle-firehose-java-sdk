package com.mparticle.sdk.model.eventprocessing;

public class UserIdentity {
    public enum Type {
        MPID,
        PUSH_REGISTRATION_TOKEN,
        ANDROID_DEVICE_ID,
        IOS_IDFA,
        IOS_IDFV;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
