package com.mparticle.sdk.tmp;

import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.eventprocessing.consent.GDPRConsent;
import com.mparticle.sdk.model.eventprocessing.notification.GDPRConsentStateNotification;
import com.mparticle.sdk.model.eventprocessing.notification.SystemNotification;
import com.mparticle.sdk.model.registration.Account;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.samples.SampleExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class TestMain {
    public static void main(String[] args) {
        testSerializer();
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

        GDPRConsentStateNotification notification = new GDPRConsentStateNotification();
        notification.setPurpose("foo purpose");
        notification.setNewConsentState(new GDPRConsent()
                .setConsented(true)
                .setDocument("foo document")
                .setHardwareId("foo hardware id")
                .setLocation("foo location")
                .setTimestamp(123L));
        notification.setOldConsentState(new GDPRConsent()
                .setConsented(false)
                .setDocument("bar document")
                .setHardwareId("bar hardware id")
                .setLocation("bar location")
                .setTimestamp(456L));
        List<SystemNotification> systemNotifications = new ArrayList<>();
        systemNotifications.add(notification);

        batch.setSystemNotifications(systemNotifications);
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