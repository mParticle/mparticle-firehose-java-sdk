# mparticle-sdk-java

A Java library for building and deploying third-party extensions for the [mParticle Data Automation Platform](https://www.mparticle.com).

## Extension Development

### 1. Create AWS Lambda project. 

The [AWS Developer Guide](http://docs.aws.amazon.com/lambda/latest/dg/java-gs.html) provides step-by-step instructions for creating  and deploying Lambda functions. 

### 2. Link mParticle SDK.

Releases of mParticle SDK are available in the Maven central repository.

*For Maven:*

```xml
<dependency>
  <groupId>com.mparticle</groupId>
  <artifactId>java-sdk</artifactId>
  <version>LATEST</version>
</dependency>
```

*For Gradle:*

```gradle
compile 'com.mparticle:java-sdk:+'
```

### 3. Create Extension. 

A typical extension would inherit from the ```MessageProcessor``` and implement custom event processing logic by overriding one or more callback methods. 

```java
public class SampleExtension extends MessageProcessor {

    // This method is called by mParticle servers during publishing process
    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {

        // Describe your extension
        ModuleRegistrationResponse response = new ModuleRegistrationResponse("SampleExtension", "1.0");

        // Request access to specific user and device id's
        Permissions permissions = new Permissions();

        List<DeviceIdentityPermission> deviceIds = Arrays.asList(
                new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.MD5),
                new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.MD5)
        );

        permissions.setDeviceIdentities(deviceIds);

        List<UserIdentityPermission> userIds = Arrays.asList(
                new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.RAW)
        );

        permissions.setUserIdentities(userIds);

        // Request access to GPS data
        permissions.setAllowAccessLocation(true);

        response.setPermissions(permissions);

        // Register a mobile event stream listener
        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration();

        eventProcessingRegistration.setDescription("Sample Event Processor");

        // Declare supported environments
        List<RuntimeEnvironment.Type> runtimeEnvironments = Arrays.asList(
                RuntimeEnvironment.Type.ANDROID,
                RuntimeEnvironment.Type.IOS);

        // Add account settings that should be provided by the subscribers
        List<Setting> accountSettings = new ArrayList<>();

        TextSetting apiKey = new TextSetting("apiKey", "API Key");
        apiKey.setIsRequired(true);
        accountSettings.add(apiKey);

        eventProcessingRegistration.setAccountSettings(accountSettings);

        // Specify supported event types
        List<Event.Type> supportedEventTypes = Arrays.asList(Event.Type.CUSTOM_EVENT);
        eventProcessingRegistration.setSupportedEventTypes(supportedEventTypes);

        response.setEventProcessingRegistration(eventProcessingRegistration);

        // Register audience stream listener
        AudienceProcessingRegistration audienceProcessingRegistration = new AudienceProcessingRegistration();
        audienceProcessingRegistration.setDescription("Sample Audience Processor");
        audienceProcessingRegistration.setAccountSettings(accountSettings);
        response.setAudienceProcessingRegistration(audienceProcessingRegistration);

        return response;
    }

    @Override
    public void processCustomEvent(CustomEvent event) {

        // Read account settings
        Account account = event.getContext().getAccount();
        String apiKey = account.getStringSetting("apiKey", true, null);

        // Access mobile device information
        RuntimeEnvironment environment = event.getContext().getRuntimeEnvironment();

        if (environment.getType() == RuntimeEnvironment.Type.IOS) {
            IosRuntimeEnvironment ios = (IosRuntimeEnvironment)environment;
            String cpuArchitecture = ios.getCpuArchitecture();
        }

        // Access device GPS data
        Location location = event.getLocation();

        if (location != null) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
        }

        // Iterate over custom event attributes
        Map<String, String> userAttributes = event.getAttributes();

        if (userAttributes != null) {
            for (Map.Entry<String, String> entry : userAttributes.entrySet()) {
                String attributeKey = entry.getKey();
                String attributeValue = entry.getValue();
            }
        }
    }

    @Override
    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {

        for (UserProfile profile : request.getUserProfiles()) {
            
            // extract emails from the user profile
            List<String> emails = profile.getUserIdentities().stream()
                    .filter(id -> id.getType() == UserIdentity.Type.EMAIL && id.getEncoding() == Identity.Encoding.RAW)
                    .map(Identity::getValue)
                    .collect(Collectors.toList());

            // get a list of added audiences 
            List<String> added = profile.getAddedAudiences().stream()
                    .map(a -> a.getAudienceName())
                    .collect(Collectors.toList());

            // get a list of removed audiences 
            List<String> removed = profile.getAddedAudiences().stream()
                    .map(a -> a.getAudienceName())
                    .collect(Collectors.toList());
            
            // update online user profile store
            // ...
        }

        return new AudienceMembershipChangeResponse();
    }
}


```

### 4. Add Lambda Function

Below is an example of a typical Lambda function.  

```java
public class AwsLambdaEndpoint implements RequestStreamHandler {
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        SampleExtension processor = new SampleExtension();
        MessageSerializer serializer = new MessageSerializer();
        Message request = serializer.deserialize(input, Message.class);
        Message response = processor.processMessage(request);
        serializer.serialize(output, response);
    }
}
```

Please refer to the [AWS Developer Guide]((http://docs.aws.amazon.com/lambda/latest/dg/java-programming-model.html)) for more details.

### 5. Publish Lambda Function

The [AWS Developer Guide](http://docs.aws.amazon.com/lambda/latest/dg/java-gs.html) provides step-by-step instructions.

The snippet below can be used for running a quick test from the AWS Management Console.

```javascript
{
  "type": "module_registration_request",
  "id": "2126a6ee-a4dc-421d-8b5f-fef1d3a3217c",
  "timestamp_ms": 1444249312974
}
```

### 6. Publish Extension

1. Go to https://www.mparticle.com and log into your account.
2. Click ```Publish Extension```. 
3. When prompted enter function ARN and click ```Next```. 
4. Grant permissions to the provided AWS account to let us call your Lambda function.
5. Click ```Verify```. If everything goes well you will be presented with the confirmation screen.
6. Click ```Publish``` to complete the process.   

## Building

```bash
$ git clone https://github.com/mParticle/mparticle-sdk-java.git
$ cd mparticle-sdk-java/
$ gradlew build
```

## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://github.com/mParticle/mparticle-sdk-java/issues).


## Contributing

We encourage contributions through pull requests from forks of this repository. 

- Fork it!
- Create your feature branch: ```git checkout -b my-new-feature```
- Commit your changes: ```git commit -am 'Add some feature'```
- Push to the branch: ```git push origin my-new-feature```
- Submit a pull request


## License

Apache License, Version 2.0
