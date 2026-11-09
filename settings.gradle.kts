// Lets Gradle download a JDK 25 if it does not find one, for example when IntelliJ starts Gradle with another JDK.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "ise26-27_spring-boot-intro"
