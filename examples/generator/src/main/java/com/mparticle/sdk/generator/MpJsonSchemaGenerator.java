package com.mparticle.sdk.generator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.RuntimeEnvironment;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static javax.tools.StandardLocation.CLASS_PATH;

public class MpJsonSchemaGenerator {

    private static final String REGISTRATION_PKG = "com.mparticle.sdk.model.registration";
    private static final String AUTHENTICATION_PKG = "com.mparticle.sdk.model.registration.authentication";
    private static final String EVENT_PROCESSING_PKG = "com.mparticle.sdk.model.eventprocessing";
    private static final String CONSENT_PROCESSING_PKG = "com.mparticle.sdk.model.eventprocessing.consent";
    private static final String SYSTEM_NOTIFICATION_PROCESSING_PKG = "com.mparticle.sdk.model.eventprocessing.notification";
    private static final String AUDIENCE_PROCESSING_PKG = "com.mparticle.sdk.model.audienceprocessing";
    private static final String DSR_PROCESSING_PKG = "com.mparticle.sdk.model.dsrprocessing";
    private static final String SCHEMA_FILE_SUFFIX = "_schema.json";

    public static void main(String[] args) throws RuntimeException, IOException
    {
        // Get file handles
        File mainDir = new File("output");
        File dir = new File(mainDir, "schema");

        // Delete the main directory if it already exists
        deleteDirectory(mainDir);

        // Try to create the directory structure again
        if (dir.mkdirs())
        {
            // Generate the registration message schemata.
            generateSchema(REGISTRATION_PKG, dir);

            // Generate the audience message schemata.
            generateSchema(AUDIENCE_PROCESSING_PKG, dir);

            // Generate the dsr message schemata.
            generateSchema(DSR_PROCESSING_PKG, dir);

            // Generate the event message schemata.
            generateSchema(EVENT_PROCESSING_PKG, dir);
            generateSchema(CONSENT_PROCESSING_PKG, dir);
            generateSchema(SYSTEM_NOTIFICATION_PROCESSING_PKG, dir);
            generateSchema(AUTHENTICATION_PKG, dir);

            // Collect the data which will be used for the JSON sample files
            List<Map.Entry<String, ? extends Message>> messageClasses = Arrays.asList(
                    AudienceMembershipChangeRequestSample.GenerateMessage(),
                    AudienceMembershipChangeResponseSample.GenerateMessage(),
                    AudienceSubscriptionRequestSample.GenerateMessage(),
                    AudienceSubscriptionResponseSample.GenerateMessage(),
                    EventProcessingRequestSample.GenerateMessage(RuntimeEnvironment.Type.IOS),
                    EventProcessingRequestSample.GenerateMessage(RuntimeEnvironment.Type.ANDROID),
                    EventProcessingResponseSample.GenerateMessage(),
                    DsrProcessingRequestSample.GenerateMessage(),
                    DsrProcessingResponseSample.GenerateMessage(),
                    ModuleRegistrationRequestSample.GenerateMessage(),
                    ModuleRegistrationResponseSample.GenerateMessage()
            );

            // Now generate the sample requests
            for (Map.Entry<String, ? extends Message> tuple : messageClasses) {
                File output = new File(mainDir, tuple.getKey() + ".json");

                FileOutputStream stream = new FileOutputStream(output);
                MessageSerializer ser = new MessageSerializer();
                ser.serializePretty(stream, tuple.getValue());
                stream.close();
            }
        }
    }

    private static void deleteDirectory(File directory) throws IOException {
        // We'll only delete the directory if it exists
        if (!directory.exists()) {
            return;
        }

        // Recursively delete this directory structure
        Files.walkFileTree(directory.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void generateSchema(String pkg, File dir) throws IOException {
        Collection<Class> eventClasses = getClasses(pkg);
        for (Class c: eventClasses) {
            // Find file name
            String name = c.getCanonicalName().split(pkg + ".")[1];
            File output = new File(dir, name.concat(SCHEMA_FILE_SUFFIX));

            // Write out this file's schema to a file
            BufferedWriter writer = new BufferedWriter(new FileWriter(output.getAbsoluteFile()));
            String schema = getJsonSchema(c);
            writer.write(schema);
            writer.close();
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
        final Pattern pattern  = Pattern.compile("(.*)(sdk\\.jar)\\((.*)\\)");

        return StreamSupport.stream(list.spliterator(), false)
                .map(javaFileObject -> {
                    try {
                        Matcher m = pattern.matcher(javaFileObject.getName());

                        String className = "";
                        if(m.matches())
                        {
                            className = sanitiseClassName(m.group(3));
                        }
                        return Class.forName(className);

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static String sanitiseClassName(String className)
    {
        // Remove leading '/' if there is one
        if(className.indexOf("/") == 0)
            className = className.substring(1);

        return className
                .replace("/", ".")
                .replace(".class", "");
    }
}
