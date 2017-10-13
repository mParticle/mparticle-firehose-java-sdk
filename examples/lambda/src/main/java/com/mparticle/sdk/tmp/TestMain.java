package com.mparticle.sdk.tmp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.audienceprocessing.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.*;
import com.mparticle.sdk.samples.SampleExtension;
import com.sun.org.apache.xpath.internal.operations.And;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        generateJsonSchema();
        //testSerializer();
        testRegister();
        //testEventProcessing();
    }

    private static void testRegister() {
        MessageSerializer m = new MessageSerializer();
        SampleExtension processor = new SampleExtension();

        try {

            ModuleRegistrationRequest request = new ModuleRegistrationRequest();
            String data = m.serialize(request);
            Message response = processor.processMessage(request);
            data = m.serialize(response);
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void generateJsonSchema() {
        ObjectMapper mapper = new ObjectMapper();
        // ignore missing fields
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // ignore nulls and empty containers
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // only include annotated fields
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        // use toString() for enums
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        //registration
        printSchema(Account.class, mapper);
        printSchema(AudienceProcessingRegistration.class, mapper);
        printSchema(BooleanSetting.class, mapper);
        printSchema(DeviceIdentityPermission.class, mapper);
        printSchema(EventProcessingRegistration.class, mapper);
        printSchema(FloatSetting.class, mapper);
        printSchema(IntegerSetting.class, mapper);
        printSchema(ModuleRegistrationRequest.class, mapper);
        printSchema(ModuleRegistrationResponse.class, mapper);
        printSchema(Permissions.class, mapper);
        printSchema(Setting.class, mapper);
        printSchema(TextSetting.class, mapper);
        printSchema(UserIdentityPermission.class, mapper);
        //audience
        printSchema(Audience.class, mapper);
        printSchema(AudienceMembershipChangeRequest.class, mapper);
        printSchema(AudienceMembershipChangeResponse.class, mapper);
        printSchema(AudienceSubscriptionRequest.class, mapper);
        printSchema(AudienceSubscriptionResponse.class, mapper);
        printSchema(UserProfile.class, mapper);
        //event processing
        printSchema(AndroidRuntimeEnvironment.class, mapper);
        printSchema(ApplicationStateTransitionEvent.class, mapper);
        printSchema(AttributionEvent.class, mapper);
        printSchema(CustomEvent.class, mapper);
        printSchema(DeviceIdentity.class, mapper);
        printSchema(ErrorEvent.class, mapper);
        printSchema(Event.class, mapper);
        printSchema(EventProcessingRequest.class, mapper);
        printSchema(EventProcessingResponse.class, mapper);
        printSchema(Identity.class, mapper);
        printSchema(Impression.class, mapper);
        printSchema(ImpressionEvent.class, mapper);
        printSchema(IosRuntimeEnvironment.class, mapper);
        printSchema(Location.class, mapper);
        printSchema(PrivacySettingChangeEvent.class, mapper);
        printSchema(Product.class, mapper);
        printSchema(ProductActionEvent.class, mapper);
        printSchema(Promotion.class, mapper);
        printSchema(PromotionActionEvent.class, mapper);
        printSchema(PushMessageReceiptEvent.class, mapper);
        printSchema(PushSubscriptionEvent.class, mapper);
        printSchema(RuntimeEnvironment.class, mapper);
        printSchema(ScreenViewEvent.class, mapper);
        printSchema(SessionEndEvent.class, mapper);
        printSchema(SessionStartEvent.class, mapper);
        printSchema(TVOSRuntimeEnvironment.class, mapper);
        printSchema(UnknownRuntimeEnvironment.class, mapper);
        printSchema(UserAttributeChangeEvent.class, mapper);
        printSchema(UserIdentity.class, mapper);
        printSchema(UserIdentityChangeEvent.class, mapper);
        printSchema(ScreenViewEvent.class, mapper);
        printSchema(WebRuntimeEnvironment.class, mapper);

    }

    private static void printSchema(Class<?> type, ObjectMapper mapper) {
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
        try {
            JsonSchema schema = schemaGen.generateSchema(type);
            PrintWriter writer = new PrintWriter(type.getSimpleName() + "_schema.json", "UTF-8");
            writer.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema));
            writer.close();

        }catch (JsonMappingException ex) {

        }catch (IOException ex) {

        }
    }

    private static void testEventProcessing() {

        MessageSerializer m = new MessageSerializer();

        EventProcessingRequest batch = new EventProcessingRequest();
        Account account = new Account();
        Map<String,String> settings = new HashMap<>();
        settings.put("apiKey", "xyz");
        account.setAccountSettings(settings);
        batch.setAccount(account);
        batch.setRuntimeEnvironment(new UnknownRuntimeEnvironment());

        List<Event> events = Arrays.asList(
            new SessionStartEvent(),
            new CustomEvent(),
            new SessionEndEvent());

        batch.setEvents(events);

        try {
            String data = m.serialize(batch);
            System.out.println(data);
            Message request = m.deserialize(data, Message.class);

            SampleExtension processor = new SampleExtension();
            Message response = processor.processMessage(request);

            data = m.serialize(response);
            System.out.println();
            System.out.println(data);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void testSerializer() {

        MessageSerializer m = new MessageSerializer();

        EventProcessingRequest batch = new EventProcessingRequest();

        ProductActionEvent pa = new ProductActionEvent()
                .setAction(ProductActionEvent.Action.PURCHASE)
                .setTaxAmount(new BigDecimal("0.1"))
                .setTotalAmount(new BigDecimal("100.34"))
                        .setShippingAmount(new BigDecimal("23.33"))
                                .setProducts(Arrays.asList(
                                        new Product()
                                                .setId("111")
                                                .setName("Boots")
                                                .setPrice(new BigDecimal("70.00"))
                                                .setQuantity(BigDecimal.valueOf(1))
                                                .setTotalAmount(new BigDecimal("70.00"))
                                                ));

        List<Event> events = Arrays.asList(
                new SessionStartEvent(),
                new CustomEvent(),
                new SessionEndEvent(),
                pa);

        batch.setEvents(events);

        try {
            String s = m.serialize(batch);
            System.out.println(s);

            EventProcessingRequest batch2 = m.deserialize(s, EventProcessingRequest.class);

            for (Event ev : batch2.getEvents()) {
                System.out.println();
                System.out.println(ev.getType());
                System.out.println(ev.getId());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
