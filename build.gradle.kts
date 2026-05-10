import java.net.URI


plugins {
    java
    `maven-publish`
}

group = "com.wynntils.hades"
version = "0.6.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.netty:netty-all:4.2.7.Final") // needs to match MC version
    implementation("com.google.guava:guava:33.5.0-jre") // matches MC version
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