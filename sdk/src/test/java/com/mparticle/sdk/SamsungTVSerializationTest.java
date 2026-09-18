package com.mparticle.sdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.DeviceIdentityPermission;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class SamsungTVSerializationTest {

    private final MessageSerializer serializer = new MessageSerializer();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    public void testSamsungTVEventRequest() throws IOException {
        // Synthetic payload using the server's GenericRuntimeEnvironment wire fields.
        JsonNode input;
        try (InputStream stream = getClass().getResourceAsStream("/samsungtv-event-processing-request.json")) {
            assertNotNull(stream);
            input = jsonMapper.readTree(stream);
        }

        Message message = serializer.deserialize(input.toString(), Message.class);
        assertTrue(message instanceof EventProcessingRequest);
        EventProcessingRequest request = (EventProcessingRequest) message;
        assertTrue(request.getRuntimeEnvironment() instanceof SamsungTVRuntimeEnvironment);
        SamsungTVRuntimeEnvironment runtime = (SamsungTVRuntimeEnvironment) request.getRuntimeEnvironment();
        assertEquals(RuntimeEnvironment.Type.SAMSUNGTV, runtime.getType());
        assertEquals("Samsung", runtime.getBrand());
        assertEquals("Tizen 8.0", runtime.getOsVersion());
        assertEquals("SamsungTV Test", runtime.getApplicationName());
        assertEquals(1920, runtime.getScreenWidth());
        assertEquals("SamsungTV test", runtime.getUserAgent());
        assertEquals(1, runtime.getIdentities().size());
        DeviceIdentity identity = runtime.getIdentities().get(0);
        assertEquals(DeviceIdentity.Type.TIZEN_ADVERTISING_ID, identity.getType());
        assertEquals(Identity.Encoding.RAW, identity.getEncoding());
        assertEquals("17b8106f-4563-4b7a-8c7f-629c84691168", identity.getValue());
        assertEquals("samsungtv-test", ((CustomEvent) request.getEvents().get(0)).getName());

        // Check every device field survives, including inherited fields and wire names.
        JsonNode output = jsonMapper.readTree(serializer.serialize(request));
        assertEquals(input.get("runtime_environment"), output.get("runtime_environment"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", ",\"identities\":[]"})
    public void testSamsungTVWithoutAdvertisingId(String identities) throws IOException {
        RuntimeEnvironment runtime = serializer.deserialize(
                "{\"type\":\"samsungtv\"" + identities + "}", RuntimeEnvironment.class);
        assertTrue(runtime instanceof SamsungTVRuntimeEnvironment);
        assertEquals(RuntimeEnvironment.Type.SAMSUNGTV, runtime.getType());
        assertTrue(runtime.getIdentities() == null || runtime.getIdentities().isEmpty());
    }

    @Test
    public void testSamsungTVRegistration() throws IOException {
        String json = "{\"type\":\"module_registration_response\","
                + "\"event_processing_registration\":{\"supported_runtime_environments\":[\"samsungtv\"]},"
                + "\"permissions\":{\"device_identities\":[{\"type\":\"tizen_advertising_id\","
                + "\"encoding\":\"raw\",\"required\":false}]}}";
        ModuleRegistrationResponse response = serializer.deserialize(json, ModuleRegistrationResponse.class);
        assertEquals(RuntimeEnvironment.Type.SAMSUNGTV,
                response.getEventProcessingRegistration().getSupportedRuntimeEnvironments().get(0));
        DeviceIdentityPermission permission = response.getPermissions().getDeviceIdentities().get(0);
        assertEquals(DeviceIdentity.Type.TIZEN_ADVERTISING_ID, permission.getType());
        assertEquals(Identity.Encoding.RAW, permission.getEncoding());
        assertFalse(permission.isRequired());

        JsonNode output = jsonMapper.readTree(serializer.serialize(response));
        JsonNode input = jsonMapper.readTree(json);
        assertEquals(input.get("permissions"), output.get("permissions"));
        assertEquals(input.at("/event_processing_registration/supported_runtime_environments"),
                output.at("/event_processing_registration/supported_runtime_environments"));
    }

    @Test
    public void testGenericRuntimeDefaultIsUnchanged() throws IOException {
        GenericRuntimeEnvironment runtime = new GenericRuntimeEnvironment();
        assertEquals(RuntimeEnvironment.Type.UNKNOWN, runtime.getType());
        assertEquals("unknown", jsonMapper.readTree(serializer.serialize(runtime)).get("type").asText());
    }
}
