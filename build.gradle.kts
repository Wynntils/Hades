import java.net.URI


plugins {
    java
    `maven-publish`
}

group = "com.wynntils.hades"
version = "0.5.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.netty:netty-all:4.1.68.Final") // needs to match MC version
    implementation("com.google.guava:guava:31.0.1-jre") // matches MC version

    implementation("com.vdurmont:semver4j:3.1.0")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/wynntils/hades")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            artifactId = "hades"
            from(components["java"])
        }
    }
}

tasks {
    processResources {
        filesMatching("hades.json") {
            expand("version" to version)
        }
    }
}
