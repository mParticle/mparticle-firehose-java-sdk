package com.mparticle.sdk.model;

public final class Consts {

    public static final String SDK_VERSION= "2.6.0";

    private Consts() {
    }

    /**
     * Indicates the source of the original Batch-object when
     * sent to mParticle's server.
     */
    public interface ChannelSourceType {
        String UNKNOWN = "unknown";
        /**
         * Native SDKs (iOS, Android, Roku)
         */
        String NATIVE = "native";
        /**
         * Mobile Web / mParticle Javascript SDK
         */
        String JAVASCRIPT = "javascript";
        String PIXEL = "pixel";
        /**
         * mParticle-feeds integration
         */
        String PARTNER = "partner";
        /**
         * mParticle server-API
         */
        String SERVER_TO_SERVER = "server_to_server";
    }
}
