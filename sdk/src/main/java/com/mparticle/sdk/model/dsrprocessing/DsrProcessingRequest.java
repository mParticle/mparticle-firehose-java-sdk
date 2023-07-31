package com.mparticle.sdk.model.dsrprocessing;

import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.registration.Account;

/**
 * This message contains the information to fulfill a Data Subject Request.
 */
public final class DsrProcessingRequest extends Message{
    
    public DsrProcessingRequest() {
        super(Message.Type.DSR_PROCESSING_REQUEST);
    }

    @JsonProperty(value="account", required=true)
    private Account account;

    @JsonProperty("subject_request_id")
    private String subjectRequestId;

    @JsonProperty("subject_request_type")
    private Type subjectRequestType;

    @JsonProperty("submitted_time")
    private Instant submittedTime; 

    @JsonProperty("subject_identities")
    private Map<OpenDsrIdentity.Type, OpenDsrIdentity> identities;

    @JsonProperty("api_version")
    private String openDsrApiVersion; 

    @JsonProperty("regulation")
    private RegulationType regulation;

    /**
     *
     * @return module account
     */
    public Account getAccount() {
        return account;
    }

    /**
     *
     * @param account module account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return the identifier for the request created by mParticle, this corresponds to a GUID internally
     */
    public String getSubjectRequestId() {
        return subjectRequestId;
    }

    /**
     * @param subjectRequestId the string to set as the indentifier
     */
    public void setSubjectRequestId(String subjectRequestId) {
        this.subjectRequestId = subjectRequestId;
    }

    /**
     * @return the type of request, currently only ERASURE is available
     */
    public Type getSubjectRequestType() {
        return subjectRequestType;
    }

    /**
     * @param subjectRequestType the type of this request, currently only ERASURE is valid
     */
    public void setSubjectRequestType(Type subjectRequestType) {
        this.subjectRequestType = subjectRequestType;
    }

    /**
     * @return the data regulation of this request, it can be CCPA, GDPR or UNKNOWN
     */
    public RegulationType getRegulation() {
        return regulation;
    }

    /**
     * @param regulation the data regulation of this request, it can be CCPA, GDPR or UNKNOWN
     */
    public void setRegulation(RegulationType regulation) {
        this.regulation = regulation;
    }

    /**
     * @return the version of OpenDSR which this request is subject to
     * @see <a href="https://github.com/opengdpr/OpenDSR">OpenDSR spec</a>
     */
    public String getOpenDsrApiVersion() {
        return openDsrApiVersion;
    }

    /**
     * @param openDsrApiVersion the version of OpenDSR which this request is subject to
     * @see <a href="https://github.com/opengdpr/OpenDSR">OpenDSR spec</a>
     */
    public void setOpenDsrApiVersion(String openDsrApiVersion) {
        this.openDsrApiVersion = openDsrApiVersion;
    }

    /**
     * @return the timestamp when a user created the request in milliseconds since epoch, this can be done by using the mParticle API or 
     * the mParticle UI
     */
    public Instant getSubmittedTime() {
        return submittedTime;
    }

    /**
     * @param submittedTime the timestamp when a user created the request in milliseconds since epoch
     */
    public void setSubmittedTime(Instant submittedTime) {
        this.submittedTime = submittedTime;
    }

    /**
     * @return the list of identities to process, they can be partner, user or device identities 
     */
    public Map<OpenDsrIdentity.Type, OpenDsrIdentity> getIdentities() {
        return identities;
    }

    /**
     * @param identities the list of identities to process, they can be partner, user or device identities
     */
    public void setIdentities(Map<OpenDsrIdentity.Type, OpenDsrIdentity> identities) {
        this.identities = identities;
    }

    public enum Type {
        ERASURE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
