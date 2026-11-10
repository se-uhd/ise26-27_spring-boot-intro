// gradle/gradle-daemon-jvm.properties requires JDK 25 for the Gradle daemon and lists download URLs for that JDK. When
// Gradle cannot find an installed JDK 25, for example when IntelliJ starts Gradle with another JDK, Gradle downloads
// JDK 25 from these URLs. `gradle updateDaemonJvm` needs this plugin to write the URLs and fails without it. The build
// itself does not use the plugin, because Gradle uses the daemon JDK as the Java toolchain.
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "ise26-27_spring-boot-intro"
