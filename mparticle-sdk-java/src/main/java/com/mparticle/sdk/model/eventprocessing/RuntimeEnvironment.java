package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="unknown", value=UnknownRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="android", value=AndroidRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="ios", value=IosRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="tvos", value=TVOSRuntimeEnvironment.class)
})
public abstract class RuntimeEnvironment {

    private final Type type;

    @JsonProperty("is_debug")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isDebug;

    @JsonProperty("client_ip_address")
    private String clientIpAddress;

    public RuntimeEnvironment(Type type) {
        this.type = type;
    }

    /**
     *
     * @return runtime environment type
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @return true if application is running in the debug mode
     */
    public boolean isDebug() {
        return isDebug;
    }

    /**
     * Get the IP address of the original request. Requests to the mParticle API may come from
     * a browser, and iOS/tvOS/Android device, or a server when data originates from
     * a server-to-server integration.
     *
     * @return returns the IP address of the client request
     */
    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public enum Type {
        UNKNOWN,
        ANDROID,
        IOS,
        TVOS;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
