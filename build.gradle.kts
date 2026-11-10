// This build file starts from the one that Spring Initializr (https://start.spring.io/) generates for the project type
// "Gradle - Kotlin", the language Kotlin, Spring Boot 4.1.2, Java 25, and the dependency Spring Web. This file uses
// Kotlin 2.4.21 instead of 2.3.21 and leaves out the compiler option -Xannotation-default-target=param-property, which
// Initializr adds. The option makes an annotation on a constructor property apply to the parameter and the property,
// and Kotlin 2.4 does this by default.
plugins {
    kotlin("jvm") version "2.4.21"
    // Makes the classes with Spring annotations, such as @RestController, open, because Kotlin classes are final by
    // default and Spring creates subclasses of some beans at runtime.
    kotlin("plugin.spring") version "2.4.21"
    // Adds the tasks bootRun and bootJar.
    id("org.springframework.boot") version "4.1.2"
    // Takes the versions of the dependencies below from the Spring Boot BOM (bill of materials). The dependencies
    // therefore omit the version.
    id("io.spring.dependency-management") version "1.1.7"
}

group = "de.seuhd.teaching"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("tools.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    // Mockito comes with spring-boot-starter-webmvc-test and attaches an agent that appends to the boot class path. The
    // JVM then warns that class data sharing works only for classes of the boot loader. Turning class data sharing off
    // removes the warning.
    jvmArgs("-Xshare:off")
}
