package com.mparticle.sdk.model.registration.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class OAuth2Authentication extends AuthenticationConfiguration {

    public OAuth2Authentication()
    {
        super(Type.OAUTH2);
    }

    @JsonProperty(value="authorization_url", required = true)
    private String authorizationUrl;

    @JsonProperty(value="refresh_url")
    private String refreshUrl;

    @JsonProperty(value="token_url", required = true)
    private String tokenUrl;

    @JsonProperty(value="grant_type")
    private GrantType grantType;

    @JsonProperty(value="default_expires_in")
    private Integer defaultExpiresIn;

    @JsonProperty(value="client_id")
    private String clientId;

    @JsonProperty(value="access_token_type")
    private AccessTokenType accessTokenType;

    @JsonProperty(value="custom_header_name")
    private String customHeaderName;

    @JsonProperty(value="param_client_id_name")
    private String paramClientIdName;

    @JsonProperty(value="param_secret_name")
    private String paramSecretName;

    @JsonProperty(value="scopes")
    private ScopeDetail[] scopes;

    /**
     *
     * @return Authorization Url
     */
    public String getAuthorizationUrl() { return authorizationUrl; }

    /**
     *
     * @param authorizationUrl Authorization Url
     * @return this
     */
    public OAuth2Authentication setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
        return this;
    }

    /**
     *
     * @return Refresh URL
     */
    public String getRefreshUrl() { return refreshUrl; }

    /**
     *
     * @param refreshUrl Refresh URL
     * @return this
     */
    public OAuth2Authentication setRefreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
        return this;
    }

    /**
     *
     * @return Token Url
     */
    public String getTokenUrl() { return tokenUrl; }

    /**
     *
     * @param tokenUrl Token Url
     * @return this
     */
    public OAuth2Authentication setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
        return this;
    }

    /**
     *
     * @return Method used to gain Access Tokens
     */
    public GrantType getGrantType() { return grantType; }

    /**
     *
     * @param grantType Method used to gain Access Tokens
     * @return this
     */
    public OAuth2Authentication setGrantType(GrantType grantType) {
        this.grantType = grantType;
        return this;
    }

    /**
     *
     * @return duration of time the access token is granted for
     */
    public int getDefaultExpiresIn() { return defaultExpiresIn; }

    /**
     *
     * @param defaultExpiresIn duration of time the access token is granted for
     * @return this
     */
    public OAuth2Authentication setDefaultExpiresIn(int defaultExpiresIn) {
        this.defaultExpiresIn = defaultExpiresIn;
        return this;
    }

    /**
     *
     * @return Unique public identifier
     */
    public String getClientId() { return clientId; }

    /**
     *
     * @param clientId Unique public identifier
     * @return this
     */
    public OAuth2Authentication setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     *
     * @return Specifies how the access token will be provided
     */
    public AccessTokenType getAccessTokenType() {
        return accessTokenType;
    }

    /**
     *
     * @param accessTokenType Specifies how the access token will be provided
     * @return this
     */
    public OAuth2Authentication setAccessTokenType(AccessTokenType accessTokenType) {
        this.accessTokenType = accessTokenType;
        return this;
    }

    /**
     *
     * @return Name of the custom header for the access token
     */
    public String getCustomHeaderName() { return customHeaderName; }

    /**
     *
     * @param customHeaderName Name of the custom header for the access token
     * @return this
     */
    public OAuth2Authentication setCustomHeaderName(String customHeaderName) {
        this.customHeaderName = customHeaderName;
        return this;
    }

    /**
     *
     * @return Custom name for ClientID
     */
    public String getParamClientIdName() { return paramClientIdName; }

    /**
     *
     * @param paramClientIdName Custom name for ClientID
     * @return this
     */
    public OAuth2Authentication setParamClientIdName(String paramClientIdName) {
        this.paramClientIdName = paramClientIdName;
        return this;
    }

    /**
     *
     * @return Custom name for ClientSecret
     */
    public String getParamSecretName() { return paramSecretName; }

    /**
     *
     * @param paramSecretName Custom name for ClientSecret
     * @return this
     */
    public OAuth2Authentication setParamSecretName(String paramSecretName) {
        this.paramSecretName = paramSecretName;
        return this;
    }

    /**
     *
     * @return Scope to limit an app's access to user account
     */
    public ScopeDetail[] getScopes() {
        return scopes;
    }

    /**
     *
     * @param scopes Scope to limit an app's access to user account
     * @return this
     */
    public OAuth2Authentication setScopes(ScopeDetail[] scopes) {
        this.scopes = scopes;
        return this;
    }

    /**
     * Specifies how the access token will be provided to the integration
     */
    public enum AccessTokenType {
        BEARER,
        CUSTOM_HEADER;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public enum GrantType {
        AUTHORIZATION_CODE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
