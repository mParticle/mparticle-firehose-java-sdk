package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Web Environment
 */
public final class WebRuntimeEnvironment extends RuntimeEnvironment {
    
    @JsonProperty("build_id")
    private String buildId;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("product")
    private String product;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("device_manufacturer")
    private String deviceManufacturer;

    @JsonProperty("os_version")
    private String osVersion;

    @JsonProperty("device_model")
    private String deviceModel;

    @JsonProperty("screen_height")
    private int screenHeight;

    @JsonProperty("screen_width")
    private int screenWidth;

    @JsonProperty("screen_dpi")
    private int screenDpi;

    @JsonProperty("device_country")
    private String deviceCountry;

    @JsonProperty("locale_language")
    private String localeLanguage;

    @JsonProperty("locale_country")
    private String localeCountry;

    @JsonProperty("network_country")
    private String networkCountry;

    @JsonProperty("network_carrier")
    private String networkCarrier;

    @JsonProperty("network_code")
    private String mobileNetworkCode;

    @JsonProperty("network_mobile_country_code")
    private String mobileCountryCode;

    @JsonProperty("timezone_offset")
    private int utcOffset;

    @JsonProperty("push_token")
    private String pushToken;

    @JsonProperty("is_tablet")
    private Boolean isTablet;

    @JsonProperty("radio_access_technology")
    private String radioAccessTechnology;

    @JsonProperty("limit_ad_tracking")
    private String limitAdTracking;

    @JsonProperty("is_dst")
    private String isDayLightSavingsTime;

    public WebRuntimeEnvironment() {
        super(Type.MOBILEWEB);
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenDpi() {
        return screenDpi;
    }

    public void setScreenDpi(int screenDpi) {
        this.screenDpi = screenDpi;
    }

    public String getDeviceCountry() {
        return deviceCountry;
    }

    public void setDeviceCountry(String deviceCountry) {
        this.deviceCountry = deviceCountry;
    }

    public String getLocaleLanguage() {
        return localeLanguage;
    }

    public void setLocaleLanguage(String localeLanguage) {
        this.localeLanguage = localeLanguage;
    }

    public String getLocaleCountry() {
        return localeCountry;
    }

    public void setLocaleCountry(String localeCountry) {
        this.localeCountry = localeCountry;
    }

    public String getNetworkCountry() {
        return networkCountry;
    }

    public void setNetworkCountry(String networkCountry) {
        this.networkCountry = networkCountry;
    }

    public String getNetworkCarrier() {
        return networkCarrier;
    }

    public void setNetworkCarrier(String networkCarrier) {
        this.networkCarrier = networkCarrier;
    }

    public String getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(String mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    public String getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(String mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    /***
     * @deprecated This method delegates to the getter of the userAgent field. Left in for backwards compatibility
     * @return the user agent
     */
    @Deprecated
    public String getHttpHeaderUserAgent() {
        return super.getUserAgent();
    }

    /***
     * @deprecated This method delegates to the setter of the userAgent field. Left in for backwards compatibility
     * @param httpHeaderUserAgent the user agent to set
     */
    @Deprecated
    public void setHttpHeaderUserAgent(String httpHeaderUserAgent) {
        super.setUserAgent(httpHeaderUserAgent);
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public Boolean getIsTablet() {
        return isTablet;
    }

    public void setIsTablet(Boolean isTablet) {
        this.isTablet = isTablet;
    }

    public String getRadioAccessTechnology() {
        return radioAccessTechnology;
    }

    public void setRadioAccessTechnology(String radioAccessTechnology) {
        this.radioAccessTechnology = radioAccessTechnology;
    }

    public String getLimitAdTracking() {
        return limitAdTracking;
    }

    public void setLimitAdTracking(String limitAdTracking) {
        this.limitAdTracking = limitAdTracking;
    }

    public String getIsDayLightSavingsTime() {
        return isDayLightSavingsTime;
    }

    public void setIsDayLightSavingsTime(String isDayLightSavingsTime) {
        this.isDayLightSavingsTime = isDayLightSavingsTime;
    }    
}

