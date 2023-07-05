package com.mparticle.sdk.generator;

import java.time.Instant;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import com.mparticle.sdk.model.dsrprocessing.DsrProcessingRequest;
import com.mparticle.sdk.model.dsrprocessing.OpenDsrIdentity;
import com.mparticle.sdk.model.dsrprocessing.RegulationType;
import com.mparticle.sdk.model.dsrprocessing.DsrProcessingRequest.Type;
import com.mparticle.sdk.model.dsrprocessing.OpenDsrIdentity.Encoding;

public class DsrProcessingRequestSample {

    public static Map.Entry<String, DsrProcessingRequest> GenerateMessage()
    {
        DsrProcessingRequest req = new DsrProcessingRequest();

        req.setTimestamp(System.currentTimeMillis());
        
        req.setSubmittedTime(Instant.now());
        Map <OpenDsrIdentity.Type, OpenDsrIdentity> identities =  new HashMap<>();
        
        OpenDsrIdentity android_id = new OpenDsrIdentity();
        android_id.setEncoding(Encoding.RAW);
        android_id.setValue("84aae47d-d376-4a35-bde5-c0ee5a808bd3");
        
        identities.put(OpenDsrIdentity.Type.ANDROID_ADVERTISING_ID, android_id);

        OpenDsrIdentity email_id = new OpenDsrIdentity();
        email_id.setEncoding(Encoding.RAW);
        email_id.setValue("jane.doe@example.com");

        identities.put(OpenDsrIdentity.Type.EMAIL, email_id);

        req.setIdentities(identities);

        req.setRegulation(RegulationType.GDPR);
        req.setOpenDsrApiVersion("3.0");
        req.setSubjectRequestId("1e2c8cf4-f985-4398-b482-a520860d3bf6");
        req.setSubjectRequestType(Type.ERASURE);

        return new AbstractMap.SimpleImmutableEntry<>(req.getClass().getSimpleName(), req);
    }
}
