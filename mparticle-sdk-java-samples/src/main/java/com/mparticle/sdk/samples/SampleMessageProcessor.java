package com.mparticle.sdk.samples;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.eventprocessing.EventProcessingContext;
import com.mparticle.sdk.model.registration.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleMessageProcessor extends MessageProcessor {

    @Override
    public RegistrationResponse processRegistrationRequest(RegistrationRequest request) {

        RegistrationResponse response = new RegistrationResponse("GoogleAnalytics");

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
        response.setMaxDataAgeHours(24);

        //response.requiredIdentities = Arrays.asList(UserIdentity.Type.ANDROID_DEVICE_ID, UserIdentity.Type.IOS_IDFV);

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
