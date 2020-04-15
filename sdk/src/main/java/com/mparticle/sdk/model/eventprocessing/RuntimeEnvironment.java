package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name="unknown", value=GenericRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="android", value=AndroidRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="ios", value=IosRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="tvos", value=TVOSRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="mobileweb", value=WebRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="roku", value=RokuRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="xbox", value=XboxRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="firetv", value=FireTVRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="smarttv", value=GenericRuntimeEnvironment.class),
        @JsonSubTypes.Type(name="alexa", value=GenericRuntimeEnvironment.class)
})
public abstract class RuntimeEnvironment {

    private final Type type;

    @JsonProperty("is_debug")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isDebug;

    @JsonProperty("client_ip_address")
    private String clientIpAddress;

    @JsonProperty("sdk_version")
    private String sdkVersion;

    @JsonProperty("http_header_user_agent")
    private String userAgent;

    @JsonProperty("identities")
    private List<DeviceIdentity> identities;

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

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    /**
     * Get the SDK version associated with this request. mParticle customers can use a variety of SDKs to send
     * data into the mParticle platform. The Android, iOS, and Javascript client SDKs will automatically include
     * their SDK version with each request, which will be included in the respective outgoing Firehose request.
     *
     * Customers who use the mParticle server-to-server SDKs, or who send data to mParticle using their own HTTP
     * clients may not include any SDK version.
     *
     * @param sdkVersion SDK version, ex: "6.12.1", may be null
     */
    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    /**
     *
     * @return the user agent HTTP header sent by this runtime environment
     */
    public String getUserAgent() { return userAgent; }

    /**
     * Set the user agent HTTP header that is sent by this runtime environment
     * @param userAgent value
     */
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }

    /**
     *
     * @return the device identities
     */
    public List<DeviceIdentity> getIdentities() {
        return this.identities;
    }

    /**
     * Set the device identities
     * @param identities
     */
    public void setIdentities(List<DeviceIdentity> identities) {
        this.identities = identities;
    }

    public enum Type {
        UNKNOWN,
        ANDROID,
        IOS,
        TVOS,
        MOBILEWEB,
        ROKU,
        XBOX,
        FIRETV,
        ALEXA,
        SMARTTV;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
