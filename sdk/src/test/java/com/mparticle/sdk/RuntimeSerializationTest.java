package com.mparticle.sdk;

import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.RuntimeEnvironment;
import com.mparticle.sdk.model.eventprocessing.WebRuntimeEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RuntimeSerializationTest {

    /**
     * Tests that http_header_user_agent is accessible through both RuntimeEnvironment.getUserAgent() and
     * WebRuntimeEnvironment.getHttpHeaderUserAgent()
     *
     * @throws IOException from MessageSerializer
     */
    @Test
    public void testWebRuntimeUserAgentSerialization() throws IOException {
        String json = "{\n" +
                "        \"type\": \"mobileweb\",\n" +
                "        \"screen_height\": 600,\n" +
                "        \"screen_width\": 400,\n" +
                "        \"screen_dpi\": 0,\n" +
                "        \"http_header_user_agent\": \"MyTestUserAgent\",\n" +
                "        \"is_dst\": false,\n" +
                "        \"application_name\": \"Test App Development\",\n" +
                "        \"is_debug\": true,\n" +
                "        \"client_ip_address\": \"1.2.3.4\",\n" +
                "        \"identities\":\n" +
                "        [],\n" +
                "        \"sdk_version\": \"2.23.2\"\n" +
                "    }";

        MessageSerializer serializer = new MessageSerializer();

        RuntimeEnvironment runtime = serializer.deserialize(json, RuntimeEnvironment.class);
        Assertions.assertEquals("MyTestUserAgent", runtime.getUserAgent(), "Correct user agent parses correctly");

        if (runtime instanceof WebRuntimeEnvironment) {
            Assertions.assertEquals("MyTestUserAgent", ((WebRuntimeEnvironment) runtime).getHttpHeaderUserAgent(), "Correct user agent parses correctly");
        }
    }
}
