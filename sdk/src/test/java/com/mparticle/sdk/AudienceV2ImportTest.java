package com.mparticle.sdk;

import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.audienceprocessing.*;
import com.mparticle.sdk.model.eventprocessing.DeviceIdentity;
import com.mparticle.sdk.model.eventprocessing.Identity;
import com.mparticle.sdk.model.eventprocessing.PartnerIdentity;
import com.mparticle.sdk.model.eventprocessing.UserIdentity;
import com.mparticle.sdk.model.registration.Account;
import com.mparticle.sdk.model.registration.AudienceProcessingRegistration;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;
import com.mparticle.sdk.model.registration.authentication.OAuth2Authentication;
import com.mparticle.sdk.model.registration.authentication.OAuth2Authentication.GrantType;
import com.mparticle.sdk.model.registration.authentication.OAuth2Authentication.AccessTokenType;
import com.mparticle.sdk.model.registration.authentication.ScopeDetail;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.AbstractMap.SimpleImmutableEntry;
import static com.mparticle.sdk.model.audienceprocessing.Audience.AudienceAction;
import static com.mparticle.sdk.model.audienceprocessing.UserAttributeAudienceEvent.AttributeAction;

public class AudienceV2ImportTest extends ImportTest {

    private MessageSerializer serializer = new MessageSerializer();

    // Set-up Audience data
    private String audienceName = "audienceName";
    private int audienceId = 123;

    // Set-up User data
    private String mpid = "mpid";
    private UserIdentity.Type userIdentityType = UserIdentity.Type.EMAIL;
    private DeviceIdentity.Type deviceIdentityType = DeviceIdentity.Type.IOS_ADVERTISING_ID;
    private String attributeKey = "key", attributeVal = "val";

    /**
     * Helper to generate testing data for our device id 'theory'
     * @return
     */
    private static Stream<SimpleImmutableEntry<AudienceAction, AttributeAction>> audienceProvider() {
        return Arrays.stream(AudienceAction.values()).flatMap(audAction ->
                Arrays.stream(AttributeAction.values()).map(attrAction ->
                        new SimpleImmutableEntry<>(audAction, attrAction))
        );
    }

    @ParameterizedTest
    @EnumSource(UserIdentity.Type.class)
    public void UserIdentityTest(UserIdentity.Type userIdentityType) {
        // Set-up testing data
        Audience audience = generateAudience(AudienceAction.ADD, audienceId, audienceName);
        UserProfile profile = generateUserProfile(deviceIdentityType, userIdentityType, mpid, audience);
        AudienceMembershipChangeRequest request = generateAudienceMembershipChangeRequest(profile);

        try {
            // Serialize and deserialize the request
            String json = serializer.serialize(request);
            request = serializer.deserialize(json, AudienceMembershipChangeRequest.class);
            assertNotNull(request);

            // Get the profile
            assertEquals(1, request.getUserProfiles().size());
            profile = request.getUserProfiles().get(0);

            // Extract out the user identity
            assertEquals(1, profile.getUserIdentities().size());
            UserIdentity userIdentity = profile.getUserIdentities().get(0);
            assertEquals(userIdentityType, userIdentity.getType());
            assertEquals("userId", userIdentity.getValue());
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @EnumSource(DeviceIdentity.Type.class)
    public void DeviceIdentityTest(DeviceIdentity.Type deviceIdentityType) {
        // Set-up testing data
        Audience audience = generateAudience(AudienceAction.ADD, audienceId, audienceName);
        UserProfile profile = generateUserProfile(deviceIdentityType, userIdentityType, mpid, audience);
        AudienceMembershipChangeRequest request = generateAudienceMembershipChangeRequest(profile);

        try {
            // Serialize and deserialize the request
            String json = serializer.serialize(request);
            request = serializer.deserialize(json, AudienceMembershipChangeRequest.class);
            assertNotNull(request);

            // Get the profile
            assertEquals(1, request.getUserProfiles().size());
            profile = request.getUserProfiles().get(0);

            // Extract out the device identity
            assertEquals(1, profile.getDeviceIdentities().size());
            DeviceIdentity deviceIdentity = profile.getDeviceIdentities().get(0);
            assertEquals(deviceIdentityType, deviceIdentity.getType());
            assertEquals("deviceId", deviceIdentity.getValue());
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void PartnerIdentityTest() {
        // Set-up testing data
        Audience audience = generateAudience(AudienceAction.ADD, audienceId, audienceName);
        UserProfile profile = generateUserProfile(deviceIdentityType, userIdentityType, mpid, audience);
        AudienceMembershipChangeRequest request = generateAudienceMembershipChangeRequest(profile);

        try {
            // Serialize and deserialize the request
            String json = serializer.serialize(request);
            request = serializer.deserialize(json, AudienceMembershipChangeRequest.class);
            assertNotNull(request);

            // Get the profile
            assertEquals(1, request.getUserProfiles().size());
            profile = request.getUserProfiles().get(0);

            // Extract out the partner identity
            List<PartnerIdentity> partnerIdentities = profile.getPartnerIdentities();
            assertEquals(1, partnerIdentities.size());
            PartnerIdentity partnerIdentity = partnerIdentities.get(0);
            assertEquals("partner_id", partnerIdentity.getType());
            assertEquals("partnerId", partnerIdentity.getValue());
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @EnumSource(AudienceAction.class)
    public void AudienceNoAttributesTest(AudienceAction audienceAction) {
        // Set-up testing data
        Audience audience = generateAudience(audienceAction, audienceId, audienceName);
        UserProfile profile = generateUserProfile(deviceIdentityType, userIdentityType, mpid, audience);
        AudienceMembershipChangeRequest request = generateAudienceMembershipChangeRequest(profile);

        try {
            // Serialize and deserialize the request
            String json = serializer.serialize(request);
            request = serializer.deserialize(json, AudienceMembershipChangeRequest.class);
            assertNotNull(request);

            // Check the profile.
            assertEquals(1, request.getUserProfiles().size());
            profile = request.getUserProfiles().get(0);

            // Check the audience
            assertEquals(1, profile.getAudiences().size());
            audience = profile.getAudiences().get(0);
            assertNotNull(audience);
            checkAudience(audience, audienceName, audienceId, audienceAction);
            assertNull(audience.getUserAttributes());
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource("audienceProvider")
    public void AudienceAndAttributeTest(SimpleImmutableEntry<AudienceAction, AttributeAction> audienceAndAttributeTuple) {
        AudienceAction audienceAction = audienceAndAttributeTuple.getKey();
        AttributeAction attributeAction = audienceAndAttributeTuple.getValue();

        // Set-up testing data
        UserAttributeAudienceEvent audienceAttribute = generateAttribute(attributeAction, attributeKey, attributeVal);
        Audience audience = generateAudience(audienceAction, audienceId, audienceName, audienceAttribute);
        UserProfile profile = generateUserProfile(deviceIdentityType, userIdentityType, mpid, audience);
        AudienceMembershipChangeRequest request = generateAudienceMembershipChangeRequest(profile);

        PerformTest(request,
                audienceName,
                audienceId,
                audienceAction,
                attributeAction,
                attributeKey,
                attributeVal
        );
    }

    @ParameterizedTest
    @MethodSource("audienceProvider")
    public void AudienceAndListAttributeTest(SimpleImmutableEntry<AudienceAction, AttributeAction> audienceAndAttributeTuple) {
        AudienceAction audienceAction = audienceAndAttributeTuple.getKey();
        AttributeAction attributeAction = audienceAndAttributeTuple.getValue();

        // Set up list attribute
        String attributeVal2 = "val2", attributeVal3 = "val3";
        UserAttributeAudienceEvent audienceAttribute = generateAttribute(attributeAction,
                attributeKey,
                attributeVal,
                attributeVal2,
                attributeVal3
        );

        // Set-up testing data
        Audience audience = generateAudience(audienceAction, audienceId, audienceName, audienceAttribute);
        UserProfile profile = generateUserProfile(deviceIdentityType, userIdentityType, mpid, audience);
        AudienceMembershipChangeRequest request = generateAudienceMembershipChangeRequest(profile);

        PerformTest(request,
                audienceName,
                audienceId,
                audienceAction,
                attributeAction,
                attributeKey,
                attributeVal,
                attributeVal2,
                attributeVal3
        );
    }

    @Test
    public void OAuth2SettingsTest() {
        String testAuthorizationUrl = "TEST_AUTHORIZATION_URL", testRefreshUrl = "TEST_REFRESH_URL", testTokenUrl = "TEST_TOKEN_URL", testClientId = "TEST_CLIENT_ID";
        String testCustomHeaderName ="TEST_CUSTOMER_HEADER_NAME", testParamClientIdName = "TEST_PARAM_CLIENT_ID_NAME", testParamSecretName = "TEST_PARAM_SECRET_NAME";
        String testScopeNamePrefix = "TEST_SCOPE_NAME_", testScopeDescriptionPrefix = "TEST_SCOPE_DESCRIPTION_";
        Integer defaultExpiresIn = 1000;

        OAuth2Authentication authentication = new OAuth2Authentication();
        AudienceProcessingRegistration audienceProcessingRegistration = new AudienceProcessingRegistration();
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
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setDefaultExpiresIn(defaultExpiresIn)
                .setClientId(testClientId)
                .setAccessTokenType(AccessTokenType.CUSTOM_HEADER)
                .setCustomHeaderName(testCustomHeaderName)
                .setParamClientIdName(testParamClientIdName)
                .setParamSecretName(testParamSecretName)
                .setScopes(scopes);

        audienceProcessingRegistration.setAuthentication(authentication);

        ModuleRegistrationResponse mmr = new ModuleRegistrationResponse("OAuth Permission Test Name", "Test Version");
        mmr.setAudienceProcessingRegistration(audienceProcessingRegistration);

        try {
            String json = serializer.serialize(mmr);
            mmr = serializer.deserialize(json, ModuleRegistrationResponse.class);

            assertNotNull(mmr);

            authentication = (OAuth2Authentication) mmr.getAudienceProcessingRegistration().getAuthentication();

            checkOAuthSettings(authentication,
                    testAuthorizationUrl,
                    testRefreshUrl,
                    testTokenUrl,
                    GrantType.AUTHORIZATION_CODE,
                    defaultExpiresIn,
                    testClientId,
                    AccessTokenType.CUSTOM_HEADER,
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

    /**
     * Helper method for performing the test and validating expected values
     * @param request
     * @param expectedAudienceName
     * @param expectedAudienceId
     * @param expectedAudienceAction
     * @param expectedAttributeAction
     * @param expectedAttributeKey
     * @param expectedAttributeVal
     */
    private void PerformTest(AudienceMembershipChangeRequest request,
                             String expectedAudienceName,
                             int expectedAudienceId,
                             AudienceAction expectedAudienceAction,
                             AttributeAction expectedAttributeAction,
                             String expectedAttributeKey,
                             String... expectedAttributeVal)
    {
        try {
            // Serialize and deserialize the request
            String json = serializer.serialize(request);
            request = serializer.deserialize(json, AudienceMembershipChangeRequest.class);
            assertNotNull(request);

            // Check the audience in the profile.
            assertEquals(1, request.getUserProfiles().size());
            UserProfile profile = request.getUserProfiles().get(0);
            assertEquals(1, profile.getAudiences().size());
            Audience audience = profile.getAudiences().get(0);
            assertNotNull(audience);
            checkAudience(audience, expectedAudienceName, expectedAudienceId, expectedAudienceAction);

            // Check the list attribute
            assertNotNull(audience.getUserAttributes());
            assertEquals(1, audience.getUserAttributes().size());
            UserAttributeAudienceEvent attr = audience.getUserAttributes().get(0);
            checkUserAttribute(attr, expectedAttributeAction, expectedAttributeKey, expectedAttributeVal);
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Check the expected audience name and action
     * @param aud
     * @param audName
     * @param audId
     * @param audAction
     */
    private void checkAudience(Audience aud, String audName, int audId, AudienceAction audAction) {
        assertEquals(audAction, aud.getAudienceAction());
        assertEquals(audName, aud.getAudienceName());
        assertEquals(audId, aud.getAudienceId());
    }

    /**
     * Helper function for checking user attributes
     * @param attribute
     * @param attrAction
     * @param expectedKey
     * @param expectedValue
     */
    private void checkUserAttribute(UserAttributeAudienceEvent attribute,
                                    AttributeAction attrAction,
                                    String expectedKey,
                                    String... expectedValue) {
        assertNotNull(attribute);
        assertEquals(attrAction, attribute.getAction());
        assertEquals(expectedKey, attribute.getKey());

        // If specified, check every provided value is included with this attribute
        if (attribute.getListValue() != null) {
            assertNotNull(attribute.getListValue());
            assertTrue(
                    Arrays.stream(expectedValue)
                    .map(val -> attribute.getListValue().contains(val))
                    .reduce((val, acc) -> val && acc)
                    .orElse(false)
            );
        }
        else
        {
            assertEquals(expectedValue[0], attribute.getValue());
        }
    }

    /**
     * Generate an AudienceMembershipChangeRequest for testing
     * @param profiles
     * @return
     */
    private AudienceMembershipChangeRequest generateAudienceMembershipChangeRequest(UserProfile... profiles)
    {
        AudienceMembershipChangeRequest req = new AudienceMembershipChangeRequest();

        // Set account
        Account account = new Account();
        account.setAccountId(123456);
        account.setAccountSettings(new HashMap<>());
        account.getAccountSettings().put("Example Setting", "Example Value");
        req.setAccount(account);

        // Set profiles
        req.setUserProfiles(Arrays.stream(profiles).collect(Collectors.toList()));

        return req;
    }

    /**
     * Generate a user profile for testing
     * @param deviceIdentityType
     * @param userIdentityType
     * @param mpid
     * @param audiences
     * @return
     */
    private UserProfile generateUserProfile(DeviceIdentity.Type deviceIdentityType,
                                            UserIdentity.Type userIdentityType,
                                            String mpid,
                                            Audience... audiences) {
        UserProfile profile = new UserProfile();

        // Set identity info
        profile.setDeviceIdentities((Collections.singletonList(
                new DeviceIdentity(deviceIdentityType, Identity.Encoding.RAW, "deviceId"))));
        profile.setUserIdentities(Collections.singletonList(
                new UserIdentity(userIdentityType, Identity.Encoding.RAW, "userId")));
        profile.setPartnerIdentities(Collections.singletonList(
                new PartnerIdentity("partner_id", Identity.Encoding.RAW, "partnerId")
        ));
        profile.setMpId(mpid);

        // Set audience changes
        profile.setAudiences(Arrays.stream(audiences).collect(Collectors.toList()));

        return profile;
    }

    /**
     * Generate an audience for testing
     * @param audienceAction
     * @param audienceId
     * @param audienceName
     * @param attributes
     * @return
     */
    private Audience generateAudience(AudienceAction audienceAction,
                                      int audienceId,
                                      String audienceName,
                                      UserAttributeAudienceEvent... attributes) {
        Audience audience = new Audience();
        audience.setAudienceId(audienceId);
        audience.setAudienceName((audienceName));
        audience.setAudienceAction(audienceAction);
        audience.setUserAttributes(Arrays.stream(attributes).collect(Collectors.toList()));

        // Settings
        audience.setAudienceSubscriptionSettings(new HashMap<>());
        audience.getAudienceSubscriptionSettings().put("Example Setting", "Example Value");

        return audience;
    }

    /**
     * Create an event to update an attribute for testing
     * @param key
     * @param values
     * @return
     */
    public UserAttributeAudienceEvent generateAttribute(AttributeAction action,
                                                        String key,
                                                        String... values) {
        UserAttributeAudienceEvent attr = new UserAttributeAudienceEvent();
        attr.setAction(action);
        attr.setKey(key);

        // Determine whether this is a list attribute or not
        if (values.length > 1)
            attr.setListValue(Arrays.stream(values).collect(Collectors.toList()));
        else
            attr.setValue(values[0]);

        return attr;
    }
}
