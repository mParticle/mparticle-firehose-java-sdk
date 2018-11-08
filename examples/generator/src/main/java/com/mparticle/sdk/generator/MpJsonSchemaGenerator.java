package com.mparticle.sdk.generator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static javax.tools.StandardLocation.CLASS_PATH;

public class MpJsonSchemaGenerator {
    public static void main(String[] args) throws RuntimeException, IOException
    {
        File dir = new File("output//schema");
        dir.mkdirs();

        // Generate the registration message schemas.
        Collection<Class> registrationClasses = getClasses("com.mparticle.sdk.model.registration");
        for (Class c: registrationClasses) {
            String schema = getJsonSchema(c);
            File output = new File(dir, c.getSimpleName() + "_schema.json");
            BufferedWriter writer = new BufferedWriter(new FileWriter(output.getAbsoluteFile()));
            writer.write(schema);
            writer.close();
        }

        // Generate the audience message schemas.
        Collection<Class> audienceClasses = getClasses("com.mparticle.sdk.model.audienceprocessing");
        for (Class c: audienceClasses) {
            String schema = getJsonSchema(c);
            File output = new File(dir, c.getSimpleName() + "_schema.json");
            BufferedWriter writer = new BufferedWriter(new FileWriter(output.getAbsoluteFile()));
            writer.write(schema);
            writer.close();
        }

        // Generate the event message schemas.
        Collection<Class> eventClasses = getClasses("com.mparticle.sdk.model.eventprocessing");
        for (Class c: eventClasses) {
            String schema = getJsonSchema(c);
            File output = new File(dir, c.getSimpleName() + "_schema.json");
            BufferedWriter writer = new BufferedWriter(new FileWriter(output.getAbsoluteFile()));
            writer.write(schema);
            writer.close();
        }

        dir = new File("output");
        dir.mkdir();

        List<Message> messageClasses = Arrays.asList(
                AudienceMembershipChangeRequestSample.GenerateMessage(),
                AudienceMembershipChangeResponseSample.GenerateMessage(),
                AudienceSubscriptionRequestSample.GenerateMessage(),
                AudienceSubscriptionResponseSample.GenerateMessage(),
                ModuleRegistrationRequestSample.GenerateMessage(),
                ModuleRegistrationResponseSample.GenerateMessage()
        );

        // Now generate the sample requests
        for (Message req : messageClasses) {
            File output = new File(dir, req.getClass().getSimpleName() + ".json");
            FileOutputStream stream = new FileOutputStream(output);
            MessageSerializer ser = new MessageSerializer();
            ser.serializePretty(stream, req);
            stream.close();
        }
    }

    private static String getJsonSchema(Class schemaClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper foo = new SchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(schemaClass, foo);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        JsonSchemaGenerator generator = new JsonSchemaGenerator(mapper);
        JsonSchema schema = generator.generateSchema(schemaClass);
        return mapper.writeValueAsString(schema);
    }

    private static Collection<Class> getClasses(final String pack) throws RuntimeException, IOException {
        final StandardJavaFileManager fileManager = ToolProvider.getSystemJavaCompiler().getStandardFileManager(null, null, null);
        final Iterable<JavaFileObject> list = fileManager.list(CLASS_PATH, pack, Collections.singleton(JavaFileObject.Kind.CLASS), false);
        return StreamSupport.stream(list.spliterator(), false)
                .map(javaFileObject -> {
                    try {
                        final String[] split = javaFileObject.getName()
                                .replace(".class", "")
                                .replace(")", "")
                                .split(Pattern.quote(File.separator));

                        final String fullClassName = pack + "." + split[split.length - 1];
                        return Class.forName(fullClassName);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
