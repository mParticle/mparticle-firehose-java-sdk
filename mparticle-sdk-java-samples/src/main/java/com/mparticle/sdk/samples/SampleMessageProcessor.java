package com.mparticle.sdk.samples;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.eventprocessing.Device;
import com.mparticle.sdk.model.eventprocessing.RequestContext;
import com.mparticle.sdk.model.registration.RegistrationRequest;
import com.mparticle.sdk.model.registration.RegistrationResponse;
import com.mparticle.sdk.model.registration.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class SampleMessageProcessor extends MessageProcessor {

    @Override
    public void processRegistrationRequest(RegistrationRequest request, RegistrationResponse response) {

        response.name="GoogleAnalytics";
        response.description="Google Analytics Event Forwarder";

        response.settings = new ArrayList<>();
        response.settings.add(new Setting("apiKey", Setting.DataType.STRING, "API Key"));
        response.settings.add(new Setting("timezoneUtcOffset", Setting.DataType.INT, "UTC Offset"));
        response.settings.add(new Setting("enableEnhancedCommerce", Setting.DataType.BOOL, "Enable Enhanced Commerce"));

        response.supportedEvents = Arrays.asList(Event.Type.APP_EVENT);

        response.requiredIdentities = Arrays.asList(UserIdentity.Type.ANDROID_DEVICE_ID, UserIdentity.Type.IOS_IDFV);

        response.maxDataAgeHours = 24;
    }

    @Override
    public void processAppEvent(AppEvent event, RequestContext context) {
        String apiKey = context.subscription.getStringSetting("apiKey", true, null);
        Device.DeviceType deviceType = context.device.deviceType;
        //event.setHandled(true);
    }

}
