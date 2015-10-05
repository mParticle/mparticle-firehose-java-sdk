package com.mparticle.sdk.samples;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.registration.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleMessageProcessor extends MessageProcessor {

    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {

        ModuleRegistrationResponse response = new ModuleRegistrationResponse("GoogleAnalytics", "1.0");

        Permissions permissions = new Permissions();

        List<DeviceIdentityPermission> deviceIds = Arrays.asList(
                new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.MD5),
                new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.MD5)
        );

        permissions.setDeviceIdentities(deviceIds);

        List<UserIdentityPermission> userIds = Arrays.asList(
                new UserIdentityPermission(UserIdentity.Type.FACEBOOK, Identity.Encoding.RAW)
        );

        permissions.setUserIdentities(userIds);

        permissions.setAllowAccessLocation(true);

        response.setPermissions(permissions);

        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration();

        eventProcessingRegistration.setDescription("Google Analytics Event Forwarder");

        List<Setting> accountSettings = new ArrayList<>();

        TextSetting apiKey = new TextSetting("apiKey", "API Key");
        apiKey.setIsRequired(true);
        accountSettings.add(apiKey);

        IntegerSetting timezoneUtcOffset = new IntegerSetting("timezoneUtcOffset", "GMT Offset");
        timezoneUtcOffset.setIsRequired(true);
        timezoneUtcOffset.setDefaultValue(-5);
        timezoneUtcOffset.setMinValue(-12);
        timezoneUtcOffset.setMaxValue(14);
        accountSettings.add(timezoneUtcOffset);

        BooleanSetting enableEnhancedCommerce = new BooleanSetting("enableEnhancedCommerce", "Enable Enhanced Commerce");
        accountSettings.add(enableEnhancedCommerce);

        FloatSetting samplingPct = new FloatSetting("samplingPct", "Sampling %");
        samplingPct.setDefaultValue(100.0);
        samplingPct.setMinValue(0.0);
        samplingPct.setMaxValue(100.0);
        accountSettings.add(samplingPct);

        eventProcessingRegistration.setAccountSettings(accountSettings);

        List<Event.Type> supportedEventTypes = Arrays.asList(Event.Type.CUSTOM_EVENT, Event.Type.SESSION_START);

        eventProcessingRegistration.setSupportedEventTypes(supportedEventTypes);

        eventProcessingRegistration.setMaxDataAgeHours(24);

        response.setEventProcessingRegistration(eventProcessingRegistration);

        return response;
    }

    @Override
    public void processCustomEvent(CustomEvent event) {

        Account account = event.getContext().getAccount();

        if (account != null) {
            String apiKey = account.getStringSetting("apiKey", true, null);
        }

        RuntimeEnvironment env = event.getContext().getRuntimeEnvironment();

        if (env != null) {

            if (env.isDebug()) {
                return;
            }

            if (env.getType() == RuntimeEnvironment.Type.ANDROID) {
                AndroidRuntimeEnvironment androidEnv = (AndroidRuntimeEnvironment)env;
                int androidSdkLevel = androidEnv.getAndroidSdkLevel();
            }
        }
    }
}
