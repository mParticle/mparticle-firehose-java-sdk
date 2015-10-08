package com.mparticle.sdk.tmp;

import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.samples.SampleExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        testRegister();
        testEventProcessing();
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

        /*
        SampleExtension processor = new SampleExtension();

        ModuleRegistrationRequest request = new ModuleRegistrationRequest();

        Message response = processor.processMessage(request);

        try {
            String s = m.serialize(response);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // TODO: create serializer unit tests

        EventProcessingRequest batch = new EventProcessingRequest();

        List<Event> events = Arrays.asList(
                new SessionStartEvent(),
                new CustomEvent(),
                new SessionEndEvent());

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
