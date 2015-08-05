package com.mparticle.sdk.tmp;

import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.RegistrationRequest;
import com.mparticle.sdk.samples.SampleMessageProcessor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        testEventProcessing();
    }

    private static void testEventProcessing() {

        MessageSerializer m = new MessageSerializer();

        EventProcessingRequest batch = new EventProcessingRequest();
        List<Event> events = new ArrayList<>();
        events.add(new SessionStartEvent());
        events.add(new AppEvent());
        events.add(new SessionEndEvent());
        batch.events = events;

        try {
            String data = m.serialize(batch);
            System.out.println(data);
            Message request = m.deserialize(data, Message.class);

            SampleMessageProcessor processor = new SampleMessageProcessor();
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
        SampleMessageProcessor processor = new SampleMessageProcessor();

        RegistrationRequest request = new RegistrationRequest();

        Message response = processor.processMessage(request);

        try {
            String s = m.serialize(response);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // TODO: create serializer unit tests

        EventProcessingRequest batch = new EventProcessingRequest();
        List<Event> events = new ArrayList<>();
        events.add(new SessionStartEvent());
        events.add(new AppEvent());
        batch.events = events;

        try {
            String s = m.serialize(batch);
            System.out.println(s);

            EventProcessingRequest batch2 = m.deserialize(s, EventProcessingRequest.class);

            for (Event ev : batch2.events) {
                System.out.println();
                System.out.println(ev.type);
                System.out.println(ev.id);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
