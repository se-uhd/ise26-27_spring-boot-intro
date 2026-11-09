# Software Engineering (ISE) Winter Semester 2026/27

This repository contains a minimal Spring Boot application in Kotlin for the lecture on Spring Boot. `DemoApp.kt` and
`HelloController.kt` are the "Hello, World!" from the slides, and a greeting example shows constructor injection,
configuration properties, and a profile. [Coffee Kitty](https://github.com/se-uhd/coffee-kitty), the running example of
the course, uses the same concepts in an application with four Gradle subprojects and a PostgreSQL database. This
application needs neither Docker nor a database.

## Setup

The project needs the Temurin JDK 25 and Gradle 9.8. Gradle downloads the Kotlin compiler and Spring Boot during the
build, so you do not need to install them. The easiest way to install both tools in the right versions is
[mise](https://mise.jdx.dev), which reads them from [`mise.toml`](mise.toml):

```shell
git clone https://github.com/se-uhd/ise26-27_spring-boot-intro.git
cd ise26-27_spring-boot-intro
mise trust
mise install
```

Without shell activation, prefix each command with `mise exec --`, as in `mise exec -- gradle build`.

The project does not use the Gradle wrapper, so IntelliJ IDEA has to use the Gradle installation from mise. Otherwise,
IntelliJ generates a wrapper with its bundled Gradle version and warns that this version is outdated. Open the
repository directory in IntelliJ, go to Settings | Build, Execution, Deployment | Build Tools | Gradle, select "Local
installation", and set the path to the Gradle home in the mise installation directory. `mise where gradle` prints the
installation directory, and the Gradle home is its subdirectory `gradle-9.8.2`, as in
`/Users/<name>/.local/share/mise/installs/gradle/9.8.2/gradle-9.8.2` on macOS. Then reload the Gradle project and start
the application with the run icon next to the `main` function in `DemoApp.kt`.

## Running the application

`gradle bootRun` starts the application on port 8080. Send requests to it with curl from a second terminal:

```shell
curl http://localhost:8080/hello
curl http://localhost:8080/greeting
curl "http://localhost:8080/greeting?name=Kitty"
```

The first command prints `Hello, Spring Boot!`, the second `Hello, World!`, and the third `Hello, Kitty!`. The dev
profile replaces the salutation "Hello" with "Hi". Stop the application with Ctrl+C and start it again with the dev
profile:

```shell
gradle bootRun --args='--spring.profiles.active=dev'
```

Now `curl http://localhost:8080/greeting` prints `Hi, World!`. `gradle build` compiles the code and runs the tests.

## Files

The Kotlin files are in `src/main/kotlin/de/seuhd/teaching/springboot/` and the tests in
`src/test/kotlin/de/seuhd/teaching/springboot/`. The configuration is in `src/main/resources/application.yaml`.

| File | Content |
|---|---|
| `DemoApp.kt` | The application class with `@SpringBootApplication` and the `main` function |
| `HelloController.kt` | The controller from the slides, which answers `GET /hello` |
| `GreetingController.kt` | A controller that receives a `GreetingService` through its constructor and answers `GET /greeting` |
| `GreetingService.kt` | The interface of the service that builds the greeting |
| `GreetingServiceImpl.kt` | The implementation of `GreetingService`, a bean because of `@Service`, which receives `GreetingProperties` through its constructor |
| `GreetingProperties.kt` | The configuration properties under the key `greeting` in `application.yaml` |
| `HelloControllerTest.kt`, `GreetingControllerTest.kt` | Tests that start the application and send requests through MockMvc |
| `GreetingControllerDevProfileTest.kt` | A test of `GET /greeting` with the dev profile |
| `GreetingServiceImplTest.kt` | A unit test without Spring, which passes its own `GreetingProperties` to the constructor |

`DemoApp.kt` differs from the slides by the annotation `@ConfigurationPropertiesScan`, which the greeting example needs.
The first commit of the repository contains the "Hello, World!" without the greeting example.

## Differences from Spring Initializr

`build.gradle.kts` is the build file that [Spring Initializr](https://start.spring.io/) generates for a Gradle - Kotlin
project with Kotlin, Spring Boot 4.1.2, Java 25, and the dependency Spring Web. The repository differs from the
generated project where it follows the setup of Coffee Kitty:

- mise provides Java and Gradle, and the repository has no Gradle wrapper (`gradlew`).
- `settings.gradle.kts` applies the foojay toolchain resolver, and `gradle/gradle-daemon-jvm.properties` requires JDK
  25 for the Gradle daemon, so that IntelliJ can run the build even if it starts Gradle with another JDK.
- The project uses Kotlin 2.4.21 instead of 2.3.21.
- The configuration is in `application.yaml` instead of `application.properties`.
- `gradle.properties` enables the configuration cache and overrides the Jackson version until Spring Boot brings a
  fixed release.
- The tests run with the JVM option `-Xshare:off`, which removes a warning about class data sharing.
- The files are indented with four spaces instead of tabs, as `.editorconfig` defines.
- A GitHub Actions workflow runs `gradle build` for every push to `main` and every pull request.
