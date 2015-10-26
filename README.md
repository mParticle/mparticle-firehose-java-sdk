# mParticle Java SDK

A Java library for building and deploying third-party extensions for [mParticle](https://www.mparticle.com) over Amazon's [Lambda platform](https://aws.amazon.com/lambda/).

This library will translate data from mParticle into easy-to-use objects so that you can send that data to your platform of choice. The library also provides the necessary APIs to register your extension with mParticle.

## AWS Lambda

The mParticle Extension API is designed to be deployed to AWS's Lambda platform. Check out [AWS Lambda](https://aws.amazon.com/lambda/) for an overview. You'll also need an AWS account once you're ready to start testing and deploying.

## Extension Development

Extensions are composed of a *lambda method* that will be called by AWS.

#### `RequestStreamHandler`

This class will typically serve as the entry-point to your code. Create a subclass of `RequestStreamHandler` and override `handleRequest`. You can then specify the fully-qualified class name of your subclass in your AWS Lambda project. From within `handleRequest`, you'll receive batches of events, data, and more from mParticle which you can use this Java SDK to process. For example: 


```java
public class SampleLambdaEndpoint implements RequestStreamHandler {
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

#### `MessageSerializer` and `MessageProcessor`

The snippet above uses the mParticle `MessageSerializer` class to translate incoming data into more usable objects to be processed. It then uses a custom `SampleExtension` class, a subclass of `MessageProcessor`, to actually interpret the data and process in the correct sequence.

## Starter Project

To really get started with development we recommend [cloning our starter project](https://github.com/mParticle/lambda-extension-sample) and making it your own!

## Including this Library

### Maven/Gradle

The sample extension linked above includes this library via Maven in a Gradle-based build. mParticle makes the binary of this library available via our Maven server which can be added to a Gradle-based build by adding the following custom repository:

```groovy
repositories {
    mavenCentral()
    //for now the SDK is only available via mParticle's Maven server
    //rather than Maven Central
    maven { url "http://maven.mparticle.com/" }
}
```

You can then reference the `com.mparticle:java-sdk` artifact:

```groovy
dependencies {
    compile (
            'com.amazonaws:aws-lambda-java-core:1.1.0',
            'com.amazonaws:aws-lambda-java-events:1.1.0',
            'com.mparticle:java-sdk:1.0.0'
    )
}
```

#### Publish to a local Maven repo

Rather than using mParticle's Maven server, you can also publish the library to your local Maven server by cloning this repo and invoking the following:


    ./gradlew publishToMavenLocal

### Building

You can clone this project and build the jar manually to include in your project's classpath:

    ./gradlew assemble
    
We also make the latest jar binaries available in the releases section of this repo.

## More Resources

### Samples

- See the samples in this project
- Real life example!: [Iterable Extension](https://github.com/mParticle/lambda-iterable)
- [Starter Project](https://github.com/mParticle/lambda-extension-sample) 

### AWS Documentation

- [Creating and deploying a Lambda project](http://docs.aws.amazon.com/lambda/latest/dg/java-gs.html) 
- [Lambda Programming Model Overview](http://docs.aws.amazon.com/lambda/latest/dg/java-programming-model.html)

## Bugs and Feedback

For bugs, questions, etc., please use [Github Issues](https://github.com/mParticle/mparticle-sdk-java/issues).

## Contributing

Contributions welcome! Fork this repo and put up a PR - thanks!

## License

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
