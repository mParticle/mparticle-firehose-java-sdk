package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.consent.*;
import com.mparticle.sdk.model.eventprocessing.notification.*;

import java.math.BigDecimal;
import java.util.*;

public class EventProcessingRequestSample {

    private static final long timestamp = System.currentTimeMillis();
    private static final String mpid = "12345";

    public static Map.Entry<String, EventProcessingRequest> GenerateMessage(RuntimeEnvironment.Type runtimeType) {
        // Basic request info
        EventProcessingRequest req = new EventProcessingRequest();
        req.setTimestamp(System.currentTimeMillis());
        req.setDeviceApplicationStamp(UUID.randomUUID().toString());
        req.setMpId(mpid);
        req.setSourceChannel(Consts.ChannelSourceType.NATIVE);
        req.setSourceId(UUID.randomUUID().toString());

        // Settings
        req.setAccount(Sample.generateAccount());
        req.getAccount().getAccountSettings().put("apiKey", "sample API Key");

        // Consent
        String consentPurpose = "consentPurpose";
        ConsentState cs = new ConsentState();

        Map<String, GDPRConsent> gdpr = new HashMap<>();
        GDPRConsent newGdprConsent = getGdprConsent(true);
        gdpr.put(consentPurpose, newGdprConsent);
        cs.setGDPR(gdpr);

        CCPAConsent newCcpaConsent = getCcpaConsent(true);
        cs.setCCPA(newCcpaConsent); // Purpose is optional

        req.setConsentState(cs);
        req.setSystemNotifications(getSystemNotifications(consentPurpose, newGdprConsent, newCcpaConsent));

        // UAs
        Map<String, String> uas = new HashMap<String, String>();
        uas.put("$Country", "USA");
        uas.put("$State", "FL");
        uas.put("$FirstName", "Brian");
        uas.put("$LastName", "O'Brian");
        uas.put("$Gender", "M");
        uas.put("$City", "Boca Raton");
        uas.put("$Zip", "33431");
        req.setUserAttributes(uas);

        // Identities
        req.setPartnerIdentities(Collections.singletonList(
                new PartnerIdentity("a_partner_id", Identity.Encoding.RAW, "partnerId")
        ));
        req.setUserIdentities(Arrays.asList(
                Sample.generateUserIdentity(UserIdentity.Type.EMAIL),
                Sample.generateUserIdentity(UserIdentity.Type.GOOGLE),
                Sample.generateUserIdentity(UserIdentity.Type.FACEBOOK),
                Sample.generateUserIdentity(UserIdentity.Type.OTHER),
                Sample.generateUserIdentity(UserIdentity.Type.CUSTOMER)));

        req.setRuntimeEnvironment(getRuntimeEnv(runtimeType));

        // Events
        req.setEvents(Arrays.asList(
                getCustomEvent(),
                getCommerceEvent()
        ));

        String fileName = String.format("%s_%s", req.getClass().getSimpleName(), runtimeType.toString());
        return new AbstractMap.SimpleImmutableEntry<>(fileName, req);
    }

    private static ProductActionEvent getCommerceEvent() {
        BigDecimal amount = new BigDecimal(10);

        // Basic info
        ProductActionEvent event = new ProductActionEvent();
        event.setCurrencyCode("USD");
        event.setCouponCode("coupon");
        event.setAction(ProductActionEvent.Action.ADD_TO_CART);
        event.setTotalAmount(amount.add(new BigDecimal(1)));
        event.setTaxAmount(new BigDecimal(1));
        event.setShippingAmount(new BigDecimal(0));
        event.setTransactionId("transactionId");
        setCommonEventInfo(event);

        Map<String, String> attributes = new HashMap<>();
        attributes.put("event_attribute", "value");
        event.setAttributes(attributes);

        // Product info
        Product p = new Product();
        p.setBrand("brand");
        p.setCategory("category");
        p.setId("id");
        p.setName("name");
        p.setPrice(amount);
        p.setQuantity(new BigDecimal(1));
        p.setTotalAmount(amount);
        p.setPosition(1);
        p.setVariant("variant");
        event.setProducts(Collections.singletonList(p));

        return event;
    }

    private static CustomEvent getCustomEvent() {
        CustomEvent event = new CustomEvent();
        event.setName("customEvent");
        event.setCustomType(CustomEvent.CustomType.OTHER);
        setCommonEventInfo(event);

        Map<String, String> attributes = new HashMap<>();
        attributes.put("event_attribute", "value");
        event.setAttributes(attributes);

        Location l = new Location();
        l.setLatitude(47.6062);
        l.setLongitude(122.3231);
        event.setLocation(l);

        return event;
    }

    private static void setCommonEventInfo(Event event) {
        event.setSessionId((long)123456789);
        event.setTimestamp(timestamp);
        event.setSourceId(UUID.randomUUID().toString());
    }

    private static List<SystemNotification> getSystemNotifications(
            String consentPurpose,
            GDPRConsent newGdprConsent,
            CCPAConsent newCcpaConsent) {
        // Set-up the 'before' and 'after' for GDPR consent
        GDPRConsentStateNotification gdprNotification = new GDPRConsentStateNotification();
        gdprNotification.setOldConsentState(getGdprConsent(false));
        gdprNotification.setNewConsentState(newGdprConsent);
        gdprNotification.setPurpose(consentPurpose);

        // Set-up the 'before' and 'after' for CCPA consent. Purpose is optional.
        CCPAConsentStateNotification ccpaNotification = new CCPAConsentStateNotification();
        ccpaNotification.setOldConsentState(getCcpaConsent(false));
        ccpaNotification.setNewConsentState(newCcpaConsent);

        return Arrays.asList(gdprNotification, ccpaNotification);
    }

    private static GDPRConsent getGdprConsent(boolean consented) {
        return new GDPRConsent()
            .setConsented(consented)
            .setDocument("document")
            .setHardwareId("hardware id")
            .setLocation("location")
            .setTimestamp(timestamp);
    }

    private static CCPAConsent getCcpaConsent(boolean consented) {
        return new CCPAConsent()
            .setConsented(consented)
            .setDocument("document")
            .setHardwareId("hardware id")
            .setLocation("location")
            .setTimestamp(timestamp);
    }

    private static RuntimeEnvironment getRuntimeEnv(RuntimeEnvironment.Type type) {
        switch(type) {

            case ANDROID:
                return getAndroidRuntimeEnv();
            case IOS:
                return getIOsRuntimeEnv();

            case UNKNOWN:
            case TVOS:
            case MOBILEWEB:
            case ROKU:
            case XBOX:
            case FIRETV:
            case ALEXA:
            case SMARTTV:
            default:
                return null;
        }
    }

    private static AndroidRuntimeEnvironment getAndroidRuntimeEnv() {
        AndroidRuntimeEnvironment runtime = new AndroidRuntimeEnvironment();

        runtime.setClientIpAddress("192.168.1.15");
        runtime.setBuildId("M4=rc20");
        runtime.setApplicationName("App Name");
        runtime.setApplicationPackage("com.mparticle.TravelApp");
        runtime.setApplicationVersion("1.2.001-dbg");
        runtime.setIsTablet(false);
        runtime.setManufacturer("Samsung");
        runtime.setBrand("Samsung");
        runtime.setName("My Galaxy S3");
        runtime.setProduct("Galaxy S3");
        runtime.setModel("Galaxy S3");
        runtime.setOsVersion("4.1.1");

        runtime.setNetworkCarrier("Verizon");
        runtime.setTimeZoneName("UTC-5");
        runtime.setUtcOffset(-5);
        runtime.setMobileNetworkCode("MobileNetworkCode");
        runtime.setMobileCountryCode("MobileCountryCode");
        runtime.setLocaleCountry(Locale.US.toString());
        runtime.setLocaleLanguage(Locale.US.getLanguage());
        runtime.setCountry("US");
        runtime.setNetworkCountry("US");
        runtime.setScreenHeight(736);
        runtime.setScreenWidth(1280);
        runtime.setScreenDpi(160);
        runtime.setSdkVersion("6.12.1");
        runtime.setUserAgent("user-agent");
        runtime.setInstallReferrer("utm_source%3Dgoogle%26utm_medium%3Dcpc%26utm_term%3Drunning%252Bshoes%26utm_content%3Dlogolink%26utm_campaign%3Dspring_sale");

        // Device Identities
        runtime.setIdentities(Arrays.asList(
                Sample.generateDeviceIdentity(DeviceIdentity.Type.ANDROID_ID),
                Sample.generateDeviceIdentity(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID),
                Sample.generateDeviceIdentity(DeviceIdentity.Type.GOOGLE_CLOUD_MESSAGING_TOKEN)
        ));

        return runtime;
    }

    private static IosRuntimeEnvironment getIOsRuntimeEnv() {
        IosRuntimeEnvironment runtime = new IosRuntimeEnvironment();

        runtime.setClientIpAddress("127.0.0.1");
        runtime.setBuildId("BuildId");
        runtime.setApplicationName("App Name");
        runtime.setApplicationPackage("Package");
        runtime.setApplicationVersion("v2");
        runtime.setCpuArchitecture("x86");
        runtime.setIsSandboxed(true);
        runtime.setIsTablet(true);
        runtime.setManufacturer("Apple");
        runtime.setBrand("Apple");
        runtime.setName("My iPad");
        runtime.setModel("iPad Pro");
        runtime.setProduct("iPad Pro");
        runtime.setOsVersion("10.0");

        runtime.setNetworkCarrier("at&t");
        runtime.setTimeZoneName("UTC-8");
        runtime.setUtcOffset(-8);
        runtime.setMobileCountryCode("MobileCountryCode");
        runtime.setMobileNetworkCode("MobileNetworkCode");
        runtime.setNetworkCountry("US");
        runtime.setCountry("US");
        runtime.setLocaleCountry(Locale.US.toString());
        runtime.setLocaleLanguage(Locale.US.getLanguage());
        runtime.setRadioAccessTechnology("RadioAccessTechnology");
        runtime.setScreenHeight(2436);
        runtime.setScreenWidth(1125);
        runtime.setSdkVersion("6.12.1");
        runtime.setUserAgent("user-agent");

        // Apple search ads attribution
        Map<String, String> map = new HashMap<String, String>();
        map.put("iad-attribution", "true");
        map.put("iad-clickdate", "date");
        map.put("iad-conversion-date", "date");
        map.put("iad-org-name", "org");
        map.put("iad-campaign-id", "id");
        map.put("iad-campaign-name", "name");
        map.put("iad-adgroup-id", "id");
        map.put("iad-adgroup-name", "name");
        map.put("iad-keyword", "keyword");

        Map<String, Map<String, String>> appleSearchAdsAttribution = new HashMap<String, Map<String, String>>();
        appleSearchAdsAttribution.put("Version3.1", map);
        runtime.setAppleSearchAdsAttribution(appleSearchAdsAttribution);

        // Device Identities
        runtime.setIdentities(Arrays.asList(
                Sample.generateDeviceIdentity(DeviceIdentity.Type.IOS_ADVERTISING_ID),
                Sample.generateDeviceIdentity(DeviceIdentity.Type.IOS_VENDOR_ID)
        ));

        return runtime;
    }
}
