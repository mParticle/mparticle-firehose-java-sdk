# mParticle Java SDK

A Java library for building and deploying third-party extensions for [mParticle](https://www.mparticle.com) over Amazon's [Lambda platform](https://aws.amazon.com/lambda/).

This library will translate data from mParticle into easy-to-use objects so that you can send that data to your platform of choice. The library also provides the necessary APIs to register your extension with mParticle.

## AWS Lambda

The mParticle Extension API is designed to be deployed to AWS's Lambda platform. Check out [AWS Lambda](https://aws.amazon.com/lambda/) for an overview. You'll also need an AWS account once you're ready to start testing and deploying.

## Extension Development

[See the Wiki of this repo for a tutorial](https://github.com/mParticle/mparticle-sdk-java/wiki).

## Including this Library

### Maven/Gradle

mParticle makes the binary jar of this library available via our Maven server which can be added to a Gradle-based build by adding the following custom repository:

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

- [See the wiki](https://github.com/mParticle/mparticle-sdk-java/wiki)

### Samples

- [Starter Project](https://github.com/mParticle/lambda-extension-sample) 
- See the samples in this project
- [Real life example](https://github.com/mParticle/lambda-iterable)

### AWS Documentation

- [Creating and deploying a Lambda project](http://docs.aws.amazon.com/lambda/latest/dg/java-gs.html) 
- [Lambda Programming Model Overview](http://docs.aws.amazon.com/lambda/latest/dg/java-programming-model.html)

## Bugs and Feedback

For bugs, questions, etc., please use [Github Issues](https://github.com/mParticle/mparticle-sdk-java/issues).

## Contributing

Contributions welcome! Fork this repo and put up a PR - thanks!

## License

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
