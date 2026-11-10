// Lets Gradle download JDK 25 when Gradle cannot find an installed JDK 25, for example when IntelliJ starts Gradle
// with another JDK.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "ise26-27_spring-boot-intro"
