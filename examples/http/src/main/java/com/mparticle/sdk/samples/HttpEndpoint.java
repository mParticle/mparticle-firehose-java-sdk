package com.mparticle.sdk.samples;

import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpEndpoint {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);
        server.setHandler(new SdkHttpHandler());
        server.start();
        server.join();
    }

    public static class SdkHttpHandler extends AbstractHandler {

        private final MessageSerializer serializer = new MessageSerializer();

        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException {
            MessageProcessor processor = new ExampleMessageProcessor();
            Message request = serializer.deserialize(httpRequest.getInputStream(), Message.class);
            Message response = processor.processMessage(request);
            serializer.serialize(httpResponse.getOutputStream(), response);
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
        }
    }

    /**
     * Reference other examples and the docs for how to implement a MessageProcessor
     */
    public static class ExampleMessageProcessor extends MessageProcessor {

        @Override
        public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {
            return null;
        }
    }
}
