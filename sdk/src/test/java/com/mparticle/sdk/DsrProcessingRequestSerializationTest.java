package com.mparticle.sdk;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.dsrprocessing.DsrProcessingRequest;
import com.mparticle.sdk.model.dsrprocessing.OpenDsrIdentity;

public class DsrProcessingRequestSerializationTest {
    @Test
    public void CanDeserializeFromJsonExample(){
        MessageSerializer serializer = new MessageSerializer();
        String jsonExampleToString = "{\"type\":\"dsr_processing_request\",\"regulation\":\"gdpr\"," + 
        "\"subject_request_id\":\"a7551968-d5d6-44b2-9831-815ac9017798\",\"subject_request_type\":\"erasure\"," + 
        "\"submitted_time\":\"2021-11-01T15:00:00Z\",\"subject_identities\":{\"email\":{\"value\":\"johndoe@example.com\",\"encoding\":\"raw\"}," +
        "\"ios_advertising_id\":{\"value\":\"EA7583CD-A667-48BC-B806-42ECB2B48606\",\"encoding\":\"raw\"}},\"api_version\":\"3.0\","+ 
        "\"extensions\":{\"opendsr.mparticle.com\":{\"skip_waiting_period\":false,\"subject_identities\":{\"other6\":{"+
        "\"value\":\"EA7583CD-A667-48BC-B806-42ECB2B48606\",\"encoding\":\"raw\"}}}}}";
        try {
            DsrProcessingRequest request = serializer.deserialize(jsonExampleToString, DsrProcessingRequest.class);
            assertNotNull(request);
            assertEquals("gdpr", request.getRegulation().toString());
            assertEquals("johndoe@example.com", request.getIdentities().get(OpenDsrIdentity.Type.EMAIL).getValue());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
