package com.mparticle.sdk.samples;

import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.registration.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SampleExtension extends MessageProcessor {

    // This method is called by mParticle servers during publishing process
    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {

        // Describe your extension
        ModuleRegistrationResponse response = new ModuleRegistrationResponse("SampleExtension", "1.0");

        // Request access to specific user and device id's
        Permissions permissions = new Permissions();

        List<DeviceIdentityPermission> deviceIds = Arrays.asList(
                new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.MD5),
                new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.MD5)
        );

        permissions.setDeviceIdentities(deviceIds);

        List<UserIdentityPermission> userIds = Arrays.asList(
                new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.RAW)
        );

        permissions.setUserIdentities(userIds);

        // Request access to GPS data
        permissions.setAllowAccessLocation(true);

        response.setPermissions(permissions);

        // Register a mobile event stream listener
        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration();

        eventProcessingRegistration.setDescription("Sample Event Processor");

        // Add account settings that should be provided by the subscribers
        List<Setting> accountSettings = new ArrayList<>();

        TextSetting apiKey = new TextSetting("apiKey", "API Key");
        apiKey.setIsRequired(true);
        accountSettings.add(apiKey);

        eventProcessingRegistration.setAccountSettings(accountSettings);

        // Specify supported event types
        List<Event.Type> supportedEventTypes = Arrays.asList(Event.Type.CUSTOM_EVENT);
        eventProcessingRegistration.setSupportedEventTypes(supportedEventTypes);

        response.setEventProcessingRegistration(eventProcessingRegistration);

        return response;
    }

    @Override
    public void processCustomEvent(CustomEvent event) {

        // Read account settings
        Account account = event.getContext().getAccount();
        String apiKey = account.getStringSetting("apiKey", true, null);

        // Access mobile device information
        RuntimeEnvironment environment = event.getContext().getRuntimeEnvironment();

        if (environment.getType() == RuntimeEnvironment.Type.IOS) {
            IosRuntimeEnvironment ios = (IosRuntimeEnvironment)environment;
            String cpuArchitecture = ios.getCpuArchitecture();
        }

        // Access device GPS data
        Location location = event.getLocation();

        if (location != null) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
        }

        // Iterate over custom event attributes
        Map<String, String> userAttributes = event.getAttributes();

        if (userAttributes != null) {
            for (Map.Entry<String, String> entry : userAttributes.entrySet()) {
                String attributeKey = entry.getKey();
                String attributeValue = entry.getValue();
            }
        }
    }
}