package com.mparticle.sdk.samples;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.eventprocessing.EventProcessingContext;
import com.mparticle.sdk.model.registration.*;

import java.util.ArrayList;
import java.util.List;

public class SampleMessageProcessor extends MessageProcessor {

    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {

        ModuleRegistrationResponse response = new ModuleRegistrationResponse("GoogleAnalytics", "1.0");

        response.setDescription("Google Analytics Event Forwarder");

        List<Setting> settings = new ArrayList<>();

        TextSetting apiKey = new TextSetting("apiKey", "API Key");
        apiKey.setIsRequired(true);
        settings.add(apiKey);

        IntegerSetting timezoneUtcOffset = new IntegerSetting("timezoneUtcOffset", "GMT Offset");
        timezoneUtcOffset.setIsRequired(true);
        timezoneUtcOffset.setDefaultValue(-5);
        timezoneUtcOffset.setMinValue(-12);
        timezoneUtcOffset.setMaxValue(14);
        settings.add(timezoneUtcOffset);

        BooleanSetting enableEnhancedCommerce = new BooleanSetting("enableEnhancedCommerce", "Enable Enhanced Commerce");
        enableEnhancedCommerce.setCheckedValue("yes");
        enableEnhancedCommerce.setUncheckedValue(null);
        settings.add(enableEnhancedCommerce);

        FloatSetting samplingPct = new FloatSetting("samplingPct", "Sampling %");
        samplingPct.setDefaultValue(100.0);
        samplingPct.setMinValue(0.0);
        samplingPct.setMaxValue(100.0);
        settings.add(samplingPct);

        response.setSettings(settings);

        List<Permission> permissions = new ArrayList<>();
        permissions.add(new LocationPermission());
        permissions.add(new UserIdentityPermission(UserIdentity.Type.IOS_IDFA, UserIdentity.Format.RAW));
        permissions.add(new UserIdentityPermission(UserIdentity.Type.ANDROID_DEVICE_ID, UserIdentity.Format.SHA256));

        response.setPermissions(permissions);

        response.setMaxDataAgeHours(24);

        return response;
    }

    @Override
    public EventProcessingResult processAppEvent(AppEvent event, EventProcessingContext context) {

//        String apiKey = context.subscription.getStringSetting("apiKey", true, null);
//        Device.DeviceType deviceType = context.device.deviceType;

        return new EventProcessingResult(event.id, EventProcessingResult.Action.PROCESSED);
    }

    @Override
    public EventProcessingResult processSessionStartEvent(SessionStartEvent event, EventProcessingContext context) {
        return new EventProcessingResult(event.id, EventProcessingResult.Action.DISCARDED, 911, "IDFA is missing");
    }
}
