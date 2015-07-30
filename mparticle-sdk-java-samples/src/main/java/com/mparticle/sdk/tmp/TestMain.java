package com.mparticle.sdk.tmp;

import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.registration.RegistrationRequest;
import com.mparticle.sdk.samples.SampleMessageProcessor;
import java.io.IOException;

public class TestMain {
    public static void main(String[] args) {
        testSerializer();
    }

    private static void testSerializer() {
        MessageSerializer m = new MessageSerializer();
        SampleMessageProcessor processor = new SampleMessageProcessor();

        RegistrationRequest request = new RegistrationRequest();

        Message response = processor.processMessage(request);

        try {
            String s = m.serialize(response);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        EventProcessingRequest batch = new EventProcessingRequest();
        List<Event> events = new ArrayList<>();
        events.add(new AppEvent());
        events.add(new SessionStartEvent());
        batch.events = events;

        try {
            String s = m.serialize(batch);
            System.out.println(s);

            EventProcessingRequest batch2 = m.deserialize(s, EventProcessingRequest.class);

            for (Event ev : batch2.events) {
                System.out.println(ev.getType());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

}
