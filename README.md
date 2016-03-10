# mParticle Java SDK

[![Maven Central Status](https://maven-badges.herokuapp.com/maven-central/com.mparticle/java-sdk/badge.svg?style=flat-square)](https://search.maven.org/#search%7Cga%7C1%7Cmparticle)

A Java library for building and deploying third-party extensions for [mParticle](https://www.mparticle.com) over Amazon's [Lambda platform](https://aws.amazon.com/lambda/).

This library translates messages from the mParticle Firehose API into easy-to-use objects so that you can register and integrate your service with mParticle.

## What is mParticle Firehose?

mParticle aims to allow our customers to send their data wherever they want it to go, and we integrate with many services across the mobile-app ecosystem in order to achieve that. mParticle Firehose is an API that lets anybody become one of our integration partners. Leveraging Amazon AWS's [Lambda platform](https://aws.amazon.com/lambda/), mParticle will send your "lambda function" data as it comes into our system so that your function can then forward it along to your own API.

## Documentation

- [See the wiki for an in-depth tutorial](https://github.com/mParticle/mparticle-sdk-java/wiki)
- [Javadocs](http://docs.mparticle.com/includes/java-sdk-javadocs/)

### Samples

- [Starter Project](https://github.com/mParticle/lambda-extension-sample) 
- [See the samples in this project](https://github.com/mParticle/mparticle-sdk-java/tree/master/mparticle-sdk-java)
- [Real life example](https://github.com/mParticle/lambda-iterable)

### AWS Lambda Overview

- [Creating and deploying a Lambda project](http://docs.aws.amazon.com/lambda/latest/dg/java-gs.html) 
- [Lambda Programming Model Overview](http://docs.aws.amazon.com/lambda/latest/dg/java-programming-model.html)

## Bugs and Feedback

For bugs, questions, etc., please use [Github Issues](https://github.com/mParticle/mparticle-sdk-java/issues).

## Contributing

Contributions welcome! Fork this repo and put up a PR - thanks!

## License

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)
