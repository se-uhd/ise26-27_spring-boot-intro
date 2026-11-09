// The build file that Spring Initializr (https://start.spring.io/) generates for a Gradle - Kotlin project with
// Kotlin, Spring Boot 4.1.2, Java 25, and the dependency Spring Web, with Kotlin 2.4.21 instead of 2.3.21. Kotlin 2.4
// applies annotations on constructor properties to the parameter and the property by default, so the compiler option
// -Xannotation-default-target=param-property that Initializr adds is left out.
plugins {
    kotlin("jvm") version "2.4.21"
    // Opens the classes that carry Spring annotations, such as @RestController, because Kotlin classes are final by
    // default and Spring creates subclasses of some beans at runtime.
    kotlin("plugin.spring") version "2.4.21"
    // Adds the tasks bootRun and bootJar.
    id("org.springframework.boot") version "4.1.2"
    // Takes the versions of the Spring Boot dependencies from the Spring Boot BOM, so the dependencies below have no
    // version.
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
}
