package com.mparticle.sdk;
import com.mparticle.sdk.model.registration.authentication.OAuth2Authentication;
import com.mparticle.sdk.model.registration.authentication.ScopeDetail;
import static org.junit.jupiter.api.Assertions.*;

public abstract class ImportTest {
    protected void checkOAuthSettings(OAuth2Authentication authentication,
                                      String expectedAuthorizationUrl,
                                      String expectedRefreshUrl,
                                      String expectedTokenUrl,
                                      OAuth2Authentication.GrantType expectedGrantType,
                                      Integer expectedDefaultExpiresIn,
                                      String expectedClientId,
                                      OAuth2Authentication.AccessTokenType expectedAccessTokenType,
                                      String expectedCustomHeaderName,
                                      String expectedParamClientIdName,
                                      String expectedParamSecretName,
                                      String expectedScopeNamePrefix,
                                      String expectedScopeDescriptionPrefix,
                                      Long expectedScopeCount

    ) {
        assertNotNull(authentication);

        assertEquals(expectedAuthorizationUrl, authentication.getAuthorizationUrl());
        assertEquals(expectedRefreshUrl, authentication.getRefreshUrl());
        assertEquals(expectedTokenUrl, authentication.getTokenUrl());
        assertEquals(OAuth2Authentication.GrantType.AUTHORIZATION_CODE, authentication.getGrantType());
        assertEquals(expectedDefaultExpiresIn, authentication.getDefaultExpiresIn());
        assertEquals(expectedClientId, authentication.getClientId());
        assertEquals(OAuth2Authentication.AccessTokenType.CUSTOM_HEADER, authentication.getAccessTokenType());
        assertEquals(expectedCustomHeaderName, authentication.getCustomHeaderName());
        assertEquals(expectedParamClientIdName, authentication.getParamClientIdName());
        assertEquals(expectedParamSecretName, authentication.getParamSecretName());

        ScopeDetail[] scopes = authentication.getScopes();
        assertEquals(expectedScopeCount, authentication.getScopes().length);

        for(Integer i = 0; i < expectedScopeCount; i++) {
            assertEquals(expectedScopeNamePrefix + (i + 1), scopes[i].getName());
            assertEquals(expectedScopeDescriptionPrefix + (i + 1), scopes[i].getDescription());
        }
    }
}