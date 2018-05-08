package com.mparticle.sdk.samples;

import com.mparticle.sdk.model.audienceprocessing.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;
import com.mparticle.sdk.model.registration.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SampleExtension extends MessageProcessor {

    // This method is called by mParticle servers during publishing process
    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {

        // Describe your extension
        ModuleRegistrationResponse response = new ModuleRegistrationResponse("SampleExtension", "1.0");

        response.setDescription("Sample Message Processor");

        // Request access to specific user and device id's
        Permissions permissions = new Permissions();

        List<DeviceIdentityPermission> deviceIds = Arrays.asList(
                // require at least one of advertising ids
                new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.MD5, true),
                new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.MD5, true),
                // optional ids
                new DeviceIdentityPermission(DeviceIdentity.Type.ANDROID_ID, Identity.Encoding.MD5),
                new DeviceIdentityPermission(DeviceIdentity.Type.IOS_VENDOR_ID, Identity.Encoding.MD5)
        );

        permissions.setDeviceIdentities(deviceIds);
        permissions.setAllowConsentState(true);

        List<UserIdentityPermission> userIds = Arrays.asList(
                // users must have email or customer id, otherwise they should be filtered
                new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.RAW, true),
                new UserIdentityPermission(UserIdentity.Type.CUSTOMER, Identity.Encoding.RAW, true),
                // facebook id is optional
                new UserIdentityPermission(UserIdentity.Type.FACEBOOK, Identity.Encoding.SHA1)
        );

        permissions.setUserIdentities(userIds);

        // Request access to GPS data
        permissions.setAllowAccessLocation(true);

        response.setPermissions(permissions);

        // Register a mobile event stream listener
        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration();

        // Declare supported environments
        List<RuntimeEnvironment.Type> runtimeEnvironments = Arrays.asList(
                RuntimeEnvironment.Type.ANDROID,
                RuntimeEnvironment.Type.IOS);

        eventProcessingRegistration.setSupportedRuntimeEnvironments(runtimeEnvironments);

        // Add account settings that should be provided by the subscribers
        List<Setting> accountSettings = new ArrayList<>();

        TextSetting apiKey = new TextSetting("apiKey", "API Key");
        apiKey.setIsRequired(true);
        accountSettings.add(apiKey);

        eventProcessingRegistration.setAccountSettings(accountSettings);

        // Specify supported event types
        List<Event.Type> supportedEventTypes = Arrays.asList(Event.Type.CUSTOM_EVENT);
        eventProcessingRegistration.setSupportedEventTypes(supportedEventTypes);

        List supportedNotificationTypes = Arrays.asList(SystemNotification.Type.GDPR_CONSENT_STATE);
        eventProcessingRegistration.setSupportedSystemNotifications(supportedNotificationTypes);

        response.setEventProcessingRegistration(eventProcessingRegistration);

        // Register audience stream listener
        AudienceProcessingRegistration audienceProcessingRegistration = new AudienceProcessingRegistration();
        audienceProcessingRegistration.setAccountSettings(accountSettings);

        // declare internal setting returned by processAudienceSubscriptionRequest
        List<Setting> audienceSubscriptionSettings = Arrays.asList(
                new TextSetting("audienceId", "Audience ID").setIsVisible(false)
        );
        audienceProcessingRegistration.setAudienceSubscriptionSettings(audienceSubscriptionSettings);
        response.setAudienceProcessingRegistration(audienceProcessingRegistration);

        return response;
    }

    @Override
    public void processCustomEvent(CustomEvent event) {

        // Read account settings
        Account account = event.getRequest().getAccount();
        String apiKey = account.getStringSetting("apiKey", true, null);

        // Access mobile device information
        RuntimeEnvironment environment = event.getRequest().getRuntimeEnvironment();

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

    @Override
    public AudienceSubscriptionResponse processAudienceSubscriptionRequest(AudienceSubscriptionRequest request) throws IOException {

        // Read account settings
        Account account = request.getAccount();
        String apiKey = account.getStringSetting("apiKey", true, null);

        AudienceSubscriptionResponse response = new AudienceSubscriptionResponse();

        if (request.getAction() == AudienceSubscriptionRequest.SubscriptionAction.ADD)
        {
            // process new audience subscription
            // ...

            // store internal audience id
            Map<String, String> settings = new HashMap<>();
            settings.put("audienceId", "xyz");

            response.setAudienceSubscriptionSettings(settings);
        }


        return response;
    }

    @Override
    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {

        // Read account settings
        Account account = request.getAccount();
        String apiKey = account.getStringSetting("apiKey", true, null);

        for (UserProfile profile : request.getUserProfiles()) {

            // extract emails from the user profile

            List<String> emails = profile.getUserIdentities().stream()
                    .filter(id -> id.getType() == UserIdentity.Type.EMAIL && id.getEncoding() == Identity.Encoding.RAW)
                    .map(Identity::getValue)
                    .collect(Collectors.toList());


            // iterate through added and removed audiences

            List<Audience> addedAudiences = profile.getAddedAudiences();

            if(addedAudiences != null) {
                for(Audience audience : addedAudiences) {

                    int audienceId = audience.getAudienceId();
                    String audienceName = audience.getAudienceName();

                    // read custom audience subscription settings provided by customers or returned
                    // by processAudienceSubscriptionRequest()
                    Map<String, String> subscriptionSettings = audience.getAudienceSubscriptionSettings();
                    String internalAudienceId = subscriptionSettings.get("audienceId");
                }
            }

            List<Audience> removedAudiences = profile.getRemovedAudiences();

            if(removedAudiences != null) {
                // ...
            }

        }

        return new AudienceMembershipChangeResponse();
    }

}