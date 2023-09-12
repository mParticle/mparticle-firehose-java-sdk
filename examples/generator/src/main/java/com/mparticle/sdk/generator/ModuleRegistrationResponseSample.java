package com.mparticle.sdk.generator;

import com.mparticle.sdk.model.dsrprocessing.DsrProcessingRequest;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;
import com.mparticle.sdk.model.registration.*;
import com.mparticle.sdk.model.registration.authentication.OAuth2Authentication;
import com.mparticle.sdk.model.registration.authentication.ScopeDetail;

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
        OAuth2Authentication eventOAuthAuthentication = new OAuth2Authentication();

        eventOAuthAuthentication
                .setAuthorizationUrl("TEST_EVENT_AUTHORIZATION_URL")
                .setRefreshUrl("TEST_EVENT_REFRESH_URL")
                .setTokenUrl("TEST_EVENT_TOKEN_URL")
                .setGrantType(OAuth2Authentication.GrantType.AUTHORIZATION_CODE)
                .setDefaultExpiresIn(2000)
                .setClientId("TEST_EVENT_CLIENT_ID")
                .setAccessTokenType(OAuth2Authentication.AccessTokenType.CUSTOM_HEADER)
                .setCustomHeaderName("TEST_EVENT_CUSTOMER_HEADER_NAME")
                .setParamClientIdName("TEST_EVENT_PARAM_CLIENT_ID_NAME")
                .setParamSecretName("TEST_EVENT_PARAM_SECRET_NAME")
                .setScopes(new ScopeDetail[]{new ScopeDetail()
                        .setName("TEST_EVENT_SCOPE_NAME_1")
                        .setDescription("TEST_EVENT_SCOPE_DESCRIPTION_1")
                });
        eventRegistration
            .setMaxDataAgeHours(24)
            .setSupportedRuntimeEnvironments(Arrays.asList(RuntimeEnvironment.Type.IOS, RuntimeEnvironment.Type.ANDROID))
            .setSupportedEventTypes(Arrays.asList(Event.Type.CUSTOM_EVENT, Event.Type.SESSION_START, Event.Type.SESSION_END))
            .setSupportedSystemNotifications(Collections.singletonList(SystemNotification.Type.GDPR_CONSENT_STATE))
            .setAccountSettings(Arrays.asList(
                    getApiKeySetting(),
                    getCustomerIdSetting()
            ))
            .setAuthentication(eventOAuthAuthentication);

        // Set up Audience Registration.
        AudienceProcessingRegistration audienceRegistration = new AudienceProcessingRegistration();
        OAuth2Authentication authentication = new OAuth2Authentication();
        BulkConfiguration bulkConfiguration = new BulkConfiguration();

        authentication
                .setAuthorizationUrl("TEST_AUTHORIZATION_URL")
                .setRefreshUrl("TEST_REFRESH_URL")
                .setTokenUrl("TEST_TOKEN_URL")
                .setGrantType(OAuth2Authentication.GrantType.AUTHORIZATION_CODE)
                .setDefaultExpiresIn(2000)
                .setClientId("TEST_CLIENT_ID")
                .setAccessTokenType(OAuth2Authentication.AccessTokenType.CUSTOM_HEADER)
                .setCustomHeaderName("TEST_CUSTOMER_HEADER_NAME")
                .setParamClientIdName("TEST_PARAM_CLIENT_ID_NAME")
                .setParamSecretName("TEST_PARAM_SECRET_NAME")
                .setScopes(new ScopeDetail[]{new ScopeDetail()
                        .setName("TEST_SCOPE_NAME_1")
                        .setDescription("TEST_SCOPE_DESCRIPTION_1")
                });

        bulkConfiguration
                .setBulkForwardWaitForMessages(4)
                .setBulkForwardWaitInMinutes(30);

        audienceRegistration
                .setAudienceConnectionSettings(Collections.singletonList(getAudienceSetting()))
                .setAuthentication(authentication)
                .setAccountSettings(Arrays.asList(
                    getApiKeySetting(),
                    getCustomerIdSetting()
                ))
                .setBulkConfiguration(bulkConfiguration);

        // Set up DSR Registration.
        DsrProcessingRegistration dsrRegistration = new DsrProcessingRegistration();
        dsrRegistration
                .setAccountSettings(Arrays.asList(getDsrWebhookSetting()))
                .setDomain("TEST_DOMAIN")
                .setSupportedDsrTypes(Arrays.asList(DsrProcessingRequest.Type.ERASURE));

        response
            .setDescription("A description of your <a href=''>company</a> and services. Inline HTML markup is permitted.")
            .setEventProcessingRegistration(eventRegistration)
            .setAudienceProcessingRegistration(audienceRegistration)
            .setDsrProcessingRegistration(dsrRegistration)
            .setPermissions(permissions);

        return new AbstractMap.SimpleImmutableEntry<>(response.getClass().getSimpleName(), response);
    }

    private static Setting getApiKeySetting()
    {
        TextSetting setting = new TextSetting("apiKey", "key");
        setting.setName("API Key");
        setting.setDescription("Secret key to use the API, provided by your account manager");
        setting.setIsRequired(true);
        setting.setIsConfidential(true);
        setting.setIsVisible(true);
        return setting;
    }

    private static Setting getCustomerIdSetting()
    {
        TextSetting setting = new TextSetting("customerId", "customer_id");
        setting.setName("Customer ID");
        setting.setDescription("Internal customer ID, provided by your account manager");
        setting.setIsRequired(true);
        setting.setIsConfidential(false);
        setting.setIsVisible(true);
        return setting;
    }

    private static Setting getAudienceSetting()
    {
        BooleanSetting set = new BooleanSetting("audienceType", "Suppression Audience");
        set.setDefaultValue(false);
        set.setDescription("If enabled, this audience will be used for suppression.");
        set.setIsVisible(true);
        return set;
    }

    private static Setting getDsrWebhookSetting()
    {
        TextSetting setting = new TextSetting("webhookUrl", "webhook_url");
        setting.setName("Webhook URL");
        setting.setDescription("The url of your integration.");
        setting.setIsRequired(true);
        setting.setIsConfidential(false);
        setting.setIsVisible(true);
        return setting;
    }
}
