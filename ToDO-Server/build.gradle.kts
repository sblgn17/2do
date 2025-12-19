plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
}

tasks.test {
    useJUnitPlatform()
}