package com.mparticle.sdk.model.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mparticle.sdk.Utils;

public class BulkConfiguration {

    @JsonProperty("bulk_forward_wait_for_messages")
    private Integer bulkForwardWaitForMessages;

    @JsonProperty("bulk_forward_wait_in_minutes")
    private Integer bulkForwardWaitInMinutes;

    /**
     *
     * @return bulkForwardWaitForMessages
     */
    public Integer getBulkForwardWaitForMessages() {
        return bulkForwardWaitForMessages;
    }

    /**
     *
     * @param bulkForwardWaitForMessages setting name
     * @return this
     */
    public BulkConfiguration setBulkForwardWaitForMessages(Integer bulkForwardWaitForMessages) {
        this.bulkForwardWaitForMessages = bulkForwardWaitForMessages;
        return this;
    }

    /**
     *
     * @return bulkForwardWaitInMinutes
     */
    public Integer getBulkForwardWaitInMinutes() {
        return bulkForwardWaitInMinutes;
    }

    /**
     *
     * @param bulkForwardWaitInMinutes setting name
     * @return this
     */
    public BulkConfiguration setBulkForwardWaitInMinutes(Integer bulkForwardWaitInMinutes) {
        this.bulkForwardWaitInMinutes = bulkForwardWaitInMinutes;
        return this;
    }
}
