package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PlatformFields {

    @JsonProperty("mp_account_id")
    private int accountId;

    @JsonProperty("mp_account_name")
    private String accountName;

    @JsonProperty("workspace_id")
    private int workspaceId;

    @JsonProperty("workspace_name")
    private String workspaceName;

    @JsonProperty("input_name")
    private String inputName;

    @JsonProperty("app_name")
    private String appName;

    @JsonProperty("app_version")
    private String appVersion;

    @JsonProperty("dataplan_id")
    private String dataplanId;

    @JsonProperty("dataplan_name")
    private String dataplanName;

    @JsonProperty("dataplan_version")
    private Integer dataplanVersion;

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public int getWorkspaceId() { return workspaceId; }
    public void setWorkspaceId(int workspaceId) { this.workspaceId = workspaceId; }

    public String getWorkspaceName() { return workspaceName; }
    public void setWorkspaceName(String workspaceName) { this.workspaceName = workspaceName; }

    public String getInputName() { return inputName; }
    public void setInputName(String inputName) { this.inputName = inputName; }

    public String getAppName() { return appName; }
    public void setAppName(String appName) { this.appName = appName; }

    public String getAppVersion() { return appVersion; }
    public void setAppVersion(String appVersion) { this.appVersion = appVersion; }

    public String getDataplanId() { return dataplanId; }
    public void setDataplanId(String dataplanId) { this.dataplanId = dataplanId; }

    public String getDataplanName() { return dataplanName; }
    public void setDataplanName(String dataplanName) { this.dataplanName = dataplanName; }

    public Integer getDataplanVersion() { return dataplanVersion; }
    public void setDataplanVersion(Integer dataplanVersion) { this.dataplanVersion = dataplanVersion; }
}