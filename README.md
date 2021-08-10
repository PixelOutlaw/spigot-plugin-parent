# spigot-plugin-parent
[![Maven Central](https://img.shields.io/maven-central/v/io.pixeloutlaw/spigot-plugin-parent)](https://mvnrepository.com/artifact/io.pixeloutlaw/spigot-plugin-parent)

Maven parent for all Pixel Outlaw Spigot plugins.

## How to Get
Add the following dependency to your POM:
```xml
<parent>
    <groupId>io.pixeloutlaw</groupId>
    <artifactId>spigot-plugin-parent</artifactId>
    <version>x.y.z.aa</version>
</parent>
```

## CI/CD

### CI

`mvn verify` will be run on every PR.

### CD / Making a Release

Creating a GitHub release will make a new release with the version from the GitHub release name and publish it to
Maven Central.
