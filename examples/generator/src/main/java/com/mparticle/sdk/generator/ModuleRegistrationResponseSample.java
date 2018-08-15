package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;
import com.mparticle.sdk.model.registration.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ModuleRegistrationResponseSample {
    public static ModuleRegistrationResponse GenerateMessage() {
        ModuleRegistrationResponse req = new ModuleRegistrationResponse("Your Company Name", "1.0");

        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setDescription("A description of your <a href=''>company</a> and services. Inline HTML markup is permitted.");
        req.setTimestamp(1454693235751L);
        req.setPermissions(new Permissions());
        req.getPermissions().setDeviceIdentities(Arrays.asList(
                new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.RAW, true),
                new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.RAW, true)
        ));

        req.getPermissions().setUserIdentities(Arrays.asList(
                new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.SHA256, false)
        ));

        req.getPermissions().setAllowAccessLocation(false);
        req.getPermissions().setAllowAccessIpAddress(false);
        req.getPermissions().setAllowAccessDeviceApplicationStamp(false);
        req.getPermissions().setAllowConsentState(true);

        EventProcessingRegistration eventReg = new EventProcessingRegistration();
        eventReg.setMaxDataAgeHours(24);
        eventReg.setSupportedRuntimeEnvironments(Arrays.asList(RuntimeEnvironment.Type.IOS, RuntimeEnvironment.Type.ANDROID));
        eventReg.setSupportedEventTypes(Arrays.asList(Event.Type.CUSTOM_EVENT, Event.Type.SESSION_START, Event.Type.SESSION_END));
        eventReg.setSupportedSystemNotifications(Arrays.asList(SystemNotification.Type.GDPR_CONSENT_STATE));

        eventReg.setAccountSettings(new ArrayList<Setting>());
        eventReg.getAccountSettings().add(getApiKeySetting());
        eventReg.getAccountSettings().add(getCustomerIdSetting());

        req.setEventProcessingRegistration(eventReg);

        // Set up Audience Registration.
        AudienceProcessingRegistration audreg = new AudienceProcessingRegistration();
        audreg.setAccountSettings(new ArrayList<Setting>());
        audreg.getAccountSettings().add(getApiKeySetting());
        audreg.getAccountSettings().add(getCustomerIdSetting());

        audreg.setAudienceConnectionSettings(new ArrayList<Setting>());
        audreg.getAudienceConnectionSettings().add(getAudienceSetting());

        req.setAudienceProcessingRegistration(audreg);

        return req;
    }

    private static Setting getApiKeySetting()
    {
        TextSetting setting1 = new TextSetting("text", "key");
        setting1.setName("API Key");
        setting1.setDescription("Secret key to use the API, provided by your account manager");
        setting1.setIsRequired(true);
        setting1.setIsConfidential(false);
        setting1.setIsVisible(true);
        return setting1;
    }

    private static Setting getCustomerIdSetting()
    {
        TextSetting setting2 = new TextSetting("text", "customer_id");
        setting2.setName("Customer ID");
        setting2.setDescription("Internal customer ID, provided by your account manager");
        setting2.setIsRequired(true);
        setting2.setIsConfidential(false);
        setting2.setIsVisible(true);
        return setting2;
    }

    private static Setting getAudienceSetting()
    {
        IntegerSetting set = new IntegerSetting("integer", "val_count");
        set.setDefaultValue(120);
        set.setMaxValue(2000);
        set.setMinValue(10);
        set.setIsRequired(false);
        set.setIsVisible(true);
        return set;
    }
}
