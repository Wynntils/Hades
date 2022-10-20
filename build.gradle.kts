plugins {
    java
}

group = "com.wynntils.hades"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.netty:netty-all:4.1.9.Final") // needs to match MC version
    implementation("com.google.guava:guava:21.0") // matches MC version
}
