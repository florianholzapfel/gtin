plugins {
    kotlin("jvm") version "1.5.0"
}

group = "com.github.florianholzapfel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.strikt:strikt-core:0.31.0")
    testImplementation(kotlin("test-junit5"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}