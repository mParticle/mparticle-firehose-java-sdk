package com.mparticle.sdk.model.eventprocessing;

public class Device {

    // this might have to be made OS specific

    public DeviceType deviceType;

    public static class IosDevice extends Device {

    }

    public static class AndroidDevice extends Device {

    }

    public enum DeviceType {
        IOS_DEVICE,
        ANDROID_DEVICE
    }
}
