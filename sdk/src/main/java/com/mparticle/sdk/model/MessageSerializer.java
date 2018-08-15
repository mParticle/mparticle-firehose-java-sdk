package com.mparticle.sdk.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Converts messages to JSON and back.
 */
public final class MessageSerializer {

    private final ObjectMapper mapper;

    public MessageSerializer() {
        mapper = new ObjectMapper();
        // ignore missing fields
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // ignore nulls and empty containers
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // only include annotated fields
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        // use toString() for enums
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
    }

    /**
     * Deserializes Java class from JSON.
     * @param inputStream input stream
     * @param valueType Java class
     * @param <T> Java class type
     * @return Java class
     * @throws IOException if read fails
     */
    public <T> T deserialize(InputStream inputStream, Class<T> valueType) throws IOException {
        return mapper.readValue(inputStream, valueType);
    }

    /**
     * Deserializes Java class from JSON.
     * @param content content
     * @param valueType Java class type to deserialize
     * @param <T> Java class type
     * @return Java class
     * @throws IOException if read fails
     */

    public <T> T deserialize(String content, Class<T> valueType) throws IOException {
        return mapper.readValue(content, valueType);
    }

    /**
     * Serializes Java class to JSON.
     * @param outputStream output stream
     * @param value Java class to serialize
     * @throws IOException if write fails
     */
    public void serialize(OutputStream outputStream, Object value) throws IOException {
        mapper.writeValue(outputStream, value);
    }

    /**
     * Serializes Java class to JSON.
     * @param value Java class to serialize
     * @param outputStream output stream
     * @throws IOException if write fails
     */
    public void serializePretty(OutputStream outputStream, Object value) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, value);
    }

    /**
     * Serializes Java class to JSON.
     * @param value Java class to serialize
     * @return JSON string
     * @throws IOException if write fails
     */
    public String serialize(Object value) throws IOException {
        return mapper.writeValueAsString(value);
    }
}
