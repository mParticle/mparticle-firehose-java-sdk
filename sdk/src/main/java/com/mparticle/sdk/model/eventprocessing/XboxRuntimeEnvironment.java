package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class XboxRuntimeEnvironment  extends RuntimeEnvironment {

    @JsonProperty("identities")
    private List<DeviceIdentity> identities;

    @JsonProperty("build_id")
    private String buildId;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("product")
    private String product;

    @JsonProperty("name")
    private String name;

    @JsonProperty("manufacturer")
    private String manufacturer;

    @JsonProperty("os_version")
    private String osVersion;

    @JsonProperty("model")
    private String model;

    @JsonProperty("screen_height")
    private int screenHeight;

    @JsonProperty("screen_width")
    private int screenWidth;

    @JsonProperty("country")
    private String country;

    @JsonProperty("locale_language")
    private String localeLanguage;

    @JsonProperty("locale_country")
    private String localeCountry;

    @JsonProperty("timezone_offset")
    private int utcOffset;

    @JsonProperty("timezone_name")
    private String timeZoneName;

    @JsonProperty("cpu_architecture")
    private String cpuArchitecture;

    @JsonProperty("application_name")
    private String applicationName;

    @JsonProperty("application_version")
    private String applicationVersion;

    @JsonProperty("application_package")
    private String applicationPackage;

    public List<DeviceIdentity> getIdentities() {
        return this.identities;
    }

    public void setIdentities(List<DeviceIdentity> identities) {
        this.identities = identities;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
    
    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public void setTimeZoneName(String timeZoneName) {
        this.timeZoneName = timeZoneName;
    }

    public String getCpuArchitecture() {
        return cpuArchitecture;
    }

    public void setCpuArchitecture(String cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationPackage() {
        return applicationPackage;
    }

    public void setApplicationPackage(String applicationPackage) {
        this.applicationPackage = applicationPackage;
    }

    public XboxRuntimeEnvironment() {
        super(Type.XBOX);
    }
}