# Release Process

The SDK is published to Maven Central as `com.mparticle:java-sdk` using GitHub Actions. `VERSION` is the single source of truth for the release version.

## Release workflows

### Release – Draft

Run `Release – Draft` from `master` to open a release PR. The workflow:

1. Computes the next version from `bump-type` and an optional `qualifier`, and writes it to `VERSION`.
2. Builds, tests, and publishes the SDK to Maven local as a smoke test.
3. Generates the `CHANGELOG.md` entry.
4. Opens a `release-prep/<version>` PR back into the dispatching branch.

### Release – Publish

`Release – Publish` runs when `VERSION` changes on `master`. It builds and tests the SDK, uploads the signed artifacts to the Maven Central Portal, then tags `v<version>` and creates the GitHub release.

The upload is not released automatically. Open the deployment in the [Central Portal](https://central.sonatype.com/publishing) and publish it.

## Stable and pre-releases

- **Stable:** choose `patch`, `minor`, or `major` and leave `qualifier` empty. The GitHub release is marked as latest.
- **Pre-release:** set `qualifier` (for example `rc1`). Use `bump-type=none` to change or drop the qualifier without moving the base version. The GitHub release is marked as a pre-release.

| Current `VERSION` | Inputs | New `VERSION` |
|---|---|---|
| `3.4.0` | `minor`, `rc1` | `3.5.0-rc1` |
| `3.5.0-rc1` | `none`, `rc2` | `3.5.0-rc2` |
| `3.5.0-rc2` | `none`, empty | `3.5.0` |

## Required secrets

`MAVEN_CENTRAL_SIGNING_KEY`, `MAVEN_CENTRAL_SIGNING_KEY_PASSWORD`, `SONATYPE_NEXUS_USERNAME`, `SONATYPE_NEXUS_PASSWORD`, plus the SDK release GitHub App (`SDK_RELEASE_GITHUB_CLIENT_ID`, `SDK_RELEASE_GITHUB_APP_PRIVATE_KEY`, with the app installed on this repo) so `Release – Draft` can open its PR. `Release – Publish` fails before building if any Maven secret is missing, so it never uploads unsigned artifacts.

## Local publishing

```sh
./gradlew :sdk:publishToMavenLocal -PVERSION=0.0.0-local
```

Without `-PVERSION`, the build uses `VERSION`. Local builds are unsigned unless `ORG_GRADLE_PROJECT_signingInMemoryKey` is set.
