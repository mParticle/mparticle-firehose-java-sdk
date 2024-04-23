package com.mparticle.sdk;

import com.mparticle.sdk.model.Consts;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.Account;
import com.mparticle.sdk.model.registration.EventProcessingRegistration;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;
import com.mparticle.sdk.model.registration.authentication.OAuth2Authentication;
import com.mparticle.sdk.model.registration.authentication.ScopeDetail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.AbstractMap.SimpleImmutableEntry;

public class EventImportTest extends ImportTest {

    private MessageSerializer serializer = new MessageSerializer();

    /**
     * Helper to generate testing data for our device id 'theory'
     * @return
     */
    private static Stream<SimpleImmutableEntry<RuntimeEnvironment.Type, DeviceIdentity.Type>> deviceIdentityProvider() {
        return Stream.of(
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.FIRETV, DeviceIdentity.Type.FIRE_ADVERTISING_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.XBOX, DeviceIdentity.Type.MICROSOFT_ADVERTISING_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.XBOX, DeviceIdentity.Type.MICROSOFT_PUBLISHER_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.ROKU, DeviceIdentity.Type.ROKU_ADVERTISING_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.ROKU, DeviceIdentity.Type.ROKU_PUBLISHER_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.IOS, DeviceIdentity.Type.IOS_ADVERTISING_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.IOS, DeviceIdentity.Type.IOS_VENDOR_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.IOS, DeviceIdentity.Type.APPLE_PUSH_NOTIFICATION_TOKEN),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.TVOS, DeviceIdentity.Type.IOS_ADVERTISING_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.TVOS, DeviceIdentity.Type.IOS_VENDOR_ID),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.TVOS, DeviceIdentity.Type.APPLE_PUSH_NOTIFICATION_TOKEN),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.ANDROID, DeviceIdentity.Type.ANDROID_ID)
        );
    }

    /**
     * Helper to generate testing data for runtime environment that support authorization status
     * @return
     */
    private static Stream<SimpleImmutableEntry<RuntimeEnvironment.Type, AttAuthorizationStatus>> runtimeEnvironmentsWithAuthorizationStatus() {
        return Stream.of(
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.IOS, AttAuthorizationStatus.AUTHORIZED),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.IOS, AttAuthorizationStatus.DENIED),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.IOS, AttAuthorizationStatus.NOT_DETERMINED),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.IOS, AttAuthorizationStatus.RESTRICTED),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.TVOS, AttAuthorizationStatus.AUTHORIZED),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.TVOS, AttAuthorizationStatus.DENIED),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.TVOS, AttAuthorizationStatus.NOT_DETERMINED),
                new SimpleImmutableEntry<>(RuntimeEnvironment.Type.TVOS, AttAuthorizationStatus.RESTRICTED)
        );
    }

    /**
     * Check each user identity type
     * @param userIdType
     */
    @ParameterizedTest
    @EnumSource(UserIdentity.Type.class)
    public void TestUserIdentity(UserIdentity.Type userIdType) {
        RuntimeEnvironment.Type runtimeEnvType = RuntimeEnvironment.Type.IOS;
        DeviceIdentity.Type deviceIdType = DeviceIdentity.Type.IOS_ADVERTISING_ID;

        try {
            // Construct and serialize request
            EventProcessingRequest req = GenerateEventProcessingRequest(runtimeEnvType, userIdType, deviceIdType);
            String json = serializer.serialize(req);

            // Deserialize request
            req = serializer.deserialize(json, EventProcessingRequest.class);
            assertNotNull(req);

            // Check identity info
            assertTrue(verifyUserIdentity(req.getUserIdentities(), userIdType, "userId"));
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Check each device identity type
     * @param deviceIdTuple
     */
    @ParameterizedTest
    @MethodSource("deviceIdentityProvider")
    public void TestDeviceIdentity(SimpleImmutableEntry<RuntimeEnvironment.Type, DeviceIdentity.Type> deviceIdTuple) {
        RuntimeEnvironment.Type runtimeEnvType = deviceIdTuple.getKey();
        DeviceIdentity.Type deviceIdType = deviceIdTuple.getValue();

        try {
            // Construct and serialize request
            EventProcessingRequest req = GenerateEventProcessingRequest(runtimeEnvType, UserIdentity.Type.EMAIL, deviceIdType);
            String json = serializer.serialize(req);

            // Deserialize request
            req = serializer.deserialize(json, EventProcessingRequest.class);
            assertNotNull(req);

            // Get runtime env and identity info
            assertEquals(runtimeEnvType, req.getRuntimeEnvironment().getType());
            assertTrue(verifyDeviceIdentity(req.getRuntimeEnvironment().getIdentities(), deviceIdType, "deviceId"));
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Check partner identities
     */
    @Test
    public void TestPartnerIdentity() {
        RuntimeEnvironment.Type runtimeEnvType = RuntimeEnvironment.Type.IOS;
        DeviceIdentity.Type deviceIdType = DeviceIdentity.Type.IOS_ADVERTISING_ID;
        UserIdentity.Type userIdType = UserIdentity.Type.EMAIL;

        try {
            // Construct and serialize request
            EventProcessingRequest req = GenerateEventProcessingRequest(runtimeEnvType, userIdType, deviceIdType);
            String json = serializer.serialize(req);

            // Deserialize request
            req = serializer.deserialize(json, EventProcessingRequest.class);
            assertNotNull(req);

            // Check partner identity
            List<PartnerIdentity> partnerIdentities = req.getPartnerIdentities();
            assertEquals(1, partnerIdentities.size());
            PartnerIdentity partnerIdentity = partnerIdentities.iterator().next();
            assertEquals("partner_id", partnerIdentity.getType());
            assertEquals("partnerId", partnerIdentity.getValue());
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Validates the serialization/deserialization of authorization status for
     * iOS and TVOS runtime environments
     * @param runtimeAuthorizationTuple
     */
    @ParameterizedTest
    @MethodSource("runtimeEnvironmentsWithAuthorizationStatus")
    public void TestAttAuthorizationStatusSerializationDeserialization(SimpleImmutableEntry<RuntimeEnvironment.Type, AttAuthorizationStatus> runtimeAuthorizationTuple) throws IOException {
        RuntimeEnvironment.Type runtimeEnvType = runtimeAuthorizationTuple.getKey();
        AttAuthorizationStatus authorizationStatus = runtimeAuthorizationTuple.getValue();

        DeviceIdentity.Type deviceIdentityType = DeviceIdentity.Type.IOS_ADVERTISING_ID;

        // Construct and serialize request
        EventProcessingRequest req = GenerateEventProcessingRequest(runtimeEnvType, UserIdentity.Type.EMAIL, deviceIdentityType, authorizationStatus);
        String json = serializer.serialize(req);

        // Deserialize request
        req = serializer.deserialize(json, EventProcessingRequest.class);
        assertNotNull(req);

        // Get runtime env and identity info
        assertEquals(runtimeEnvType, req.getRuntimeEnvironment().getType());

        AttAuthorizationStatus actualAuthorizationStatus = null;
        RuntimeEnvironment reqRuntimeEnvironment = req.getRuntimeEnvironment();
        if (reqRuntimeEnvironment.getType() == RuntimeEnvironment.Type.IOS) {
            IosRuntimeEnvironment iosRuntime = (IosRuntimeEnvironment) reqRuntimeEnvironment;
            actualAuthorizationStatus = iosRuntime.getAttAuthorizationStatus();
        } else if (reqRuntimeEnvironment.getType() == RuntimeEnvironment.Type.TVOS) {
            TVOSRuntimeEnvironment tvosRuntime = (TVOSRuntimeEnvironment) reqRuntimeEnvironment;
            actualAuthorizationStatus = tvosRuntime.getAttAuthorizationStatus();
        }

        assertEquals(authorizationStatus, actualAuthorizationStatus);
    }

    @Test
    public void OAuth2SettingsTest() {
        String testAuthorizationUrl = "TEST_AUTHORIZATION_URL", testRefreshUrl = "TEST_REFRESH_URL", testTokenUrl = "TEST_TOKEN_URL", testClientId = "TEST_CLIENT_ID";
        String testCustomHeaderName ="TEST_CUSTOMER_HEADER_NAME", testParamClientIdName = "TEST_PARAM_CLIENT_ID_NAME", testParamSecretName = "TEST_PARAM_SECRET_NAME";
        String testScopeNamePrefix = "TEST_SCOPE_NAME_", testScopeDescriptionPrefix = "TEST_SCOPE_DESCRIPTION_";
        Integer defaultExpiresIn = 1000;

        OAuth2Authentication authentication = new OAuth2Authentication();
        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration();
        ScopeDetail[] scopes = new ScopeDetail[2];

        scopes[0] = new ScopeDetail()
                .setName(testScopeNamePrefix + 1)
                .setDescription(testScopeDescriptionPrefix + 1);
        scopes[1] = new ScopeDetail()
                .setName(testScopeNamePrefix + 2)
                .setDescription(testScopeDescriptionPrefix + 2);

        authentication
                .setAuthorizationUrl(testAuthorizationUrl)
                .setRefreshUrl(testRefreshUrl)
                .setTokenUrl(testTokenUrl)
                .setGrantType(OAuth2Authentication.GrantType.AUTHORIZATION_CODE)
                .setDefaultExpiresIn(defaultExpiresIn)
                .setClientId(testClientId)
                .setAccessTokenType(OAuth2Authentication.AccessTokenType.CUSTOM_HEADER)
                .setCustomHeaderName(testCustomHeaderName)
                .setParamClientIdName(testParamClientIdName)
                .setParamSecretName(testParamSecretName)
                .setScopes(scopes);

        eventProcessingRegistration.setAuthentication(authentication);

        ModuleRegistrationResponse mmr = new ModuleRegistrationResponse("OAuth Permission Test Name", "Test Version");
        mmr.setEventProcessingRegistration(eventProcessingRegistration);

        try {
            String json = serializer.serialize(mmr);
            mmr = serializer.deserialize(json, ModuleRegistrationResponse.class);

            assertNotNull(mmr);
            authentication = (OAuth2Authentication) mmr.getEventProcessingRegistration().getAuthentication();

            checkOAuthSettings(authentication,
                    testAuthorizationUrl,
                    testRefreshUrl,
                    testTokenUrl,
                    OAuth2Authentication.GrantType.AUTHORIZATION_CODE,
                    defaultExpiresIn,
                    testClientId,
                    OAuth2Authentication.AccessTokenType.CUSTOM_HEADER,
                    testCustomHeaderName,
                    testParamClientIdName,
                    testParamSecretName,
                    testScopeNamePrefix,
                    testScopeDescriptionPrefix,
                    Arrays.stream(scopes).count()
            );
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void CustomFlagsTest() {
        RuntimeEnvironment.Type runtimeEnvType = RuntimeEnvironment.Type.IOS;
        DeviceIdentity.Type deviceIdType = DeviceIdentity.Type.IOS_ADVERTISING_ID;
        UserIdentity.Type userIdType = UserIdentity.Type.EMAIL;

        try {
            // Construct and serialize request
            CustomEvent event = new CustomEvent();
            event.setName("customEvent");
            event.setCustomType(CustomEvent.CustomType.OTHER);

            event.setCustomFlags(new HashMap<String, String>() {{
                put("flag1", "value1");
                put("flag2", "value2");
                put("flag3", "value3");
            }});

            EventProcessingRequest req = GenerateEventProcessingRequest(runtimeEnvType, userIdType, deviceIdType);

            req.setEvents(Collections.singletonList(event));

            String json = serializer.serialize(req);

            // Deserialize request
            req = serializer.deserialize(json, EventProcessingRequest.class);
            assertNotNull(req);

            // Check the custom flags
            CustomEvent customEvent = (CustomEvent) req.getEvents().get(0);
            Map<String, String> flags = customEvent.getCustomFlags();

            assertEquals("value1", flags.get("flag1"));
            assertEquals("value2", flags.get("flag2"));
            assertEquals("value3", flags.get("flag3"));
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Helper method for checking for the given user identity type and value in the collection
     * @param identities
     * @param type
     * @param idValue
     * @return
     */
    private boolean verifyUserIdentity(List<UserIdentity> identities, UserIdentity.Type type, String idValue) {
        // Extract identity with given type
        List<UserIdentity> filteredIds = identities.stream()
                .filter(id -> id.getType() == type)
                .collect(Collectors.toList());

        // Verify only a single identity is present, and that it has the expected value
        return 1 == filteredIds.size() &&
                idValue.equals(filteredIds.get(0).getValue());
    }

    /**
     * Helper method for checking for the given device identity type and value in the collection
     * @param identities
     * @param type
     * @param idValue
     * @return
     */
    private boolean verifyDeviceIdentity(List<DeviceIdentity> identities, DeviceIdentity.Type type, String idValue) {
        // Extract identity with given type
        List<DeviceIdentity> filteredIds = identities.stream()
                .filter(id -> id.getType() == type)
                .collect(Collectors.toList());

        // Verify only a single identity is present, and that it has the expected value
        return 1 == filteredIds.size() &&
                idValue.equals(filteredIds.get(0).getValue());
    }

    /**
     * Generate an event processing request with ATT authorization status for testing
     * This only applies for iOS and TVOS
     * @param runtimeEnvType
     * @param userIdentityType
     * @param deviceIdentityType
     * @param attAuthorizationStatus
     * @return
     */
    private EventProcessingRequest GenerateEventProcessingRequest(RuntimeEnvironment.Type runtimeEnvType,
                                                                  UserIdentity.Type userIdentityType,
                                                                  DeviceIdentity.Type deviceIdentityType,
                                                                  AttAuthorizationStatus attAuthorizationStatus) {
        EventProcessingRequest eventProcessingRequest = GenerateEventProcessingRequest(runtimeEnvType, userIdentityType, deviceIdentityType);
        RuntimeEnvironment runtimeEnvironment = eventProcessingRequest.getRuntimeEnvironment();

        if (runtimeEnvironment.getType() == RuntimeEnvironment.Type.IOS) {
            IosRuntimeEnvironment iosRuntimeEnvironment = (IosRuntimeEnvironment) runtimeEnvironment;
            iosRuntimeEnvironment.setAttAuthorizationStatus(attAuthorizationStatus);
        } else if (runtimeEnvironment.getType() == RuntimeEnvironment.Type.TVOS) {
            TVOSRuntimeEnvironment tvosRuntimeEnvironment = (TVOSRuntimeEnvironment) runtimeEnvironment;
            tvosRuntimeEnvironment.setAttAuthorizationStatus(attAuthorizationStatus);
        }

        return eventProcessingRequest;
    }

    /**
     * Generate an event processing request for testing
     * @param runtimeEnvType
     * @param userIdentityType
     * @param deviceIdentityType
     * @return
     */
    private EventProcessingRequest GenerateEventProcessingRequest(RuntimeEnvironment.Type runtimeEnvType,
                                                                  UserIdentity.Type userIdentityType,
                                                                  DeviceIdentity.Type deviceIdentityType) {
        // Basic request info
        EventProcessingRequest req = new EventProcessingRequest();
        req.setFirehoseVersion(Consts.SDK_VERSION);
        req.setTimestamp(System.currentTimeMillis());
        req.setDeviceApplicationStamp(UUID.randomUUID().toString());
        req.setMpId("mpid");
        req.setSourceChannel(Consts.ChannelSourceType.NATIVE);
        req.setSourceId(UUID.randomUUID().toString());

        // Settings
        Account account = new Account();
        account.setAccountId(123456);
        account.setAccountSettings(new HashMap<>());
        account.getAccountSettings().put("apiKey", "sample API Key");
        req.setAccount(account);

        // UAs
        Map<String, String> uas = new HashMap<>();
        uas.put("aUserAttribute", "test");
        req.setUserAttributes(uas);

        // Identities
        req.setPartnerIdentities(Collections.singletonList(
                new PartnerIdentity("partner_id", Identity.Encoding.RAW, "partnerId")
        ));
        req.setUserIdentities(Collections.singletonList(
                new UserIdentity(userIdentityType, Identity.Encoding.RAW, "userId")));

        req.setRuntimeEnvironment(getRuntimeEnv(runtimeEnvType, deviceIdentityType));

        // Events
        CustomEvent event = new CustomEvent();
        event.setName("customEvent");
        event.setCustomType(CustomEvent.CustomType.OTHER);
        req.setEvents(Collections.singletonList(event));

        return req;
    }

    /**
     * Generate a sample runtime environment to be used for testing
     * @param type
     * @param deviceIdentityType
     * @return
     */
    private RuntimeEnvironment getRuntimeEnv(RuntimeEnvironment.Type type,
                                                    DeviceIdentity.Type deviceIdentityType) {
        // Set basic info
        RuntimeEnvironment runtime = getRuntimeEnv(type);
        runtime.setClientIpAddress("192.168.1.15");
        runtime.setSdkVersion("1.2.3");
        runtime.setUserAgent("user-agent");

        // Device Identities
        runtime.setIdentities(Collections.singletonList(
                new DeviceIdentity(deviceIdentityType, Identity.Encoding.RAW, "deviceId")));

        return runtime;
    }

    /**
     * Find the matching runtime-environment, based off of the type.
     * @param type
     * @return
     */
    private  RuntimeEnvironment getRuntimeEnv(RuntimeEnvironment.Type type) {
        switch(type) {

            case ANDROID:
                return new AndroidRuntimeEnvironment();
            case IOS:
                return new IosRuntimeEnvironment();
            case TVOS:
                return new TVOSRuntimeEnvironment();
            case ROKU:
                return new RokuRuntimeEnvironment();
            case XBOX:
                return new XboxRuntimeEnvironment();
            case FIRETV:
                return new FireTVRuntimeEnvironment();
            case MOBILEWEB:
                return new WebRuntimeEnvironment();

            case ALEXA:
            case SMARTTV:
            case UNKNOWN:
                return new GenericRuntimeEnvironment();

            default:
                return null;
        }
    }
}
