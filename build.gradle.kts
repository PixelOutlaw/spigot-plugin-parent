plugins {
    `java-platform`
    id("nebula.maven-publish") version "17.3.2"
    id("io.pixeloutlaw.gradle") version "4.4.3"
}

group = "io.pixeloutlaw"
description = "A parent project for Pixel Outlaw's Maven-based Spigot plugins."
version = "1.17.0.1-SNAPSHOT"

repositories {
    maven {
        name = "central"
        url = uri(ArtifactRepositoryContainer.MAVEN_CENTRAL_URL)
    }
    maven {
        name = "spigotmc-snapshots"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    }
    maven {
        name = "papermc-releases"
        url = uri("https://papermc.io/repo/repository/maven-releases")
    }
    maven {
        name = "papermc-snapshots"
        url = uri("https://papermc.io/repo/repository/maven-snapshots")
    }
    maven {
        name = "ossrh-snapshots"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
    maven {
        name = "jitpack"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    // only platforms (BOMs) go up here
    api(platform("org.jetbrains.kotlin:kotlin-bom:1.5.10"))
    api(platform("org.junit:junit-bom:5.7.1"))

    // normal dependencies go here
    constraints {
        api("com.destroystokyo.paper:paper-api:1.17-R0.1-SNAPSHOT")
        api("junit:junit:4.13.2")
        api("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    }
}

val mavenProperties = mapOf(
    "project.build.sourceEncoding" to "UTF-8",
    "project.build.javaVersion" to "1.8",
    "kotlin.compiler.incremental" to "true"
)

javaPlatform {
    // allows importing BOMs
    allowDependencies()
}

publishing {
    publications {
        getByName<MavenPublication>("nebula") {
            // apply rules from dependencies section
            from(components["javaPlatform"])

            // add repositories
            pom {
                withXml {
                    val root = asNode()

                    // handle repositories
                    val repos = root.children().find {
                        it is groovy.util.Node && it.name().toString()
                            .endsWith("repositories")
                    } as groovy.util.Node? ?: root.appendNode("repositories")
                    project.repositories.withType<MavenArtifactRepository>().forEach { repository ->
                        repos.appendNode("repository").apply {
                            appendNode("id", repository.name)
                            appendNode("url", repository.url.toString())
                        }
                    }

                    // handle properties
                    val properties = root.children().find {
                        it is groovy.util.Node && it.name().toString().endsWith("properties")
                    } as groovy.util.Node? ?: root.appendNode("properties")
                    mavenProperties.forEach { (key, value) ->
                        properties.appendNode(key, value)
                    }

                    // handle build
                    val xmlParser = groovy.xml.XmlParser()
                    val buildSnippet = xmlParser.parse(project.file("snippets/build.xml"))
                    val profilesSnippet = xmlParser.parse(project.file("snippets/profiles.xml"))
                    root.append(buildSnippet)
                    root.append(profilesSnippet)
                }
            }
        }
    }
}
