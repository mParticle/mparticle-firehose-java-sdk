package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;
import com.mparticle.sdk.model.registration.*;

import java.util.*;

public class ModuleRegistrationResponseSample {
    public static Map.Entry<String, ModuleRegistrationResponse> GenerateMessage() {
        ModuleRegistrationResponse response = new ModuleRegistrationResponse("Your Company Name", "1.0");

        // Set-up Permissions
        Permissions permissions = new Permissions();
        permissions
            .setDeviceIdentities(Arrays.asList(
                new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.RAW, true),
                new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.RAW, true)
            ))
            .setAllowAccessLocation(false)
            .setAllowAccessIpAddress(false)
            .setAllowAccessMpid(true)
            .setAllowAccessDeviceApplicationStamp(false)
            .setAllowConsentState(true)
            .setUserIdentities(Collections.singletonList(
                    new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.SHA256, false)
            ))
            .setPartnerIdentities(Collections.singletonList(
                    new PartnerIdentityPermission("partner_id", Identity.Encoding.RAW, false)
            ));

        // Set-up Event Registration
        EventProcessingRegistration eventRegistration = new EventProcessingRegistration();
        eventRegistration
            .setMaxDataAgeHours(24)
            .setSupportedRuntimeEnvironments(Arrays.asList(RuntimeEnvironment.Type.IOS, RuntimeEnvironment.Type.ANDROID))
            .setSupportedEventTypes(Arrays.asList(Event.Type.CUSTOM_EVENT, Event.Type.SESSION_START, Event.Type.SESSION_END))
            .setSupportedSystemNotifications(Collections.singletonList(SystemNotification.Type.GDPR_CONSENT_STATE))
            .setAccountSettings(Arrays.asList(
                    getApiKeySetting(),
                    getCustomerIdSetting()
            ));

        // Set up Audience Registration.
        AudienceProcessingRegistration audienceRegistration = new AudienceProcessingRegistration();
        audienceRegistration
                .setAudienceConnectionSettings(Collections.singletonList(getAudienceSetting()))
                .setAccountSettings(Arrays.asList(
                getApiKeySetting(),
                getCustomerIdSetting()
        ));

        response
            .setDescription("A description of your <a href=''>company</a> and services. Inline HTML markup is permitted.")
            .setEventProcessingRegistration(eventRegistration)
            .setAudienceProcessingRegistration(audienceRegistration)
            .setPermissions(permissions);

        return new AbstractMap.SimpleImmutableEntry<>(response.getClass().getSimpleName(), response);
    }

    private static Setting getApiKeySetting()
    {
        TextSetting setting1 = new TextSetting("apiKey", "key");
        setting1.setName("API Key");
        setting1.setDescription("Secret key to use the API, provided by your account manager");
        setting1.setIsRequired(true);
        setting1.setIsConfidential(true);
        setting1.setIsVisible(true);
        return setting1;
    }

    private static Setting getCustomerIdSetting()
    {
        TextSetting setting2 = new TextSetting("customerId", "customer_id");
        setting2.setName("Customer ID");
        setting2.setDescription("Internal customer ID, provided by your account manager");
        setting2.setIsRequired(true);
        setting2.setIsConfidential(false);
        setting2.setIsVisible(true);
        return setting2;
    }

    private static Setting getAudienceSetting()
    {
        BooleanSetting set = new BooleanSetting("audienceType", "Suppression Audience");
        set.setDefaultValue(false);
        set.setDescription("If enabled, this audience will be used for suppression.");
        set.setIsVisible(true);
        return set;
    }
}
